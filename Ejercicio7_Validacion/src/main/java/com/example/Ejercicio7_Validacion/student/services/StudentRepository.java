package com.example.Ejercicio7_Validacion.student.services;

import com.example.Ejercicio7_Validacion.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
