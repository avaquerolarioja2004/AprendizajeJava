package com.example.block13mongodb.persona.services;


import com.example.block13mongodb.persona.dto.PersonaInPutDTO;
import com.example.block13mongodb.persona.dto.PersonaOutPutDTO;

import java.util.HashMap;
import java.util.List;
public interface PersonaService {
    public PersonaOutPutDTO addPersona(PersonaInPutDTO persona) throws Exception;
    public PersonaOutPutDTO deletePersona(int id) throws Exception;
    public PersonaOutPutDTO updatePersona(int id,PersonaInPutDTO persona) throws Exception;
    public PersonaOutPutDTO getPersonaId(int id) throws Exception;
    public List getPersonaNombre(String nombre) throws Exception;
    public List getPersona() throws Exception;
    public List getPersonaCustomQuery(String ordenar,String paramOrdenar, HashMap<String, Object> parametros, int pagina, int cantidad) throws Exception;
}
