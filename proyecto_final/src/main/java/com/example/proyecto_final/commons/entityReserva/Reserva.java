package com.example.proyecto_final.commons.entityReserva;

import com.example.proyecto_final.commons.entityBus.Autobus;
import com.example.proyecto_final.commons.entityCliente.Cliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReserva;
    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;
    private LocalDate fecha;
    @ManyToOne
    @JoinColumn(name = "idAutobus")
    private Autobus autobus;
}
