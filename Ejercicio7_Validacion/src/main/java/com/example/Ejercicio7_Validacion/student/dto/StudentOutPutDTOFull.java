package com.example.Ejercicio7_Validacion.student.dto;

import com.example.Ejercicio7_Validacion.persona.dto.PersonaOutPutDTO;
import com.example.Ejercicio7_Validacion.profesor.dto.ProfesorOutPutDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentOutPutDTOFull extends StudentOutPutDTOSimple{
    String coments;
}
