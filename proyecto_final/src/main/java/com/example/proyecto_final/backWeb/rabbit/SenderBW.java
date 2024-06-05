package com.example.proyecto_final.backWeb.rabbit;

import com.example.proyecto_final.commons.entityCliente.ClienteInPut;
import com.example.proyecto_final.commons.entityReserva.Reserva;
import com.example.proyecto_final.commons.entityReserva.ReservaInPut;
import com.example.proyecto_final.commons.rabbitConfig.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SenderBW {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendMessage(ReservaInPut reserva) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(reserva);
            rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME_RESERVA, jsonMessage);
            System.out.println("Sent: " + jsonMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void sendMessageCliente(ClienteInPut cliente) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(cliente);
            rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME_CLIENTE, jsonMessage);
            System.out.println("Sent: " + jsonMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
