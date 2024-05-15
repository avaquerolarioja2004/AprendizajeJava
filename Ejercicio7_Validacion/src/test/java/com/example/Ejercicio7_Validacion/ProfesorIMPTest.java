package com.example.Ejercicio7_Validacion;

import com.example.Ejercicio7_Validacion.persona.Persona;
import com.example.Ejercicio7_Validacion.persona.services.PersonaRepository;
import com.example.Ejercicio7_Validacion.persona.services.PersonaServiceImpl;
import com.example.Ejercicio7_Validacion.profesor.Profesor;
import com.example.Ejercicio7_Validacion.profesor.dto.ProfesorInPutDTO;
import com.example.Ejercicio7_Validacion.profesor.dto.ProfesorOutPutDTO;
import com.example.Ejercicio7_Validacion.profesor.services.ProfesorRepository;
import com.example.Ejercicio7_Validacion.profesor.services.ProfesorServiceImp;
import com.example.Ejercicio7_Validacion.student.Student;
import com.example.Ejercicio7_Validacion.student.services.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProfesorIMPTest {
    @Mock
    ProfesorRepository profesorRepository;

    @Mock
    PersonaServiceImpl personaService;

    @InjectMocks
    ProfesorServiceImp profesorServiceImp;

    @Test
    public void testAddProfesor() throws Exception {
        ProfesorInPutDTO inputDTO = new ProfesorInPutDTO();
        inputDTO.setId_profesor(1);
        inputDTO.setId_persona(1);

        when(personaService.getPersonaId(1)).thenReturn(new Persona().cambiaFormasOut());
        when(profesorRepository.save(any(Profesor.class))).thenReturn(new Profesor());

        ProfesorOutPutDTO result = profesorServiceImp.addProfesor(inputDTO);

        assertEquals(1, result.getId_profesor());
    }

}
