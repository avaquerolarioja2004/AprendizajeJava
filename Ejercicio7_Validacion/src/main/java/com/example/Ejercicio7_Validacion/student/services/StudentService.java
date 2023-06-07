package com.example.Ejercicio7_Validacion.student.services;

import com.example.Ejercicio7_Validacion.student.dto.StudentInPutDTO;
import com.example.Ejercicio7_Validacion.student.dto.StudentOutPutDTOSimple;

import java.util.List;
public interface StudentService {
    public StudentOutPutDTOSimple addStudent(StudentInPutDTO student) throws Exception;
    public StudentOutPutDTOSimple deleteStudent(int id) throws Exception;
    public StudentOutPutDTOSimple updateStudent(int id,StudentInPutDTO persona) throws Exception;
    public StudentOutPutDTOSimple getStudentId(int id) throws Exception;
    public List getStudentNombre(String nombre) throws Exception;
    public List getStudent() throws Exception;
}
