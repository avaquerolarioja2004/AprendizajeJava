package com.example.Ejercicio7_Validacion.asignatura.dto;

import com.example.Ejercicio7_Validacion.asignatura.Asignatura;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsignaturaInPutDTO{
        int idAsignatura;
        String asignaturaNombre;
        String comments;
        Date initialDate;
        Date finishDate;

    public Asignatura camabiarFormasAsignatura() {
        Asignatura asignatura = new Asignatura();
        asignatura.setId_asignatura(this.idAsignatura);
        asignatura.setAsignatura(this.asignaturaNombre);
        asignatura.setComments(this.comments);
        asignatura.setInitial_date(this.initialDate);
        asignatura.setFinish_date(this.finishDate);
        return asignatura;
    }
}
