package com.example.ej1SpringData.controladores;

import com.example.ej1SpringData.persona.input.PersonaInPut;
import com.example.ej1SpringData.persona.output.PersonaOutPut;
import com.example.ej1SpringData.persona.repository.PersonaRepository;
import com.example.ej1SpringData.persona.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/persona")
public class Post {
    @Autowired
    private PersonaRepository personaRepository;
    @PostMapping("/personaPost")
    public PersonaOutPut addPersona(@RequestBody PersonaInPut personaInput) {
        Optional<PersonaInPut> personaOptional = Optional.ofNullable(personaInput);
        if (!personaOptional.isPresent()) {
            throw new RuntimeException("No se ha insertado a la persona");
        }
        Persona p=new Persona(personaInput.getNombre(),personaInput.getApellido(),personaInput.getEdad());
        personaRepository.save(p);
        PersonaOutPut personaOut=new PersonaOutPut(p.getId(),p.getNombre());
        return personaOut;
    }

}
