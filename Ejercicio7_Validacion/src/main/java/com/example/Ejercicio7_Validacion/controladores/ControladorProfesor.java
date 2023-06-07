package com.example.Ejercicio7_Validacion.controladores;

import com.example.Ejercicio7_Validacion.profesor.services.ProfesorService;
import com.example.Ejercicio7_Validacion.profesor.dto.ProfesorInPutDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/profesor")
public class ControladorProfesor {

    @Autowired
    ProfesorService profesorService;

    @GetMapping("{id}")
    public Object getProfesorId(@PathVariable(value = "id") int id) throws Exception {
        return profesorService.getProfesorId(id);
    }

    @GetMapping("/nombre/{nombre}")
    public List getProfesorNombre(@PathVariable String nombre) throws Exception {
        return profesorService.getProfesorNombre(nombre);
    }

    @GetMapping
    public List getProfesor() throws Exception {
        return profesorService.getProfesor();
    }

    @PostMapping
    public Object addProfesor(@RequestBody ProfesorInPutDTO profesor) throws Exception {
        return profesorService.addProfesor(profesor);
    }

    @DeleteMapping("{id}")
    public Object deleteProfesor(@PathVariable int id) throws Exception {
        return profesorService.deleteProfesor(id);
    }

    @PutMapping("{id}")
    public Object updateProfesor(@PathVariable int id, @RequestBody ProfesorInPutDTO profesor) throws Exception {
        return profesorService.updateProfesor(id, profesor);
    }

}
