package com.example.Ejercicio7_Validacion.asignatura;

import com.example.Ejercicio7_Validacion.student.Student;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Asignatura {
    @Id
    @GeneratedValue
    int id_asignatura;
    @ManyToMany(mappedBy = "asignaturas")
    List<Student> estudiantes;
    String asignatura;
    String comments;
    @NotNull
    Date initial_date;
    Date finish_date;
}
