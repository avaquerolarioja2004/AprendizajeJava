package com.example.Ejercicio7_Validacion.persona.services;

import com.example.Ejercicio7_Validacion.persona.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
}
