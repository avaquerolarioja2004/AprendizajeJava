package com.example.block13mongodb.persona.services;

import com.example.block13mongodb.persona.Persona;
import com.example.block13mongodb.persona.dto.PersonaInPutDTO;
import com.example.block13mongodb.persona.dto.PersonaOutPutDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonaServiceImpl implements PersonaService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private PersonaReposioryImp personaReposioryImp;

    @Override
    public PersonaOutPutDTO addPersona(PersonaInPutDTO persona) throws Exception {
        Optional<PersonaInPutDTO> personaOptional = Optional.ofNullable(persona);
        if (!personaOptional.isPresent()) {
            throw new Exception("No se ha introducido ninguna persona");
        }

        if (persona.getUsuario() == null || persona.getUsuario().length() >= 10 || persona.getUsuario().length() <= 6) {
            throw new Exception("Usuario no puede ser null | Longitud de usuario no puede ser superior a 10 caracteres ni inferior a 6 caracteres");
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
        mongoTemplate.save(p);
        return p.cambiaFormasOut();
    }

    @Override
    public PersonaOutPutDTO deletePersona(int id) throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("id_persona").is(id));
        Persona persona = mongoTemplate.findOne(query, Persona.class);
        if (persona == null) {
            throw new EntityNotFoundException("No se encontró ninguna persona con id: " + id);
        } else {
            mongoTemplate.remove(persona);
            return persona.cambiaFormasOut();
        }
    }

    @Override
    public PersonaOutPutDTO updatePersona(int id,PersonaInPutDTO personaInPutDTO) throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("id_persona").is(id));
        Persona persona = mongoTemplate.findOne(query, Persona.class);
        if (persona == null) {
            throw new EntityNotFoundException("No se encontró ninguna persona con id: " + id);
        } else {
            mongoTemplate.remove(persona);
            Persona p=personaInPutDTO.cambiaFormasPersona();
            mongoTemplate.save(p);
            return p.cambiaFormasOut();
        }
    }

    @Override
    public PersonaOutPutDTO getPersonaId(int id) throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("id_persona").is(id));
        Persona persona = mongoTemplate.findOne(query, Persona.class);
        if (persona == null) {
            throw new EntityNotFoundException("No se encontró ninguna persona con id: " + id);
        } else {
            return persona.cambiaFormasOut();
        }
    }

    @Override
    public List getPersonaNombre(String nombre) throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(nombre));
        List<Persona> personas = mongoTemplate.find(query, Persona.class);
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
        List<Persona> personas = mongoTemplate.findAll(Persona.class);
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
