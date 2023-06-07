package com.example.ej2SpringWeb.ciudad;

public class Ciudad {
    private String nombre;
    private int habitantes;
    public Ciudad(String nombre, int habitantes) {
        this.nombre = nombre;
        this.habitantes = habitantes;
    }

    public String getNombre() {
        return nombre;
    }
    public int getHabitantes() {
        return habitantes;
    }
}
