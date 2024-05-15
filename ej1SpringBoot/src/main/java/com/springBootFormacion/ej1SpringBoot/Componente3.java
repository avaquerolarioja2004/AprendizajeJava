package com.springBootFormacion.ej1SpringBoot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Componente3 implements CommandLineRunner{
    @Value("${Componente3}")
    String nombre;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hola desde clase Terciaria, y el valor es: "+nombre);
    }
}
