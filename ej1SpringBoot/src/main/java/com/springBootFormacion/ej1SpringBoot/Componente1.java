package com.springBootFormacion.ej1SpringBoot;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class Componente1 {
    @PostConstruct
    public void info() {
        System.out.println("Hola desde clase inicial");
    }
}
