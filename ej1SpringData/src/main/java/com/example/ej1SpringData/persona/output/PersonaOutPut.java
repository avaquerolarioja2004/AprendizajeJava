package com.example.ej1SpringData.persona.output;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaOutPut {
    private Long id;
    private String nombre;
}
