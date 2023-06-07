package com.example.ej1SpringData.persona.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaInPut {
    private Long id;
    private String nombre;
    private String apellido;
    private int edad;
}
