package com.example.ej1SpringData.controladores;

import com.example.ej1SpringData.persona.output.PersonaOutPut;
import com.example.ej1SpringData.persona.repository.PersonaRepository;
import com.example.ej1SpringData.persona.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persona")
public class Get {
    @Autowired
    private PersonaRepository personaRepository;

    @GetMapping("/personaGet")
    public List getPersona() {
        List<Persona> personas = personaRepository.findAll();
        List<PersonaOutPut> personasOut = new ArrayList<>();
        for (Persona persona : personas) {
            PersonaOutPut personaOutPut = new PersonaOutPut(persona.getId(), persona.getNombre());
            personasOut.add(personaOutPut);
        }
        if (personasOut.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return personasOut;
    }

    @GetMapping("/personaGet/id/{id}")
    public ResponseEntity getPersonaId(@PathVariable(value = "id") long id) {
        Optional<Persona> personaOptional = personaRepository.findById(id);
        if (personaOptional.isPresent()) {
            PersonaOutPut personaOut = new PersonaOutPut(personaOptional.get().getId(), personaOptional.get().getNombre());
            return ResponseEntity.ok(personaOut);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/personaGet/nombre/{nombre}")
    public List getPersonaNombre(@PathVariable(value = "nombre") String nombre) {
        List<Persona> personas = personaRepository.findAll();
        List<PersonaOutPut> personasNombre = new ArrayList<>();
        for (Persona persona : personas) {
            if (persona.getNombre().equals(nombre)) {
                PersonaOutPut personaOutPut = new PersonaOutPut(persona.getId(), persona.getNombre());
                personasNombre.add(personaOutPut);
            }
        }
        if (personasNombre.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return personasNombre;
    }
}
