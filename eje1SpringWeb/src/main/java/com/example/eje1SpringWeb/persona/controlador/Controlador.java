package com.example.eje1SpringWeb.persona.controlador;

import com.example.eje1SpringWeb.persona.Persona;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class Controlador {

    @ResponseBody
    @GetMapping("/user/nombre/{nombre}")
    public String saludar(@PathVariable String nombre){
        return "Hola " + nombre;
    }

    @ResponseBody
    @PostMapping("/useradd")
    public Persona persona(@RequestBody Persona usuario){
        Persona p=new Persona(usuario.getNombre(), usuario.getApellido(),usuario.getEdad()+1);
        return p;
    }


}
