package com.example.ej1SpringData.controladores;

import com.example.ej1SpringData.persona.input.PersonaInPut;
import com.example.ej1SpringData.persona.output.PersonaOutPut;
import com.example.ej1SpringData.persona.repository.PersonaRepository;
import com.example.ej1SpringData.persona.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/persona")
public class Put {
    @Autowired
    private PersonaRepository personaRepository;

    @PutMapping("/personaPut/id/{id}")
    public ResponseEntity updatePersona(@PathVariable(value="id") long id, @RequestBody PersonaInPut personaDetails) {
        Optional<Persona> personaOptional = personaRepository.findById(id);
        if(!personaOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Persona persona = personaRepository.findById(id).get();
        if(personaDetails.getNombre() == null||personaDetails.getApellido() == null||personaDetails.getEdad() == 0) {
            return ResponseEntity.badRequest().build();
        }else {
            persona.setNombre(personaDetails.getNombre());
            persona.setApellido(personaDetails.getApellido());
            persona.setEdad(personaDetails.getEdad());
        }
        personaRepository.save(persona);
        PersonaOutPut personaOutPut = new PersonaOutPut(persona.getId(), persona.getNombre());
        return ResponseEntity.ok(personaOutPut);
    }

}
