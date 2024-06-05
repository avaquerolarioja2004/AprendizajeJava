package com.example.proyecto_final.commons.entityCliente;

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
public class Cliente {
    @Id
    @GeneratedValue
    private int idCliente;
    private String nombre;
    private String apellido;
    @Column(unique=true)
    private String telefono;
    @Column(unique=true)
    private String email;
    @OneToMany(mappedBy = "cliente")
    private List<Reserva> reservas;
}
