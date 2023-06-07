package com.example.Ejercicio7_Validacion.profesor.services;

import com.example.Ejercicio7_Validacion.persona.dto.PersonaOutPutDTO;
import com.example.Ejercicio7_Validacion.profesor.dto.ProfesorInPutDTO;
import com.example.Ejercicio7_Validacion.profesor.dto.ProfesorOutPutDTO;
import com.example.Ejercicio7_Validacion.student.dto.StudentInPutDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProfesorService {
    public ProfesorOutPutDTO addProfesor(ProfesorInPutDTO profesor) throws Exception;
    public ProfesorOutPutDTO deleteProfesor(int id) throws Exception;
    public ProfesorOutPutDTO updateProfesor(int id,ProfesorInPutDTO persona) throws Exception;
    public ProfesorOutPutDTO getProfesorId(int id) throws Exception;
    public List getProfesorNombre(String nombre) throws Exception;
    public List getProfesor() throws Exception;
}
