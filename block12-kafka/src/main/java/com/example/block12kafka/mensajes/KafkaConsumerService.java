package com.example.block12kafka.mensajes;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "factura", groupId = "my-group-id")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }
}

