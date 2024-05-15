package com.example.Ejercicio7_Validacion.profesor.dto;

import com.example.Ejercicio7_Validacion.persona.services.PersonaService;
import com.example.Ejercicio7_Validacion.profesor.Profesor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorInPutDTO {
    int id_profesor;
    int id_persona;
    String coments;
    String branch;

    public Profesor cambiarFormasProfesor(){
        Profesor p = new Profesor();
        p.setId_profesor(this.id_profesor);
        p.setComents(this.coments);
        p.setBranch(this.branch);
        return p;
    }
}
