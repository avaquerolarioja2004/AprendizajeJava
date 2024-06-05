package com.example.proyecto_final.backEmpresa.controller;

import com.example.proyecto_final.commons.entityBus.Autobus;
import com.example.proyecto_final.commons.rabbitConfig.RabbitMQConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SenderBE {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String mensaje) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME_AUTOBUS, mensaje);
        System.out.println("Sent: " + mensaje);
    }
}
