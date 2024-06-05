package com.example.proyecto_final.backEmpresa.repositories;

import com.example.proyecto_final.commons.entityReserva.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
