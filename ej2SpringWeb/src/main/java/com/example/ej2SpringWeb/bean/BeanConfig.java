package com.example.ej2SpringWeb.bean;

import com.example.ej2SpringWeb.persona.Persona;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    @Qualifier("bean1")
    public Persona bean1() {
        Persona persona = new Persona("Persona 1", "Apellidos 1", 20);
        return persona;
    }

    @Bean
    @Qualifier("bean2")
    public Persona bean2() {
        Persona persona = new Persona("Persona 2", "Apellidos 2", 30);
        return persona;
    }

    @Bean
    @Qualifier("bean3")
    public Persona bean3() {
        Persona persona = new Persona("Persona 3", "Apellidos 3", 40);
        return persona;
    }
}