package com.example.ej2SpringWeb.controladores;

import com.example.ej2SpringWeb.ciudad.ListaCiudades;
import com.example.ej2SpringWeb.persona.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/get")
public class Get {
    private ServicioPersona servicioPersona;
    private ListaCiudades listaCiudades;

    @Autowired
    public Get(ServicioPersona servicioPersona, ListaCiudades listaCiudades) {
        this.servicioPersona = servicioPersona;
        this.listaCiudades = listaCiudades;
    }

    @GetMapping("/getPersona")
    @ResponseBody
    public Persona getPersona() {
        Persona p= servicioPersona.obtenerPersona();
        p.setEdad(p.getEdad()*2);
        return p;
    }

    @GetMapping("/getCiudad")
    @ResponseBody
    public String getCiudad() {
        return listaCiudades.verCiudades();
    }
}

