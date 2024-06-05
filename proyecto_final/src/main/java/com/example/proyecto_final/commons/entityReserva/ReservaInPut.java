package com.example.proyecto_final.commons.entityReserva;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservaInPut {
    private String destino;
    private String fecha;
    private String hora;
    private String email;
}
