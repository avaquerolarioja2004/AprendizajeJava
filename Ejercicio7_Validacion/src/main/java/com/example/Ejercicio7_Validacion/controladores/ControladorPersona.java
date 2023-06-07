package com.example.Ejercicio7_Validacion.controladores;

import com.example.Ejercicio7_Validacion.persona.dto.PersonaInPutDTO;
import com.example.Ejercicio7_Validacion.persona.dto.PersonaOutPutDTO;
import com.example.Ejercicio7_Validacion.persona.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/persona")
public class ControladorPersona {

    @Autowired
    PersonaService personaService;

    @GetMapping("{id}")
    public PersonaOutPutDTO getPersonaId(@PathVariable(value = "id") int id) throws Exception {
        return personaService.getPersonaId(id);
    }

    @GetMapping("nombre/{nombre}")
    public List getPersonaNombre(@PathVariable String nombre) throws Exception {
        return personaService.getPersonaNombre(nombre);
    }

    @GetMapping
    public List getPersona() throws Exception {
        return personaService.getPersona();
    }

    @PostMapping
    public PersonaOutPutDTO addPersona(@RequestBody PersonaInPutDTO persona) throws Exception {
        return personaService.addPersona(persona);
    }

    @DeleteMapping("{id}")
    public PersonaOutPutDTO deletePersona(@PathVariable int id) throws Exception {
        return personaService.deletePersona(id);
    }

    @PutMapping("{id}")
    public PersonaOutPutDTO updatePersona(@PathVariable int id, @RequestBody PersonaInPutDTO persona) throws Exception {
        return personaService.updatePersona(id, persona);
    }

}
