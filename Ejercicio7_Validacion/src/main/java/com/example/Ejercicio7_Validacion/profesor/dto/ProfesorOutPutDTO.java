package com.example.Ejercicio7_Validacion.profesor.dto;

import com.example.Ejercicio7_Validacion.persona.dto.PersonaOutPutDTO;
import com.example.Ejercicio7_Validacion.profesor.Profesor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorOutPutDTO {
    int id_profesor;
    PersonaOutPutDTO id_persona;
    String coments;
    String branch;

    public Profesor cambiaFormasProfesor() {
        Profesor profesor = new Profesor();
        profesor.setId_profesor(this.id_profesor);
        profesor.setComents(this.coments);
        profesor.setBranch(this.branch);
        return profesor;
    }
}
