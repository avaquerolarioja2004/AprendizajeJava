package com.example.ej2SpringWeb.controladores;

import com.example.ej2SpringWeb.persona.Persona;
import org.springframework.stereotype.Service;

@Service
public class ServicioPersona {
    private Persona persona;
    public Persona crearPersona(String nombre, String poblacion, int edad) {
        this.persona = new Persona(nombre, poblacion, edad);
        return persona;
    }

    public Persona obtenerPersona() {
        return this.persona;
    }
}
