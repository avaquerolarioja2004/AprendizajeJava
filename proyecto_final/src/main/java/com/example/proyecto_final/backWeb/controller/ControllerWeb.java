package com.example.proyecto_final.backWeb.controller;

import com.example.proyecto_final.backWeb.mensajes.Mensaje;
import com.example.proyecto_final.backWeb.mensajes.MensajeService;
import com.example.proyecto_final.backWeb.rabbit.ReciverBW;
import com.example.proyecto_final.backWeb.rabbit.SenderBW;
import com.example.proyecto_final.commons.entityCliente.ClienteInPut;
import com.example.proyecto_final.commons.entityReserva.ReservaInPut;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/v0")
public class ControllerWeb {
    @Autowired
    private SenderBW senderBW;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    MensajeService service;

    @PostMapping("/reserva")
    public ResponseEntity<String> reserva(@RequestBody ReservaInPut reserva) {
        try {
            senderBW.sendMessage(reserva);
            // Lógica adicional para verificar el mensaje de respuesta
            if (ReciverBW.mensaje.contains("ERROR")) {
                return new ResponseEntity<>(ReciverBW.mensaje, HttpStatus.valueOf(501));
            }
            String jsonMessage = objectMapper.writeValueAsString(reserva);
            Mensaje m=new Mensaje();
            m.setMensaje(jsonMessage);
            service.guardarMensaje(m);
            return new ResponseEntity<>(ReciverBW.mensaje, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody ClienteInPut cliente) {
        try {
            senderBW.sendMessageCliente(cliente);
            // Lógica adicional para verificar el mensaje de respuesta
            if (ReciverBW.mensaje.contains("ERROR")) {
                return new ResponseEntity<>(ReciverBW.mensaje, HttpStatus.valueOf(501));
            }
            String jsonMessage = objectMapper.writeValueAsString(cliente);
            Mensaje m=new Mensaje();
            m.setMensaje(jsonMessage);
            service.guardarMensaje(m);
            return new ResponseEntity<>(ReciverBW.mensaje, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getmensajes")
    public ResponseEntity<List<Mensaje>> getmensajes() {
        Iterable<Mensaje> mensajesIterable = service.getRepostoryLlamadas();
        List<Mensaje> mensajes = StreamSupport.stream(mensajesIterable.spliterator(), false)
                .collect(Collectors.toList());
        return ResponseEntity.ok(mensajes);
    }
}
