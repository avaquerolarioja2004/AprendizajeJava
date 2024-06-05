package com.example.proyecto_final.commons.entityBus;

import com.example.proyecto_final.commons.entityReserva.Reserva;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Autobus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAutobus;
    private int hora;
    private int capacidad;
    private String destino;
    @OneToMany(mappedBy = "autobus")
    private List<Reserva> reservas;
}
