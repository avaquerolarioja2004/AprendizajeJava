package com.example.Ejercicio7_Validacion.asignatura.dto;

import com.example.Ejercicio7_Validacion.asignatura.Asignatura;
import com.example.Ejercicio7_Validacion.student.dto.StudentOutPutDTOSimple;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsignaturaOutPutFullDTO extends AsignaturaOutPutSimpleDTO{
    List<StudentOutPutDTOSimple> students;

    public AsignaturaOutPutFullDTO(Asignatura asignatura) {
        super(asignatura);
        List<StudentOutPutDTOSimple> students = new ArrayList<>();
        asignatura.getEstudiantes().forEach(student -> {
            students.add(student.cambiaFormasStudent("simple",student.getPersona(),student.getProfesor()));
        });
        this.students = students;
    }
}
