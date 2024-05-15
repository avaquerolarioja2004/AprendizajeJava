package com.example.Ejercicio7_Validacion.unitarios;


import com.example.Ejercicio7_Validacion.persona.Persona;
import com.example.Ejercicio7_Validacion.persona.dto.PersonaInPutDTO;
import com.example.Ejercicio7_Validacion.persona.dto.PersonaOutPutDTO;
import com.example.Ejercicio7_Validacion.persona.services.PersonaRepository;
import com.example.Ejercicio7_Validacion.persona.services.PersonaServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // I'm using this because I want the function starting to not be  static
@Slf4j
public class funcionales {
    public Persona p;
    public PersonaInPutDTO pIn;
    public PersonaOutPutDTO pOut;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private PersonaServiceImpl personaService;

    @BeforeEach
    void setUp() {
        personaRepository.deleteAll();
    }

    @BeforeAll
    public  void starting()
    {
        this.pIn = new PersonaInPutDTO();
        pIn.setUsuario("johndoe");
        pIn.setPassword("password123");
        pIn.setName("John");
        pIn.setSurname("Doe");
        pIn.setCompany_email("john.doe@example.com");
        pIn.setPersonal_email("john.doe@gmail.com");
        pIn.setCity("New York");
        pIn.setActive(true);
        pIn.setCreated_date(new Date());
        pIn.setImagen_url("http://example.com/johndoe.jpg");
        pIn.setTermination_date(null);

        this.pOut=pIn.cambiaFormasPersona().cambiaFormasOut();
    }

    @Test
    void testAddDellPersona() throws Exception {
        PersonaOutPutDTO result = personaService.addPersona(pIn);
        PersonaOutPutDTO resultDel=personaService.deletePersona(result.getId());
        Persona resultFind=personaRepository.findById(resultDel.getId()).orElse(null);
        assertEquals(null, resultFind);
        pOut.setId(2);
        assertEquals(pOut, result);
    }

    @Test
    void testUpdatePersona() throws Exception {
        PersonaOutPutDTO add = personaService.addPersona(pIn);
        PersonaInPutDTO update = new PersonaInPutDTO();
        update.setUsuario("candela");
        update.setPassword("password123");
        update.setName("Candela");
        update.setSurname("Inza");
        update.setCompany_email("john.doe@example.com");
        update.setPersonal_email("john.doe@gmail.com");
        update.setCity("Logrono");
        update.setActive(true);
        update.setCreated_date(new Date());
        update.setImagen_url("http://example.com/johndoe.jpg");
        update.setTermination_date(null);

        PersonaOutPutDTO result = new PersonaOutPutDTO();
        result.setUsuario("candela");
        result.setPassword("password123");
        result.setName("Candela");
        result.setSurname("Inza");
        result.setCompany_email("john.doe@example.com");
        result.setPersonal_email("john.doe@gmail.com");
        result.setCity("Logrono");
        result.setActive(true);
        result.setCreated_date(new Date());
        result.setImagen_url("http://example.com/johndoe.jpg");
        result.setTermination_date(null);
        result.setId(1);
        PersonaOutPutDTO resultReal=personaService.updatePersona(1,update);
        assertEquals(result, resultReal);
    }

    @Test
    void testGetPersonaId() throws Exception {
        PersonaOutPutDTO addedPersona = personaService.addPersona(pIn);
        PersonaOutPutDTO result = personaService.getPersonaId(addedPersona.getId());
        assertNotNull(result);
        assertEquals(addedPersona, result);
    }

    @Test
    void testGetPersonaNombre() throws Exception {
        personaService.addPersona(pIn);
        List<PersonaOutPutDTO> result = personaService.getPersonaNombre("John");
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }




}
