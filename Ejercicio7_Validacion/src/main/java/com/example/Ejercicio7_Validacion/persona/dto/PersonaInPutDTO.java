package com.example.Ejercicio7_Validacion.persona.dto;

import com.example.Ejercicio7_Validacion.persona.Persona;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaInPutDTO {
    int id;
    String usuario;
    String password;
    String name;
    String surname;
    String company_email;
    String personal_email;
    String city;
    Boolean active;
    Date created_date;
    String imagen_url;
    Date termination_date;

    public Persona cambiaFormasPersona(){
        Persona p = new Persona();
        p.setId_persona(this.getId());
        p.setUsuario(this.getUsuario());
        p.setPassword(this.getPassword());
        p.setName(this.getName());
        p.setSurname(this.getSurname());
        p.setCompany_email(this.getCompany_email());
        p.setPersonal_email(this.getPersonal_email());
        p.setCity(this.getCity());
        p.setActive(this.getActive());
        p.setCreated_date(this.getCreated_date());
        p.setImagen_url(this.getImagen_url());
        p.setTermination_date(this.getTermination_date());
        return p;
    }
}
