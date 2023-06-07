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
    public StudentOutPutDTOSimple getStudentId(@PathVariable(value = "id") int id) throws Exception {
        return studentService.getStudentId(id);
    }

    @GetMapping("/nombre/{nombre}")
    public List getStudentNombre(@PathVariable String nombre) throws Exception {
        return studentService.getStudentNombre(nombre);
    }

    @GetMapping
    public List getStudent() throws Exception {
        return studentService.getStudent();
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

}