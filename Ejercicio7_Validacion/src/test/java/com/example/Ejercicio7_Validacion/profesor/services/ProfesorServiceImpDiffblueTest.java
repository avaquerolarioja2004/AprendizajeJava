package com.example.Ejercicio7_Validacion.profesor.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.Ejercicio7_Validacion.persona.Persona;
import com.example.Ejercicio7_Validacion.persona.dto.PersonaOutPutDTO;
import com.example.Ejercicio7_Validacion.persona.services.PersonaService;
import com.example.Ejercicio7_Validacion.profesor.Profesor;
import com.example.Ejercicio7_Validacion.profesor.dto.ProfesorOutPutDTO;
import com.example.Ejercicio7_Validacion.student.Student;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProfesorServiceImp.class})
@ExtendWith(SpringExtension.class)
class ProfesorServiceImpDiffblueTest {
    @MockBean
    private PersonaService personaService;

    @MockBean
    private ProfesorRepository profesorRepository;

    @Autowired
    private ProfesorServiceImp profesorServiceImp;

    /**
     * Method under test: {@link ProfesorServiceImp#deleteProfesor(int)}
     */
    @Test
    void testDeleteProfesor() throws Exception {
        // Arrange
        when(personaService.getPersonaId(anyInt())).thenReturn(new PersonaOutPutDTO());

        Persona id_persona = new Persona();
        id_persona.setActive(true);
        id_persona.setCity("Oxford");
        id_persona.setCompany_email("jane.doe@example.org");
        id_persona.setCreated_date(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        id_persona.setId_persona(1);
        id_persona.setImagen_url("https://example.org/example");
        id_persona.setName("Name");
        id_persona.setPassword("iloveyou");
        id_persona.setPersonal_email("jane.doe@example.org");
        id_persona.setProfesor(new Profesor());
        id_persona.setStudent(new Student());
        id_persona.setSurname("Doe");
        id_persona
                .setTermination_date(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        id_persona.setUsuario("Usuario");

        Profesor profesor = new Profesor();
        profesor.setBranch("janedoe/featurebranch");
        profesor.setComents("Coments");
        profesor.setId_persona(id_persona);
        profesor.setId_profesor(1);
        profesor.setId_student(new HashSet<>());

        Persona persona = new Persona();
        persona.setActive(true);
        persona.setCity("Oxford");
        persona.setCompany_email("jane.doe@example.org");
        persona.setCreated_date(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        persona.setId_persona(1);
        persona.setImagen_url("https://example.org/example");
        persona.setName("Name");
        persona.setPassword("iloveyou");
        persona.setPersonal_email("jane.doe@example.org");
        persona.setProfesor(new Profesor());
        persona.setStudent(new Student());
        persona.setSurname("Doe");
        persona.setTermination_date(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        persona.setUsuario("Usuario");

        Profesor profesor2 = new Profesor();
        profesor2.setBranch("janedoe/featurebranch");
        profesor2.setComents("Coments");
        profesor2.setId_persona(new Persona());
        profesor2.setId_profesor(1);
        profesor2.setId_student(new HashSet<>());

        Student student = new Student();
        student.setAsignaturas(new ArrayList<>());
        student.setBranch("janedoe/featurebranch");
        student.setComents("Coments");
        student.setId_student(1);
        student.setNum_hours_week(10);
        student.setPersona(persona);
        student.setProfesor(profesor2);

        Persona id_persona2 = new Persona();
        id_persona2.setActive(true);
        id_persona2.setCity("Oxford");
        id_persona2.setCompany_email("jane.doe@example.org");
        id_persona2.setCreated_date(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        id_persona2.setId_persona(1);
        id_persona2.setImagen_url("https://example.org/example");
        id_persona2.setName("Name");
        id_persona2.setPassword("iloveyou");
        id_persona2.setPersonal_email("jane.doe@example.org");
        id_persona2.setProfesor(profesor);
        id_persona2.setStudent(student);
        id_persona2.setSurname("Doe");
        id_persona2
                .setTermination_date(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        id_persona2.setUsuario("Usuario");

        Profesor profesor3 = new Profesor();
        profesor3.setBranch("janedoe/featurebranch");
        profesor3.setComents("Coments");
        profesor3.setId_persona(id_persona2);
        profesor3.setId_profesor(1);
        profesor3.setId_student(new HashSet<>());
        Optional<Profesor> ofResult = Optional.of(profesor3);
        doNothing().when(profesorRepository).delete(Mockito.<Profesor>any());
        when(profesorRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        ProfesorOutPutDTO actualDeleteProfesorResult = profesorServiceImp.deleteProfesor(1);

        // Assert
        verify(personaService).getPersonaId(eq(1));
        verify(profesorRepository).delete(isA(Profesor.class));
        verify(profesorRepository).findById(eq(1));
        assertEquals("Coments", actualDeleteProfesorResult.getComents());
        assertEquals("janedoe/featurebranch", actualDeleteProfesorResult.getBranch());
        assertNull(actualDeleteProfesorResult.getId_persona().getActive());
        assertEquals(1, actualDeleteProfesorResult.getId_profesor());
    }

    /**
     * Method under test: {@link ProfesorServiceImp#deleteProfesor(int)}
     */
    @Test
    void testDeleteProfesor2() throws Exception {
        // Arrange
        when(personaService.getPersonaId(anyInt())).thenReturn(new PersonaOutPutDTO());

        Persona id_persona = new Persona();
        id_persona.setActive(true);
        id_persona.setCity("Oxford");
        id_persona.setCompany_email("jane.doe@example.org");
        id_persona.setCreated_date(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        id_persona.setId_persona(1);
        id_persona.setImagen_url("https://example.org/example");
        id_persona.setName("Name");
        id_persona.setPassword("iloveyou");
        id_persona.setPersonal_email("jane.doe@example.org");
        id_persona.setProfesor(new Profesor());
        id_persona.setStudent(new Student());
        id_persona.setSurname("Doe");
        id_persona
                .setTermination_date(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        id_persona.setUsuario("Usuario");

        Profesor profesor = new Profesor();
        profesor.setBranch("janedoe/featurebranch");
        profesor.setComents("Coments");
        profesor.setId_persona(id_persona);
        profesor.setId_profesor(1);
        profesor.setId_student(new HashSet<>());

        Persona persona = new Persona();
        persona.setActive(true);
        persona.setCity("Oxford");
        persona.setCompany_email("jane.doe@example.org");
        persona.setCreated_date(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        persona.setId_persona(1);
        persona.setImagen_url("https://example.org/example");
        persona.setName("Name");
        persona.setPassword("iloveyou");
        persona.setPersonal_email("jane.doe@example.org");
        persona.setProfesor(new Profesor());
        persona.setStudent(new Student());
        persona.setSurname("Doe");
        persona.setTermination_date(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        persona.setUsuario("Usuario");

        Profesor profesor2 = new Profesor();
        profesor2.setBranch("janedoe/featurebranch");
        profesor2.setComents("Coments");
        profesor2.setId_persona(new Persona());
        profesor2.setId_profesor(1);
        profesor2.setId_student(new HashSet<>());

        Student student = new Student();
        student.setAsignaturas(new ArrayList<>());
        student.setBranch("janedoe/featurebranch");
        student.setComents("Coments");
        student.setId_student(1);
        student.setNum_hours_week(10);
        student.setPersona(persona);
        student.setProfesor(profesor2);

        Persona id_persona2 = new Persona();
        id_persona2.setActive(true);
        id_persona2.setCity("Oxford");
        id_persona2.setCompany_email("jane.doe@example.org");
        id_persona2.setCreated_date(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        id_persona2.setId_persona(1);
        id_persona2.setImagen_url("https://example.org/example");
        id_persona2.setName("Name");
        id_persona2.setPassword("iloveyou");
        id_persona2.setPersonal_email("jane.doe@example.org");
        id_persona2.setProfesor(profesor);
        id_persona2.setStudent(student);
        id_persona2.setSurname("Doe");
        id_persona2
                .setTermination_date(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        id_persona2.setUsuario("Usuario");

        Persona id_persona3 = new Persona();
        id_persona3.setActive(true);
        id_persona3.setCity("Oxford");
        id_persona3.setCompany_email("jane.doe@example.org");
        id_persona3.setCreated_date(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        id_persona3.setId_persona(1);
        id_persona3.setImagen_url("https://example.org/example");
        id_persona3.setName("Name");
        id_persona3.setPassword("iloveyou");
        id_persona3.setPersonal_email("jane.doe@example.org");
        id_persona3.setProfesor(new Profesor());
        id_persona3.setStudent(new Student());
        id_persona3.setSurname("Doe");
        id_persona3
                .setTermination_date(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        id_persona3.setUsuario("Usuario");

        Profesor profesor3 = new Profesor();
        profesor3.setBranch("janedoe/featurebranch");
        profesor3.setComents("Coments");
        profesor3.setId_persona(id_persona3);
        profesor3.setId_profesor(1);
        profesor3.setId_student(new HashSet<>());

        Persona persona2 = new Persona();
        persona2.setActive(true);
        persona2.setCity("Oxford");
        persona2.setCompany_email("jane.doe@example.org");
        persona2.setCreated_date(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        persona2.setId_persona(1);
        persona2.setImagen_url("https://example.org/example");
        persona2.setName("Name");
        persona2.setPassword("iloveyou");
        persona2.setPersonal_email("jane.doe@example.org");
        persona2.setProfesor(new Profesor());
        persona2.setStudent(new Student());
        persona2.setSurname("Doe");
        persona2.setTermination_date(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        persona2.setUsuario("Usuario");

        Profesor profesor4 = new Profesor();
        profesor4.setBranch("janedoe/featurebranch");
        profesor4.setComents("Coments");
        profesor4.setId_persona(new Persona());
        profesor4.setId_profesor(1);
        profesor4.setId_student(new HashSet<>());

        Student student2 = new Student();
        student2.setAsignaturas(new ArrayList<>());
        student2.setBranch("janedoe/featurebranch");
        student2.setComents("Coments");
        student2.setId_student(1);
        student2.setNum_hours_week(10);
        student2.setPersona(persona2);
        student2.setProfesor(profesor4);

        Persona id_persona4 = new Persona();
        id_persona4.setActive(true);
        id_persona4.setCity("Oxford");
        id_persona4.setCompany_email("jane.doe@example.org");
        id_persona4.setCreated_date(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        id_persona4.setId_persona(1);
        id_persona4.setImagen_url("https://example.org/example");
        id_persona4.setName("Name");
        id_persona4.setPassword("iloveyou");
        id_persona4.setPersonal_email("jane.doe@example.org");
        id_persona4.setProfesor(profesor3);
        id_persona4.setStudent(student2);
        id_persona4.setSurname("Doe");
        id_persona4
                .setTermination_date(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        id_persona4.setUsuario("Usuario");

        Profesor profesor5 = new Profesor();
        profesor5.setBranch("janedoe/featurebranch");
        profesor5.setComents("Coments");
        profesor5.setId_persona(id_persona4);
        profesor5.setId_profesor(1);
        profesor5.setId_student(new HashSet<>());

        Persona id_persona5 = new Persona();
        id_persona5.setActive(true);
        id_persona5.setCity("Oxford");
        id_persona5.setCompany_email("jane.doe@example.org");
        id_persona5.setCreated_date(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        id_persona5.setId_persona(1);
        id_persona5.setImagen_url("https://example.org/example");
        id_persona5.setName("Name");
        id_persona5.setPassword("iloveyou");
        id_persona5.setPersonal_email("jane.doe@example.org");
        id_persona5.setProfesor(new Profesor());
        id_persona5.setStudent(new Student());
        id_persona5.setSurname("Doe");
        id_persona5
                .setTermination_date(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        id_persona5.setUsuario("Usuario");

        Profesor profesor6 = new Profesor();
        profesor6.setBranch("janedoe/featurebranch");
        profesor6.setComents("Coments");
        profesor6.setId_persona(id_persona5);
        profesor6.setId_profesor(1);
        profesor6.setId_student(new HashSet<>());

        Persona persona3 = new Persona();
        persona3.setActive(true);
        persona3.setCity("Oxford");
        persona3.setCompany_email("jane.doe@example.org");
        persona3.setCreated_date(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        persona3.setId_persona(1);
        persona3.setImagen_url("https://example.org/example");
        persona3.setName("Name");
        persona3.setPassword("iloveyou");
        persona3.setPersonal_email("jane.doe@example.org");
        persona3.setProfesor(new Profesor());
        persona3.setStudent(new Student());
        persona3.setSurname("Doe");
        persona3.setTermination_date(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        persona3.setUsuario("Usuario");

        Profesor profesor7 = new Profesor();
        profesor7.setBranch("janedoe/featurebranch");
        profesor7.setComents("Coments");
        profesor7.setId_persona(new Persona());
        profesor7.setId_profesor(1);
        profesor7.setId_student(new HashSet<>());

        Student student3 = new Student();
        student3.setAsignaturas(new ArrayList<>());
        student3.setBranch("janedoe/featurebranch");
        student3.setComents("Coments");
        student3.setId_student(1);
        student3.setNum_hours_week(10);
        student3.setPersona(persona3);
        student3.setProfesor(profesor7);

        Persona persona4 = new Persona();
        persona4.setActive(true);
        persona4.setCity("Oxford");
        persona4.setCompany_email("jane.doe@example.org");
        persona4.setCreated_date(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        persona4.setId_persona(1);
        persona4.setImagen_url("https://example.org/example");
        persona4.setName("Name");
        persona4.setPassword("iloveyou");
        persona4.setPersonal_email("jane.doe@example.org");
        persona4.setProfesor(profesor6);
        persona4.setStudent(student3);
        persona4.setSurname("Doe");
        persona4.setTermination_date(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        persona4.setUsuario("Usuario");

        Profesor profesor8 = new Profesor();
        profesor8.setBranch("janedoe/featurebranch");
        profesor8.setComents("Coments");
        profesor8.setId_persona(new Persona());
        profesor8.setId_profesor(1);
        profesor8.setId_student(new HashSet<>());

        Student student4 = new Student();
        student4.setAsignaturas(new ArrayList<>());
        student4.setBranch("janedoe/featurebranch");
        student4.setComents("Coments");
        student4.setId_student(1);
        student4.setNum_hours_week(10);
        student4.setPersona(new Persona());
        student4.setProfesor(new Profesor());

        Persona id_persona6 = new Persona();
        id_persona6.setActive(true);
        id_persona6.setCity("Oxford");
        id_persona6.setCompany_email("jane.doe@example.org");
        id_persona6.setCreated_date(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        id_persona6.setId_persona(1);
        id_persona6.setImagen_url("https://example.org/example");
        id_persona6.setName("Name");
        id_persona6.setPassword("iloveyou");
        id_persona6.setPersonal_email("jane.doe@example.org");
        id_persona6.setProfesor(profesor8);
        id_persona6.setStudent(student4);
        id_persona6.setSurname("Doe");
        id_persona6
                .setTermination_date(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        id_persona6.setUsuario("Usuario");

        Profesor profesor9 = new Profesor();
        profesor9.setBranch("janedoe/featurebranch");
        profesor9.setComents("Coments");
        profesor9.setId_persona(id_persona6);
        profesor9.setId_profesor(1);
        profesor9.setId_student(new HashSet<>());

        Student student5 = new Student();
        student5.setAsignaturas(new ArrayList<>());
        student5.setBranch("janedoe/featurebranch");
        student5.setComents("Coments");
        student5.setId_student(1);
        student5.setNum_hours_week(10);
        student5.setPersona(persona4);
        student5.setProfesor(profesor9);

        Persona persona5 = new Persona();
        persona5.setActive(true);
        persona5.setCity("Oxford");
        persona5.setCompany_email("jane.doe@example.org");
        persona5.setCreated_date(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        persona5.setId_persona(1);
        persona5.setImagen_url("https://example.org/example");
        persona5.setName("Name");
        persona5.setPassword("iloveyou");
        persona5.setPersonal_email("jane.doe@example.org");
        persona5.setProfesor(profesor5);
        persona5.setStudent(student5);
        persona5.setSurname("Doe");
        persona5.setTermination_date(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        persona5.setUsuario("Usuario");
        Profesor profesor10 = mock(Profesor.class);
        when(profesor10.getId_persona()).thenReturn(persona5);
        ProfesorOutPutDTO profesorOutPutDTO = new ProfesorOutPutDTO();
        when(profesor10.cambiaFormasProfesor()).thenReturn(profesorOutPutDTO);
        doNothing().when(profesor10).setBranch(Mockito.<String>any());
        doNothing().when(profesor10).setComents(Mockito.<String>any());
        doNothing().when(profesor10).setId_persona(Mockito.<Persona>any());
        doNothing().when(profesor10).setId_profesor(anyInt());
        doNothing().when(profesor10).setId_student(Mockito.<Set<Student>>any());
        profesor10.setBranch("janedoe/featurebranch");
        profesor10.setComents("Coments");
        profesor10.setId_persona(id_persona2);
        profesor10.setId_profesor(1);
        profesor10.setId_student(new HashSet<>());
        Optional<Profesor> ofResult = Optional.of(profesor10);
        doNothing().when(profesorRepository).delete(Mockito.<Profesor>any());
        when(profesorRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        ProfesorOutPutDTO actualDeleteProfesorResult = profesorServiceImp.deleteProfesor(1);

        // Assert
        verify(personaService).getPersonaId(eq(1));
        verify(profesor10).cambiaFormasProfesor();
        verify(profesor10).getId_persona();
        verify(profesor10).setBranch(eq("janedoe/featurebranch"));
        verify(profesor10).setComents(eq("Coments"));
        verify(profesor10).setId_persona(isA(Persona.class));
        verify(profesor10).setId_profesor(eq(1));
        verify(profesor10).setId_student(isA(Set.class));
        verify(profesorRepository).delete(isA(Profesor.class));
        verify(profesorRepository).findById(eq(1));
        assertNull(actualDeleteProfesorResult.getId_persona().getActive());
        assertSame(profesorOutPutDTO, actualDeleteProfesorResult);
    }
}
