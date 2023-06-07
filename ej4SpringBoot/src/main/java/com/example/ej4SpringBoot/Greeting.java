package com.example.ej4SpringBoot;

public class Greeting {
    private final long id;
    private final String nombre;

    public Greeting(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
