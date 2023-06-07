package org.example;

public class Persona {
    private String nombre;
    private String ciudad;
    private int edad;

    public Persona(String name, String town , int age) {
        this.nombre = name;
        this.ciudad = town;
        this.edad = age;
    }

    public Persona(String name) {
        this.nombre = name;
        this.ciudad = "*";
        this.edad = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String name) {
        this.nombre = name;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String town) {
        this.ciudad = town;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int age) {
        this.edad = age;
    }

    @Override
    public String toString() {
        return  "Nombre:" + nombre + " Ciudad:" + ciudad + " Edad:" + edad;
    }


}

