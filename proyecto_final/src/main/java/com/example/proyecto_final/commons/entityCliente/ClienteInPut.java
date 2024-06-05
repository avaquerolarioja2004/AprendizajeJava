package com.example.proyecto_final.commons.entityCliente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClienteInPut {
    private String nombre;
    private String apellido;
    private int telefono;
    private String email;
}
