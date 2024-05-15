package com.springBootFormacion.ej2SpringBoot;
@org.springframework.stereotype.Controller
public class Controller {
    private Componente1 componente1;
    public Controller(Componente1 componente1) {
        this.componente1 = componente1;
        System.out.println("El valor de my.number es: "+componente1.getNumber());
        System.out.println("El valor de greeting es: "+componente1.getGreeting());
        System.out.println("El valor de new.property es: ++-+"+componente1.getProperty());
    }
}
