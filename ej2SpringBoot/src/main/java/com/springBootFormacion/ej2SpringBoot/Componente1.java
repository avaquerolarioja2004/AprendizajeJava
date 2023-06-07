package com.springBootFormacion.ej2SpringBoot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Componente1 {
    @Value("${greeting}")
    private String greeting;
    @Value("${my.number}")
    private String number;
    @Value("${new.property:No existe new.property}")
    private String property;

    public String getGreeting() {
        return greeting;
    }
    public String getNumber() {
        return number;
    }
    public String getProperty() {
        if (!property.equals("")) {
            return property;
        } else {
            return "No existe new.property";
        }
    }

}
