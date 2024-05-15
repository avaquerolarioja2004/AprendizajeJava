package com.example.Ejercicio7_Validacion.asignatura.services;

import com.example.Ejercicio7_Validacion.asignatura.dto.AsignaturaInPutDTO;
import com.example.Ejercicio7_Validacion.asignatura.dto.AsignaturaOutPutSimpleDTO;

import java.util.List;

public interface AsignaturaService {
    public AsignaturaOutPutSimpleDTO addAsignatura(AsignaturaInPutDTO asignatura) throws Exception;
    public AsignaturaOutPutSimpleDTO deleteAsignatura(int id) throws Exception;
    public AsignaturaOutPutSimpleDTO updateAsignatura(int id,AsignaturaInPutDTO asignatura) throws Exception;
    public AsignaturaOutPutSimpleDTO getAsignaturaId(String type,int id) throws Exception;
    public List getAsignaturaNombre(String type, String nomAsignatura) throws Exception;
    public List getAsignatura(String type) throws Exception;
    public AsignaturaOutPutSimpleDTO addStudentToAsignatura(int idAsignatura, int idStudent) throws Exception;
}
