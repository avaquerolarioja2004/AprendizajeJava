package com.example.Ejercicio7_Validacion.controladores;

import com.example.Ejercicio7_Validacion.asignatura.dto.AsignaturaInPutDTO;
import com.example.Ejercicio7_Validacion.asignatura.dto.AsignaturaOutPutSimpleDTO;
import com.example.Ejercicio7_Validacion.asignatura.services.AsignaturaService;
import com.example.Ejercicio7_Validacion.student.dto.StudentInPutDTO;
import com.example.Ejercicio7_Validacion.student.dto.StudentOutPutDTOSimple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asignatura")
public class ControladorAsignatura {
    @Autowired
    private AsignaturaService servicioAsignatura;

    @GetMapping("{id}")
    public AsignaturaOutPutSimpleDTO getAsignaturaId(@PathVariable int id, @RequestParam String type) throws Exception {
        return servicioAsignatura.getAsignaturaId(type,id);
    }

    @GetMapping("/nombre/{nombre}")
    public List getAsignaturaNombre(@PathVariable String nombre, @RequestParam String type) throws Exception {
        return servicioAsignatura.getAsignaturaNombre(type,nombre);
    }

    @GetMapping
    public List getAsignatura(@RequestParam String type) throws Exception {
        return servicioAsignatura.getAsignatura(type);
    }

    @PostMapping
    public AsignaturaOutPutSimpleDTO addAsignatura(@RequestBody AsignaturaInPutDTO asignatura) throws Exception {
        return servicioAsignatura.addAsignatura(asignatura);
    }

    @DeleteMapping("{id}")
    public AsignaturaOutPutSimpleDTO deleteAsignatura(@PathVariable int id) throws Exception {
        return servicioAsignatura.deleteAsignatura(id);
    }

    @PutMapping("{id}")
    public AsignaturaOutPutSimpleDTO updateAsignatura(@PathVariable int id, @RequestBody AsignaturaInPutDTO asignatura) throws Exception {
        return servicioAsignatura.updateAsignatura(id, asignatura);
    }

    @PutMapping("addStudent/{idAsignatura}:{idStudent}")
    public AsignaturaOutPutSimpleDTO addStudent(@PathVariable int idAsignatura, @PathVariable int idStudent) throws Exception {
        return servicioAsignatura.addStudentToAsignatura(idAsignatura, idStudent);
    }
}
