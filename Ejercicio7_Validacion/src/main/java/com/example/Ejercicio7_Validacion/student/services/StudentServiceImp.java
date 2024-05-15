package com.example.Ejercicio7_Validacion.student.services;

import com.example.Ejercicio7_Validacion.asignatura.Asignatura;
import com.example.Ejercicio7_Validacion.asignatura.services.AsignaturaRepository;
import com.example.Ejercicio7_Validacion.asignatura.services.AsignaturaService;
import com.example.Ejercicio7_Validacion.persona.services.PersonaService;
import com.example.Ejercicio7_Validacion.profesor.Profesor;
import com.example.Ejercicio7_Validacion.profesor.services.ProfesorService;
import com.example.Ejercicio7_Validacion.student.Student;
import com.example.Ejercicio7_Validacion.student.dto.StudentInPutDTO;
import com.example.Ejercicio7_Validacion.student.dto.StudentOutPutDTOSimple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class StudentServiceImp implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Override
    public StudentOutPutDTOSimple addStudent(StudentInPutDTO student) throws Exception {
        Student s = student.cambiarFormasStudent();
        s.setPersona(personaService.getPersonaId(student.getId_persona()).cambiaFormasPersona());
        Profesor p = profesorService.getProfesorId(student.getId_profesor()).cambiaFormasProfesor();
        p.setId_persona(personaService.getPersonaId(student.getId_persona()).cambiaFormasPersona());
        s.setProfesor(p);
        studentRepository.save(s);
        return new StudentOutPutDTOSimple(s.getId_student(),s.getPersona().cambiaFormasOut(),s.getProfesor().cambiaFormasProfesor(),s.getBranch());
    }

    @Override
    public StudentOutPutDTOSimple deleteStudent(int id) throws Exception {
        Student s = studentRepository.findById(id).orElseThrow(
                () -> new Exception("No se ha encontrado el estudiante con id: " + id)
        );
        studentRepository.delete(s);
        return s.cambiaFormasStudent("simple",studentRepository.findById(id).orElse(null).getPersona(),studentRepository.findById(id).orElse(null).getProfesor());
    }

    @Override
    public StudentOutPutDTOSimple updateStudent(int id, StudentInPutDTO student) throws Exception {
        Student s = studentRepository.findById(id).orElseThrow(
                () -> new Exception("No se ha encontrado el estudiante con id: " + id)
        );
        s.setPersona(personaService.getPersonaId(student.getId_persona()).cambiaFormasPersona());
        s.setProfesor(profesorService.getProfesorId(student.getId_profesor()).cambiaFormasProfesor());
        s.setBranch(student.getBranch());
        s.setId_student(student.getId_student());
        s.setComents(student.getComents());
        s.setNum_hours_week(student.getNum_hours_week());
        studentRepository.save(s);
        return s.cambiaFormasStudent("simple",personaService.getPersonaId(student.getId_persona()).cambiaFormasPersona(),profesorService.getProfesorId(student.getId_profesor()).cambiaFormasProfesor());
    }

    @Override
    public StudentOutPutDTOSimple getStudentId(String type,int id) throws Exception {
        Student s = studentRepository.findById(id).orElseThrow(
                () -> new Exception("No se ha encontrado el estudiante con id: " + id)
        );
        return s.cambiaFormasStudent(type,s.getPersona(),s.getProfesor());
    }

    @Override
    public List getStudentNombre(String type,String nombre) throws Exception {
        List<StudentOutPutDTOSimple> s = new ArrayList<>();
        for (Student student : studentRepository.findAll()) {
            if (student.getPersona().getName().equals(nombre)) {
                s.add(student.cambiaFormasStudent(type,student.getPersona(),student.getProfesor()));
            }
        }
        if (s.isEmpty()) {
            throw new Exception("No se ha encontrado el estudiante con nombre: " + nombre);
        }else {
            return s;
        }
    }

    @Override
    public List getStudent(String type) throws Exception {
        List<StudentOutPutDTOSimple> s = new ArrayList<>();
        List<Student> lista = studentRepository.findAll();
        for (Student student :lista) {
            StudentOutPutDTOSimple studentOutPutDTOSimple = student.cambiaFormasStudent(type,student.getPersona(),student.getProfesor());
            s.add(studentOutPutDTOSimple);
        }
        if (s.isEmpty()) {
            throw new Exception("No se ha encontrado ningun estudiante");
        }else {
            return s;
        }
    }

    @Override
    public StudentOutPutDTOSimple addAsignaturaToStudent(int idStudent, int idAsignatura) throws Exception {
        Asignatura a=asignaturaRepository.findById(idAsignatura).orElseThrow(
                () -> new Exception("No se ha encontrado la asignatura con id: " + idAsignatura)
        );
        Student s=studentRepository.findById(idStudent).orElseThrow(
                () -> new Exception("No se ha encontrado el estudiante con id: " + idStudent)
        );
        List<Asignatura> asignaturas=s.getAsignaturas();
        List<Student> students=a.getEstudiantes();
        if(asignaturas.contains(a) | students.contains(s)){
            throw new Exception("El estudiante ya esta matriculado en la asignatura");
        }
        asignaturas.add(a);
        students.add(s);
        s.setAsignaturas(asignaturas);
        a.setEstudiantes(students);
        studentRepository.save(s);
        asignaturaRepository.save(a);
        return s.cambiaFormasStudent("full",s.getPersona(),s.getProfesor());
    }
}
