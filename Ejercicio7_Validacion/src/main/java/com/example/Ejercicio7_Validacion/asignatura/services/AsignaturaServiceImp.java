package com.example.Ejercicio7_Validacion.asignatura.services;

import com.example.Ejercicio7_Validacion.asignatura.Asignatura;
import com.example.Ejercicio7_Validacion.asignatura.dto.AsignaturaInPutDTO;
import com.example.Ejercicio7_Validacion.asignatura.dto.AsignaturaOutPutFullDTO;
import com.example.Ejercicio7_Validacion.asignatura.dto.AsignaturaOutPutSimpleDTO;
import com.example.Ejercicio7_Validacion.student.Student;
import com.example.Ejercicio7_Validacion.student.services.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class AsignaturaServiceImp implements AsignaturaService{
    @Autowired
    AsignaturaRepository asignaturaRepository;
    @Autowired
    StudentRepository studentRepository;

    @Override
    public AsignaturaOutPutSimpleDTO addAsignatura(AsignaturaInPutDTO asignatura) throws Exception {
        Asignatura asignatura1=new Asignatura();
        asignatura1.setAsignatura(asignatura.getAsignaturaNombre());
        asignatura1.setComments(asignatura.getComments());
        asignatura1.setInitial_date(asignatura.getInitialDate());
        asignatura1.setFinish_date(asignatura.getFinishDate());
        asignaturaRepository.save(asignatura1);
        return new AsignaturaOutPutSimpleDTO(asignatura1);
    }

    @Override
    public AsignaturaOutPutSimpleDTO deleteAsignatura(int id) throws Exception {
        Asignatura asignatura=asignaturaRepository.findById(id).orElseThrow(()->new Exception("Asignatura no encontrada"));
        Student student=studentRepository.findById(id).orElseThrow(()->new Exception("Estudiante no encontrado"));
        asignaturaRepository.delete(asignatura);
        student.getAsignaturas().remove(asignatura);
        studentRepository.save(student);
        return new AsignaturaOutPutSimpleDTO(asignatura);
    }

    @Override
    public AsignaturaOutPutSimpleDTO updateAsignatura(int id, AsignaturaInPutDTO asignatura) throws Exception {
        Asignatura asignatura1=asignaturaRepository.findById(id).orElseThrow(()->new Exception("Asignatura no encontrada"));
        asignatura1.setAsignatura(asignatura.getAsignaturaNombre());
        asignatura1.setComments(asignatura.getComments());
        asignatura1.setInitial_date(asignatura.getInitialDate());
        asignatura1.setFinish_date(asignatura.getFinishDate());
        asignaturaRepository.save(asignatura1);
        return new AsignaturaOutPutSimpleDTO(asignatura1);
    }

    @Override
    public AsignaturaOutPutSimpleDTO getAsignaturaId(String type, int id) throws Exception {
        Asignatura asignatura=asignaturaRepository.findById(id).orElseThrow(()->new Exception("Asignatura no encontrada"));
        if(type.equals("full"))
            return new AsignaturaOutPutFullDTO(asignatura);
        else{
            return new AsignaturaOutPutSimpleDTO(asignatura);
        }
    }

    @Override
    public List getAsignaturaNombre(String type, String nomAsignatura) throws Exception {
        List<AsignaturaOutPutSimpleDTO> asignaturasOut=new ArrayList<>();
        for (Asignatura asignatura:asignaturaRepository.findAll()) {
            if(asignatura.getAsignatura().equals(nomAsignatura)){
                if(type.equals("full"))
                    asignaturasOut.add(new AsignaturaOutPutFullDTO(asignatura));
                else{
                    asignaturasOut.add(new AsignaturaOutPutSimpleDTO(asignatura));
                }
            }
        }
        if (asignaturasOut.isEmpty())
            throw new Exception("No se encontraron asignaturas con ese nombre");
        return asignaturasOut;
    }

    @Override
    public List getAsignatura(String type) throws Exception {
        List<AsignaturaOutPutSimpleDTO> asignaturasOut=new ArrayList<>();
        for (Asignatura asignatura:asignaturaRepository.findAll()) {
            if(type.equals("full"))
                asignaturasOut.add(new AsignaturaOutPutFullDTO(asignatura));
            else{
                asignaturasOut.add(new AsignaturaOutPutSimpleDTO(asignatura));
            }
        }
        if (asignaturasOut.isEmpty())
            throw new Exception("No se encontraron asignaturas");
        return asignaturasOut;
    }

    @Override
    public AsignaturaOutPutSimpleDTO addStudentToAsignatura(int idAsignatura, int idStudent) throws Exception {
        Asignatura asignatura=asignaturaRepository.findById(idAsignatura).orElseThrow(()->new Exception("Asignatura no encontrada"));
        Student student=studentRepository.findById(idStudent).orElseThrow(()->new Exception("Estudiante no encontrado"));
        List<Student> students= asignatura.getEstudiantes();
        List<Asignatura> asignaturas= student.getAsignaturas();
        students.add(student);
        asignatura.setEstudiantes(students);
        asignaturaRepository.save(asignatura);
        asignaturas.add(asignatura);
        student.setAsignaturas(asignaturas);
        studentRepository.save(student);
        return new AsignaturaOutPutSimpleDTO(asignatura);
    }
}
