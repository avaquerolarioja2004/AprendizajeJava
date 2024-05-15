package com.example.Ejercicio7_Validacion;

import com.example.Ejercicio7_Validacion.asignatura.Asignatura;
import com.example.Ejercicio7_Validacion.asignatura.dto.AsignaturaInPutDTO;
import com.example.Ejercicio7_Validacion.asignatura.dto.AsignaturaOutPutFullDTO;
import com.example.Ejercicio7_Validacion.asignatura.dto.AsignaturaOutPutSimpleDTO;
import com.example.Ejercicio7_Validacion.asignatura.services.AsignaturaRepository;
import com.example.Ejercicio7_Validacion.asignatura.services.AsignaturaServiceImp;
import com.example.Ejercicio7_Validacion.persona.Persona;
import com.example.Ejercicio7_Validacion.profesor.Profesor;
import com.example.Ejercicio7_Validacion.student.Student;
import com.example.Ejercicio7_Validacion.student.services.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class) // Si no se pone esta etiqueta hay que poner la funcion setUp que esta comentada en este ejemplo.

class AsignaturasIMPTests {

	@Mock
	private AsignaturaRepository asignaturaRepository;

	@Mock
	private StudentRepository studentRepository;

	@InjectMocks
	private AsignaturaServiceImp asignaturaService;

	@Test
	void addAsignaturaTest() throws Exception {
		// Arrange
		AsignaturaInPutDTO inputDTO = new AsignaturaInPutDTO();

		inputDTO.setAsignaturaNombre("Math");
		inputDTO.setComments("Basic Math");
		inputDTO.setInitialDate(new Date());
		inputDTO.setFinishDate(new Date());
		Asignatura asignatura = new Asignatura();
		asignatura.setId_asignatura(1);
		when(asignaturaRepository.save(any(Asignatura.class))).thenReturn(asignatura);

		// Act
		AsignaturaOutPutSimpleDTO result = asignaturaService.addAsignatura(inputDTO);

		// Assert
		assertNotNull(result);
		assertEquals(1, result.getIdAsignatura());
	}

	@Test
	void deleteAsignaturaTest() throws Exception {
		// Arrange
		int id = 1;
		Asignatura asignatura = new Asignatura();
		asignatura.setId_asignatura(id);
		when(asignaturaRepository.findById(id)).thenReturn(Optional.of(asignatura));

		Student student = new Student();
		student.setId_student(id);
		List<Asignatura> asignaturas = new ArrayList<>();
		asignaturas.add(asignatura);
		student.setAsignaturas(asignaturas);

		when(studentRepository.findById(id)).thenReturn(Optional.of(student));

		// Act
		AsignaturaOutPutSimpleDTO result = asignaturaService.deleteAsignatura(id);

		// Assert
		assertNotNull(result);
		assertEquals(id, result.getIdAsignatura());
		verify(asignaturaRepository, times(1)).delete(asignatura);
		verify(studentRepository, times(1)).save(student);
	}

	@Test
	void updateAsignaturaTest() throws Exception {
		// Arrange
		int id = 1;
		AsignaturaInPutDTO inputDTO = new AsignaturaInPutDTO();
		inputDTO.setAsignaturaNombre("Math");
		inputDTO.setComments("Basic Math");
		inputDTO.setInitialDate(new Date());
		inputDTO.setFinishDate(new Date());

		Asignatura asignatura = new Asignatura();
		asignatura.setId_asignatura(id);
		asignatura.setAsignatura(inputDTO.getAsignaturaNombre());
		asignatura.setComments(inputDTO.getComments());
		asignatura.setInitial_date(inputDTO.getInitialDate());
		asignatura.setFinish_date(inputDTO.getFinishDate());

		when(asignaturaRepository.findById(id)).thenReturn(Optional.of(asignatura));
		when(asignaturaRepository.save(asignatura)).thenReturn(asignatura);

		// Act
		AsignaturaOutPutSimpleDTO result = asignaturaService.updateAsignatura(id, inputDTO);

		// Assert
		assertEquals(asignatura.getAsignatura(), result.getNombreAsignatura());
		assertEquals(asignatura.getComments(), result.getComments());
	}

	@Test
	void getAsignaturaIdFullTest() throws Exception {
		// Arrange
		int id = 1;
		Asignatura asignatura = new Asignatura();
		asignatura.setId_asignatura(id);
		asignatura.setAsignatura("Math");
		asignatura.setComments("Basic Math");
		asignatura.setEstudiantes(new ArrayList<>());

		when(asignaturaRepository.findById(id)).thenReturn(Optional.of(asignatura));

		// Act
		AsignaturaOutPutSimpleDTO result = asignaturaService.getAsignaturaId("full", id);

		// Assert
		assertEquals(asignatura.getAsignatura(), result.getNombreAsignatura());
	}

	@Test
	void getAsignaturaNombreFullTest() throws Exception {
		// Arrange
		String nombre = "Math";
		Asignatura asignatura = new Asignatura();
		asignatura.setAsignatura(nombre);
		asignatura.setComments("Basic Math");
		asignatura.setEstudiantes(new ArrayList<>());

		List<Asignatura> asignaturas = new ArrayList<>();
		asignaturas.add(asignatura);

		when(asignaturaRepository.findAll()).thenReturn(asignaturas);

		// Act
		List<AsignaturaOutPutSimpleDTO> results = asignaturaService.getAsignaturaNombre("full", nombre);

		// Assert
		assertFalse(results.isEmpty());
		AsignaturaOutPutSimpleDTO result = results.get(0);
		assertEquals(asignatura.getAsignatura(), result.getNombreAsignatura());
		assertEquals(asignatura.getComments(), result.getComments());
	}

	@Test
	void getAsignaturaFullTest() throws Exception {
		// Arrange
		Asignatura asignatura1 = new Asignatura();
		asignatura1.setAsignatura("Math");
		asignatura1.setComments("Basic Math");
		asignatura1.setEstudiantes(new ArrayList<>());

		Asignatura asignatura2 = new Asignatura();
		asignatura2.setAsignatura("Physics");
		asignatura2.setComments("Basic Physics");
		asignatura2.setEstudiantes(new ArrayList<>());

		List<Asignatura> asignaturas = new ArrayList<>();
		asignaturas.add(asignatura1);
		asignaturas.add(asignatura2);

		when(asignaturaRepository.findAll()).thenReturn(asignaturas);

		// Act
		List<AsignaturaOutPutSimpleDTO> results = asignaturaService.getAsignatura("full");

		// Assert
		assertEquals(2, results.size());
	}

	@Test
	void addStudentToAsignaturaTest() throws Exception {
		// Arrange
		int idAsignatura = 1;
		int idStudent = 1;

		Asignatura asignatura = new Asignatura();
		asignatura.setId_asignatura(idAsignatura);
		asignatura.setAsignatura("Math");
		asignatura.setComments("Basic Math");
		asignatura.setEstudiantes(new ArrayList<>());

		Student student = new Student();
		student.setId_student(idStudent);
		student.setAsignaturas(new ArrayList<>());
		Persona p=new Persona();
		Profesor pro=new Profesor();
		pro.setId_profesor(1);
		Persona p2=new Persona();
		p2.setId_persona(2);
		pro.setId_persona(p2);
		p.setId_persona(1);
		student.setPersona(p);
		student.setProfesor(pro);

		when(asignaturaRepository.findById(idAsignatura)).thenReturn(Optional.of(asignatura));
		when(studentRepository.findById(idStudent)).thenReturn(Optional.of(student));
		when(asignaturaRepository.save(asignatura)).thenReturn(asignatura);
		when(studentRepository.save(student)).thenReturn(student);

		// Act
		AsignaturaOutPutFullDTO result = (AsignaturaOutPutFullDTO) asignaturaService.addStudentToAsignatura(idAsignatura, idStudent);

		// Assert
		assertTrue(student.getAsignaturas().contains(asignatura));
		assertTrue(asignatura.getEstudiantes().contains(student));
	}
}
