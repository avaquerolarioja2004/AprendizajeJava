package com.example.Ejercicio7_Validacion.asignatura.dto;

import com.example.Ejercicio7_Validacion.asignatura.Asignatura;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsignaturaOutPutSimpleDTO {
    int idAsignatura;
    String nombreAsignatura;
    String comments;
    Date initialDate;
    Date finishDate;


    public AsignaturaOutPutSimpleDTO(Asignatura asignatura)
    {
        idAsignatura=asignatura.getId_asignatura();
        nombreAsignatura=asignatura.getAsignatura();
        comments=asignatura.getComments();
        initialDate=asignatura.getInitial_date();
        finishDate=asignatura.getFinish_date();
    }
}
