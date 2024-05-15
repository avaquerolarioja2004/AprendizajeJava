package com.example.ej2SpringWeb.controladores;

import com.example.ej2SpringWeb.ciudad.ListaCiudades;
import com.example.ej2SpringWeb.ciudad.Ciudad;
import com.example.ej2SpringWeb.persona.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/put")
public class Put {
    private ServicioPersona servicioPersona;
    private ListaCiudades listaCiudades;

    @Autowired
    public Put(ServicioPersona servicioPersona, ListaCiudades listaCiudades) {
        this.servicioPersona = servicioPersona;
        this.listaCiudades = listaCiudades;
    }

    @GetMapping("/addPersona")
    public Persona addPersona(
            @RequestHeader("nombre") String nombre,
            @RequestHeader("apellidos") String apellidos,
            @RequestHeader("edad") int edad
    ) {
        Persona persona = servicioPersona.crearPersona(nombre, apellidos, edad);
        return persona;
    }

    @PostMapping("/addCiudad")
    public String addCiudad(@RequestBody Ciudad ciudad) {
        listaCiudades.addCiudad(ciudad);
        return listaCiudades.verCiudades();
    }
}

