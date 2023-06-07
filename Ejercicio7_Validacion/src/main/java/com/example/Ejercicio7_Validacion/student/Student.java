package com.example.Ejercicio7_Validacion.student;

import com.example.Ejercicio7_Validacion.persona.Persona;
import com.example.Ejercicio7_Validacion.profesor.Profesor;
import com.example.Ejercicio7_Validacion.student.dto.StudentOutPutDTOFull;
import com.example.Ejercicio7_Validacion.student.dto.StudentOutPutDTOSimple;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "estudiante")
public class Student {
    @Id
    @GeneratedValue
    int id_student;
    @OneToOne
    @JoinColumn(name = "id_persona", nullable = false, unique = true)
    Persona persona;
    @NotNull
    int num_hours_week;
    @ManyToOne
    Profesor profesor;
    @NotNull
    String branch;
    String coments;

    public StudentOutPutDTOSimple cambiaFormasStudent(String type,Persona persona, Profesor profesor) {
        if (type.equals("full")){
            StudentOutPutDTOFull studentOutPutDTOFull = new StudentOutPutDTOFull();
            studentOutPutDTOFull.setId_student(this.id_student);
            studentOutPutDTOFull.setComents(this.coments);
            studentOutPutDTOFull.setBranch(this.branch);
            studentOutPutDTOFull.setId_profesor(profesor.cambiaFormasProfesor());
            studentOutPutDTOFull.setId_persona(persona.cambiaFormasOut());
            return studentOutPutDTOFull;
        }else{
            StudentOutPutDTOSimple studentOutPutDTOSimple = new StudentOutPutDTOSimple();
            studentOutPutDTOSimple.setId_persona(persona.cambiaFormasOut());
            studentOutPutDTOSimple.setBranch(this.branch);
            studentOutPutDTOSimple.setId_student(this.id_student);
            studentOutPutDTOSimple.setId_profesor(profesor.cambiaFormasProfesor());
            return studentOutPutDTOSimple;
        }
    }
}
