package com.example.proyecto_final.backWeb.mensajes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
@Service
public class MensajeService {

    @Autowired
    private RepostoryLlamadas repostoryLlamadas;

    public Iterable<Mensaje> getRepostoryLlamadas() {
        return repostoryLlamadas.findAll();
    }
    public Mensaje guardarMensaje(Mensaje mensaje) {
        if (mensaje.getIdMensaje() == null || mensaje.getIdMensaje().isEmpty()) {
            mensaje.setIdMensaje(UUID.randomUUID().toString());
        }
        return repostoryLlamadas.save(mensaje);
    }

}
