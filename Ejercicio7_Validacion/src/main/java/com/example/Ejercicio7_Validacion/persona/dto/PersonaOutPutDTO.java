package com.example.Ejercicio7_Validacion.persona.dto;

import com.example.Ejercicio7_Validacion.persona.Persona;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaOutPutDTO {
    int id;
    String usuario;
    String name;
    String surname;
    String company_email;
    String imagen_url;

    public Persona cambiaFormasPersona() {
        Persona persona = new Persona();
        persona.setId_persona(this.id);
        persona.setUsuario(this.usuario);
        persona.setName(this.name);
        persona.setSurname(this.surname);
        persona.setCompany_email(this.company_email);
        persona.setImagen_url(this.imagen_url);
        return persona;
    }

}
