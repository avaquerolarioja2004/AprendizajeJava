package com.example.proyecto_final.commons.rabbitConfig;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME_RESERVA = "reserva_queue";
    public static final String QUEUE_NAME_CLIENTE = "cliente_queue";
    public static final String QUEUE_NAME_AUTOBUS = "autobus_queue";
    public static final String QUEUE_NAME_GET_BBDD = "get_bbdd_queue";

    @Bean
    public Queue intQueue() {
        return new Queue(QUEUE_NAME_AUTOBUS, false);
    }
    @Bean
    public Queue reservaQueue() {
        return new Queue(QUEUE_NAME_RESERVA, false);
    }
    @Bean
    public Queue clienteQueue() {
        return new Queue(QUEUE_NAME_CLIENTE, false);
    }
    @Bean
    public Queue bbddQueue() {return new Queue(QUEUE_NAME_GET_BBDD, false);}
}
