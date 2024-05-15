package com.example.ej1SpringData.persona.repository;

import com.example.ej1SpringData.persona.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
