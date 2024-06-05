package com.example.proyecto_final.backEmpresa.repositories;

import com.example.proyecto_final.commons.entityBus.Autobus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AutobusRepository extends JpaRepository<Autobus, Long> {
}