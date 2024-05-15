package com.example.Ejercicio7_Validacion.controladores;

import com.example.Ejercicio7_Validacion.student.dto.StudentInPutDTO;
import com.example.Ejercicio7_Validacion.student.dto.StudentOutPutDTOSimple;
import com.example.Ejercicio7_Validacion.student.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/student")
public class ControladorStudent {

    @Autowired
    StudentService studentService;

    @GetMapping("{id}")
    public StudentOutPutDTOSimple getStudentId(@PathVariable(value = "id") int id, @RequestParam(defaultValue = "simple") String type) throws Exception {
        return studentService.getStudentId(type,id);
    }

    @GetMapping("/nombre/{nombre}")
    public List getStudentNombre(@PathVariable String nombre, @RequestParam(defaultValue = "simple") String type) throws Exception {
        return studentService.getStudentNombre(type,nombre);
    }

    @GetMapping
    public List getStudent(@RequestParam(defaultValue = "simple") String type) throws Exception {
        return studentService.getStudent(type);
    }

    @PostMapping
    public StudentOutPutDTOSimple addStudent(@RequestBody StudentInPutDTO student) throws Exception {
        return studentService.addStudent(student);
    }

    @DeleteMapping("{id}")
    public StudentOutPutDTOSimple deleteStudent(@PathVariable int id) throws Exception {
        return studentService.deleteStudent(id);
    }

    @PutMapping("{id}")
    public StudentOutPutDTOSimple updateStudent(@PathVariable int id, @RequestBody StudentInPutDTO student) throws Exception {
        return studentService.updateStudent(id, student);
    }

    @PutMapping("addAsignatura/{idAsignatura}:{idStudent}")
    public StudentOutPutDTOSimple addAsignatura(@PathVariable int idAsignatura, @PathVariable int idStudent) throws Exception {
        return studentService.addAsignaturaToStudent(idAsignatura, idStudent);
    }

}