package com.example.Ejercicio7_Validacion.persona.services;

import com.example.Ejercicio7_Validacion.persona.dto.PersonaInPutDTO;
import com.example.Ejercicio7_Validacion.persona.dto.PersonaOutPutDTO;
import org.springframework.stereotype.Service;

import java.util.List;
public interface PersonaService {
    public PersonaOutPutDTO addPersona(PersonaInPutDTO persona) throws Exception;
    public PersonaOutPutDTO deletePersona(int id) throws Exception;
    public PersonaOutPutDTO updatePersona(int id,PersonaInPutDTO persona) throws Exception;
    public PersonaOutPutDTO getPersonaId(int id) throws Exception;
    public List getPersonaNombre(String nombre) throws Exception;
    public List getPersona() throws Exception;
}
