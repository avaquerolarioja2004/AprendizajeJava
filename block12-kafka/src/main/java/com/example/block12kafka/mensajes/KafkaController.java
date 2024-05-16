package com.example.block12kafka.mensajes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    @Autowired
    private KafkaProducerService producerService;

    @PostMapping("/send-message")
    public void sendMessageToKafka(@RequestBody String message) {
        producerService.sendMessage(message);
    }
}

