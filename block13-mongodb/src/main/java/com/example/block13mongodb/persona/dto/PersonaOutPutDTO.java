package com.example.block13mongodb.persona.dto;

import com.example.block13mongodb.persona.Persona;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaOutPutDTO {
    private String _id;
    private String usuario;
    private String password;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private Boolean active;
    private Date created_date;
    private String imagen_url;
    private Date termination_date;

    public Persona cambiaFormasPersona() {
        Persona persona = new Persona();
        persona.set_id(this.get_id());
        persona.setUsuario(this.getUsuario());
        persona.setSurname(this.getSurname());
        persona.setPassword(this.getPassword());
        persona.setName(this.getName());
        persona.setCompany_email(this.getCompany_email());
        persona.setPersonal_email(this.getPersonal_email());
        persona.setCity(this.getCity());
        persona.setActive(this.getActive());
        persona.setCreated_date(this.getCreated_date());
        persona.setImagen_url(this.getImagen_url());
        persona.setTermination_date(this.getTermination_date());
        return persona;
    }

}
