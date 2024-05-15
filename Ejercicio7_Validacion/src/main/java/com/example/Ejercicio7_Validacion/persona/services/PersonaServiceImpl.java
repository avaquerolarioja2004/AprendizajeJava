package com.example.Ejercicio7_Validacion.persona.services;

import com.example.Ejercicio7_Validacion.errores.EntityNotFoundException;
import com.example.Ejercicio7_Validacion.errores.UnprocessableEntityException;
import com.example.Ejercicio7_Validacion.persona.Persona;
import com.example.Ejercicio7_Validacion.persona.dto.PersonaInPutDTO;
import com.example.Ejercicio7_Validacion.persona.dto.PersonaOutPutDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonaServiceImpl implements PersonaService{
    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private PersonaReposioryImp personaReposioryImp;

    @Override
    public PersonaOutPutDTO addPersona(PersonaInPutDTO persona) throws Exception {
        Optional<PersonaInPutDTO> personaOptional = Optional.ofNullable(persona);
        if (!personaOptional.isPresent()) {
            throw new UnprocessableEntityException("No se ha introducido ninguna persona");
        }

        if (persona.getUsuario() == null || persona.getUsuario().length() >= 10 || persona.getUsuario().length() <= 6) {
            throw new UnprocessableEntityException("Usuario no puede ser null | Longitud de usuario no puede ser superior a 10 caracteres ni inferior a 6 caracteres");
        }
        if (persona.getPassword() == null) {
            throw new EntityNotFoundException("Password no puede ser null");
        }
        if (persona.getCompany_email() == null) {
            throw new EntityNotFoundException("El email de la empresa no puede ser null");
        }
        if (persona.getPersonal_email() == null) {
            throw new EntityNotFoundException("El email personal no puede ser null");
        }
        if (persona.getCity() == null) {
            throw new EntityNotFoundException("Ciudad no puede ser null");
        }
        if (persona.getActive() == null) {
            throw new EntityNotFoundException("El campo active no puede ser null");
        }
        if (persona.getCreated_date() == null) {
            throw new EntityNotFoundException("El campo created_date no puede ser null");
        }
        if (persona.getName() == null) {
            throw new EntityNotFoundException("El campo name no puede ser null");
        }

        Persona p = persona.cambiaFormasPersona();
        personaRepository.save(p);
        return p.cambiaFormasOut();
    }

    @Override
    public PersonaOutPutDTO deletePersona(int id) throws Exception {
        Optional<Persona> personaOptional = personaRepository.findById(id);
        if (!personaOptional.isPresent()) {
            throw new EntityNotFoundException("No se encontró ninguna persona con id: " + id);
        } else {
            Persona persona = personaOptional.get();
            personaRepository.delete(persona);
            return persona.cambiaFormasOut();
        }
    }

    @Override
    public PersonaOutPutDTO updatePersona(int id,PersonaInPutDTO personaInPutDTO) throws Exception {
        Optional<Persona> personaOptional = personaRepository.findById(id);
        if (!personaOptional.isPresent()) {
            throw new EntityNotFoundException("No se encontró ninguna persona con id: " + id);
        } else {
            Persona p = personaInPutDTO.cambiaFormasPersona();
            p.setId_persona(id);
            personaRepository.save(p);
            return p.cambiaFormasOut();
        }
    }

    @Override
    public PersonaOutPutDTO getPersonaId(int id) throws Exception {
        Optional<Persona> personaOptional = personaRepository.findById(id);
        if (!personaOptional.isPresent()) {
            throw new EntityNotFoundException("No se encontró ninguna persona con id: " + id);
        } else {
            Persona persona = personaOptional.get();
            return persona.cambiaFormasOut();
        }
    }

    @Override
    public List getPersonaNombre(String nombre) throws Exception {
        List<Persona> personas = personaRepository.findAll();
        List<PersonaOutPutDTO> personasEncontradas = new ArrayList<>();
        for (Persona persona : personas) {
            if (persona.getName().equals(nombre)) {
                PersonaOutPutDTO p = persona.cambiaFormasOut();
                personasEncontradas.add(p);
            }
        }
        if (personasEncontradas.isEmpty()) {
            throw new EntityNotFoundException("No se encontró ninguna persona con nombre: " + nombre);
        } else {
            return personasEncontradas;
        }
    }

    @Override
    public List getPersona() throws Exception {
        List<Persona> personas = personaRepository.findAll();
        List<PersonaOutPutDTO> personasEncontradas = new ArrayList<>();
        for (Persona persona : personas) {
            PersonaOutPutDTO p = persona.cambiaFormasOut();
            personasEncontradas.add(p);
        }
        if (personasEncontradas.isEmpty()) {
            throw new EntityNotFoundException("No se encontró ninguna persona");
        } else {
            return personasEncontradas;
        }
    }

    @Override
    public List getPersonaCustomQuery(String ordenar, String paramOrdenar, HashMap<String, Object> parametros, int pagina, int limite) {
        return personaReposioryImp.getPersonaCustomQuery(ordenar, paramOrdenar, parametros, pagina, limite);
    }
}
