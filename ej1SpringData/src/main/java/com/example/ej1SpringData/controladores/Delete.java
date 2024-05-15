package com.example.ej1SpringData.controladores;

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
public class Delete {
    @Autowired
    private PersonaRepository personaRepository;

    @DeleteMapping("/personaDelete/id/{id}")
    public ResponseEntity deletePersona(@PathVariable("id") long id) {
        Optional<Persona> personaOptional = personaRepository.findById(id);
        if (personaOptional.isPresent()) {
            Persona persona = personaRepository.findById(id).get();
            PersonaOutPut personaOutPut = new PersonaOutPut(persona.getId(), persona.getNombre());
            personaRepository.delete(persona);
            return ResponseEntity.ok(personaOutPut);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
