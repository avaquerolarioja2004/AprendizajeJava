package com.example.Ejercicio7_Validacion.profesor.services;

import com.example.Ejercicio7_Validacion.persona.services.PersonaService;
import com.example.Ejercicio7_Validacion.profesor.Profesor;
import com.example.Ejercicio7_Validacion.profesor.dto.ProfesorInPutDTO;
import com.example.Ejercicio7_Validacion.profesor.dto.ProfesorOutPutDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfesorServiceImp implements ProfesorService{

    @Autowired
    ProfesorRepository profesorRepository;
    @Autowired
    PersonaService personaService;
    @Override
    public ProfesorOutPutDTO addProfesor(ProfesorInPutDTO profesor) throws Exception {
        Profesor p=profesor.cambiarFormasProfesor();
        p.setId_persona(personaService.getPersonaId(profesor.getId_persona()).cambiaFormasPersona());
        profesorRepository.save(p);
        return p.cambiaFormasProfesor();
    }

    @Override
    public ProfesorOutPutDTO deleteProfesor(int id) throws Exception {
        Profesor p = profesorRepository.findById(id).orElseThrow(
                () -> new Exception("No se ha encontrado el profesor con id: " + id)
        );
        profesorRepository.delete(p);
        ProfesorOutPutDTO out= p.cambiaFormasProfesor();
        out.setId_persona(personaService.getPersonaId(p.getId_persona().getId_persona()));
        return out;
    }

    @Override
    public ProfesorOutPutDTO updateProfesor(int id, ProfesorInPutDTO persona) throws Exception {
        Profesor p = profesorRepository.findById(id).orElseThrow(
                () -> new Exception("No se ha encontrado el profesor con id: " + id)
        );
        p.setId_persona(personaService.getPersonaId(persona.getId_persona()).cambiaFormasPersona());
        p.setBranch(persona.getBranch());
        p.setComents(persona.getComents());
        profesorRepository.save(p);
        return p.cambiaFormasProfesor();
    }

    @Override
    public ProfesorOutPutDTO getProfesorId(int id) throws Exception {
        Profesor p = profesorRepository.findById(id).orElseThrow(
                () -> new Exception("No se ha encontrado el profesor con id: " + id)
        );
        ProfesorOutPutDTO out= p.cambiaFormasProfesor();
        out.setId_persona(personaService.getPersonaId(p.getId_persona().getId_persona()));
        return out;
    }

    @Override
    public List getProfesorNombre(String nombre) throws Exception {
        List<Profesor> profesores = profesorRepository.findAll();
        List<ProfesorOutPutDTO> profesoresDTO = new ArrayList<>();
        for (Profesor p : profesores) {
            if (p.getId_persona().getName().equals(nombre)) {
                ProfesorOutPutDTO out= p.cambiaFormasProfesor();
                out.setId_persona(personaService.getPersonaId(p.getId_persona().getId_persona()));
                profesoresDTO.add(out);
            }
        }
        if (profesoresDTO.isEmpty()) {
            throw new Exception("No se ha encontrado el profesor con nombre: " + nombre);
        }else {
            return profesoresDTO;
        }
    }

    @Override
    public List getProfesor() throws Exception {
        List<Profesor> profesores = profesorRepository.findAll();
        List<ProfesorOutPutDTO> profesoresDTO = new ArrayList<>();
        for (Profesor p : profesores) {
            ProfesorOutPutDTO out= p.cambiaFormasProfesor();
            out.setId_persona(personaService.getPersonaId(p.getId_persona().getId_persona()));
            profesoresDTO.add(out);
        }
        if (profesoresDTO.isEmpty()) {
            throw new Exception("No se ha encontrado ningun profesor");
        }else {
            return profesoresDTO;
        }
    }
}
