package com.example.proyecto_final.backWeb.rabbit;

import com.example.proyecto_final.commons.rabbitConfig.RabbitMQConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReciverBW {
    @Autowired
    private ObjectMapper objectMapper;

    public static String mensaje;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME_AUTOBUS)
    public void receiveMessage(String mensaje) {
        System.out.println("Received: " + mensaje);
        this.mensaje = mensaje;
    }
}
