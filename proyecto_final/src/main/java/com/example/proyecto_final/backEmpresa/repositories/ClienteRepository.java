package com.example.proyecto_final.backEmpresa.repositories;

import com.example.proyecto_final.commons.entityCliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByEmail(String email);
}
