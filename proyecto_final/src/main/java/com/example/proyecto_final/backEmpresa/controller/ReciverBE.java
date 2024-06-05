package com.example.proyecto_final.backEmpresa.controller;

import com.example.proyecto_final.backEmpresa.repositories.AutobusRepository;
import com.example.proyecto_final.backEmpresa.repositories.ClienteRepository;
import com.example.proyecto_final.backEmpresa.repositories.ReservaRepository;
import com.example.proyecto_final.commons.entityBus.Autobus;
import com.example.proyecto_final.commons.entityCliente.Cliente;
import com.example.proyecto_final.commons.entityReserva.ReservaInPut;
import com.example.proyecto_final.commons.rabbitConfig.RabbitMQConfig;
import com.example.proyecto_final.commons.entityReserva.Reserva;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class ReciverBE {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private AutobusRepository autobusRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private SenderBE senderBE;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME_RESERVA)
    public void receiveMessage(String jsonMessage) {
        try {
            ReservaInPut reserva = objectMapper.readValue(jsonMessage, ReservaInPut.class);
            System.out.println("Received: " + reserva);
            boolean clienteEncontrado = false;
            for(Autobus autobus : autobusRepository.findAll()) {
                if(autobus.getDestino().equals(reserva.getDestino()) && autobus.getCapacidad()>0 && autobus.getHora()==Integer.parseInt(reserva.getHora())){
                    Reserva saver=new Reserva();
                    saver.setAutobus(autobus);
                    saver.setFecha(LocalDate.now());
                    // Intenta encontrar el cliente por su email
                    Optional<Cliente> clienteOptional = clienteRepository.findByEmail(reserva.getEmail());
                    if (clienteOptional.isPresent()) {
                        saver.setCliente(clienteOptional.get());
                        reservaRepository.save(saver);
                        senderBE.sendMessage("CORRECT");
                    } else {
                        // Si no se encuentra el cliente, lanza una excepción
                        throw new IllegalArgumentException("ERROR: Cree una cuenta o escriba correctamente el correo");
                    }
                    clienteEncontrado = true;
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            senderBE.sendMessage("ERROR: " + e.getMessage());
        }
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME_CLIENTE)
    public void reciveCliente(String jsonMessage) {
        try {
            Cliente cliente = objectMapper.readValue(jsonMessage, Cliente.class);
            Optional<Cliente> clienteExistente = clienteRepository.findByEmail(cliente.getEmail());

            if (clienteExistente.isPresent()) {
                // Cliente ya existe, envía mensaje de error
                senderBE.sendMessage("ERROR: El cliente ya existe");
            } else {
                // Cliente no existe, guárdalo y envía mensaje de éxito
                clienteRepository.save(cliente);
                senderBE.sendMessage("CORRECT");
            }
        } catch (Exception e) {
            e.printStackTrace();
            senderBE.sendMessage("ERROR: " + e.getMessage());
        }
    }

}
