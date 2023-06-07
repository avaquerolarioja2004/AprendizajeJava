package com.example.Ejercicio7_Validacion.student.services;

import com.example.Ejercicio7_Validacion.persona.services.PersonaService;
import com.example.Ejercicio7_Validacion.profesor.services.ProfesorService;
import com.example.Ejercicio7_Validacion.student.Student;
import com.example.Ejercicio7_Validacion.student.dto.StudentInPutDTO;
import com.example.Ejercicio7_Validacion.student.dto.StudentOutPutDTOSimple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImp implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private ProfesorService profesorService;

    @Override
    public StudentOutPutDTOSimple addStudent(StudentInPutDTO student) throws Exception {
        Student s = student.cambiarFormasStudent();
        s.setPersona(personaService.getPersonaId(student.getId_persona()).cambiaFormasPersona());
        s.setProfesor(profesorService.getProfesorId(student.getId_profesor()).cambiaFormasProfesor());
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
    public StudentOutPutDTOSimple getStudentId(int id) throws Exception {
        Student s = studentRepository.findById(id).orElseThrow(
                () -> new Exception("No se ha encontrado el estudiante con id: " + id)
        );
        return s.cambiaFormasStudent("simple",s.getPersona(),s.getProfesor());
    }

    @Override
    public List getStudentNombre(String nombre) throws Exception {
        List<StudentOutPutDTOSimple> s = new ArrayList<>();
        for (Student student : studentRepository.findAll()) {
            if (student.getPersona().getName().equals(nombre)) {
                s.add(student.cambiaFormasStudent("simple",student.getPersona(),student.getProfesor()));
            }
        }
        if (s.isEmpty()) {
            throw new Exception("No se ha encontrado el estudiante con nombre: " + nombre);
        }else {
            return s;
        }
    }

    @Override
    public List getStudent() throws Exception {
        List<StudentOutPutDTOSimple> s = new ArrayList<>();
        List<Student> lista = studentRepository.findAll();
        for (Student student :lista) {
            StudentOutPutDTOSimple studentOutPutDTOSimple = student.cambiaFormasStudent("simple",student.getPersona(),student.getProfesor());
            s.add(studentOutPutDTOSimple);
        }
        if (s.isEmpty()) {
            throw new Exception("No se ha encontrado ningun estudiante");
        }else {
            return s;
        }
    }
}
