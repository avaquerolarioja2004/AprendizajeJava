package com.example.Ejercicio7_Validacion;

import com.example.Ejercicio7_Validacion.asignatura.Asignatura;
import com.example.Ejercicio7_Validacion.errores.EntityNotFoundException;
import com.example.Ejercicio7_Validacion.persona.Persona;
import com.example.Ejercicio7_Validacion.persona.dto.PersonaInPutDTO;
import com.example.Ejercicio7_Validacion.persona.dto.PersonaOutPutDTO;
import com.example.Ejercicio7_Validacion.persona.services.PersonaReposioryImp;
import com.example.Ejercicio7_Validacion.persona.services.PersonaRepository;
import com.example.Ejercicio7_Validacion.persona.services.PersonaServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonaIMPTest {

    @Mock
    private PersonaRepository personaRepository;

    @Mock
    private PersonaReposioryImp personaReposioryImp;

    @InjectMocks
    private PersonaServiceImpl personaService;

    @Test
    public void testAddPersona() throws Exception {
        Persona p = new Persona();
        p.setId_persona(1);
        p.setUsuario("usuario");
        p.setPassword("password");
        p.setName("nombre");
        p.setCompany_email("empresa@example.com");
        p.setPersonal_email("personal@example.com");
        p.setCity("ciudad");
        p.setActive(true);
        p.setCreated_date(new Date());
        p.setImagen_url("imagen_url");
        p.setTermination_date(null);

        PersonaInPutDTO inputDTO = new PersonaInPutDTO();
        inputDTO.setUsuario("usuario");
        inputDTO.setPassword("password");
        inputDTO.setName("nombre");
        inputDTO.setCompany_email("empresa@example.com");
        inputDTO.setPersonal_email("personal@example.com");
        inputDTO.setCity("ciudad");
        inputDTO.setActive(true);
        inputDTO.setCreated_date(new Date());
        inputDTO.setImagen_url("imagen_url");
        inputDTO.setTermination_date(null);

        when(personaRepository.save(any(Persona.class))).thenReturn(p);
        PersonaOutPutDTO result = personaService.addPersona(inputDTO);
        assertEquals(1, result.getId());
    }

    @Test
    public void testDeletePersona() throws Exception {
        int idToDelete = 1;
        Persona p = new Persona();
        p.setId_persona(idToDelete);
        when(personaRepository.findById(idToDelete)).thenReturn(Optional.of(p));
        PersonaOutPutDTO result = personaService.deletePersona(idToDelete);
        assertEquals(idToDelete, result.getId());
    }

    @Test
    public void testUpdatePersona() throws Exception {
        int idToUpdate = 1;
        PersonaInPutDTO inputDTO = new PersonaInPutDTO();
        inputDTO.setUsuario("usuario");
        inputDTO.setPassword("password");
        inputDTO.setName("nombre");
        inputDTO.setCompany_email("empresa@example.com");
        inputDTO.setPersonal_email("personal@example.com");
        inputDTO.setCity("ciudad");
        inputDTO.setActive(true);
        inputDTO.setCreated_date(new Date());
        inputDTO.setImagen_url("imagen_url");
        inputDTO.setTermination_date(null);

        when(personaRepository.findById(idToUpdate)).thenReturn(Optional.of(new Persona()));
        PersonaOutPutDTO result = personaService.updatePersona(idToUpdate, inputDTO);
        assertEquals(idToUpdate, result.getId());
    }

    @Test
    public void testGetPersonaId() throws Exception {
        int idToGet = 1;
        Persona p = new Persona();
        p.setId_persona(idToGet);
        when(personaRepository.findById(idToGet)).thenReturn(Optional.of(p));
        PersonaOutPutDTO result = personaService.getPersonaId(idToGet);
        assertEquals(idToGet, result.getId());
    }

    @Test
    public void testGetPersonaNombre() throws Exception {
        String nombre = "Nombre";
        List<Persona> personas = new ArrayList<>();
        Persona persona = new Persona();
        persona.setName(nombre);
        personas.add(persona);
        when(personaRepository.findAll()).thenReturn(personas);
        List<PersonaOutPutDTO> result = personaService.getPersonaNombre(nombre);
        assertEquals(1, result.size());
        assertEquals(nombre, result.get(0).getName());
    }

    @Test
    public void testGetPersona() throws Exception {
        ArrayList<Persona> arrayList=new ArrayList<>();
        Persona p = new Persona();
        p.setId_persona(1);
        arrayList.add(p);
        when(personaRepository.findAll()).thenReturn(arrayList);
        List<PersonaOutPutDTO> result = personaService.getPersona();
        assertEquals(1, result.size());
    }

    @Test
    public void testGetPersonaCustomQuery() {
        List<PersonaOutPutDTO> expectedOutput = new ArrayList<>();
        when(personaReposioryImp.getPersonaCustomQuery(any(), any(), any(), anyInt(), anyInt())).thenReturn(expectedOutput);
        List<PersonaOutPutDTO> result = personaService.getPersonaCustomQuery("", "", new HashMap<>(), 1, 10);
        assertEquals(expectedOutput, result);
    }
}
