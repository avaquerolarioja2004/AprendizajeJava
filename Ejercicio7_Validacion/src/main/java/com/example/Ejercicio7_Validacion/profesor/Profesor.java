package com.example.Ejercicio7_Validacion.profesor;

import com.example.Ejercicio7_Validacion.persona.Persona;
import com.example.Ejercicio7_Validacion.profesor.dto.ProfesorOutPutDTO;
import com.example.Ejercicio7_Validacion.student.Student;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profesor {
    @Id
    @GeneratedValue
    int id_profesor;
    @OneToOne
    Persona id_persona;
    String coments;
    @NotNull
    String branch;
    @OneToMany(mappedBy = "profesor", fetch = FetchType.LAZY)
    Set<Student> id_student;

    public ProfesorOutPutDTO cambiaFormasProfesor(){
        ProfesorOutPutDTO p = new ProfesorOutPutDTO();
        p.setId_profesor(this.id_profesor);
        p.setId_persona(this.id_persona.cambiaFormasOut());
        p.setComents(this.coments);
        p.setBranch(this.branch);
        return p;
    }
}

