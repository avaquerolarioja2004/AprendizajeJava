package com.example.Ejercicio7_Validacion.student.dto;

import com.example.Ejercicio7_Validacion.persona.Persona;
import com.example.Ejercicio7_Validacion.profesor.Profesor;
import com.example.Ejercicio7_Validacion.student.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentInPutDTO {
    int id_student;
    int id_persona;
    int num_hours_week;
    String coments;
    int id_profesor;
    String branch;

    public Student cambiarFormasStudent(){
        Student s = new Student();
        s.setId_student(this.id_student);
        s.setNum_hours_week(this.num_hours_week);
        s.setComents(this.coments);
        s.setBranch(this.branch);
        return s;
    }
}
