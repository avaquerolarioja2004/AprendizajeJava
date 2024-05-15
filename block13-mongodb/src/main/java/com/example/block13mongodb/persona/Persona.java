package com.example.block13mongodb.persona;

import com.example.block13mongodb.persona.dto.PersonaOutPutDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "personas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona {

    @Id
    String _id;
    @Size(min=6, max=10, message = "El nombre debe contener entre 6 y 10 caracteres")
    @NotNull
    String usuario;
    @NotNull
    String password;
    @NotNull
    String name;
    String surname;
    @NotNull
    String company_email;
    @NotNull
    String personal_email;
    @NotNull
    String city;
    @NotNull
    Boolean active;
    @NotNull
    Date created_date;
    String imagen_url;
    Date termination_date;

    public PersonaOutPutDTO cambiaFormasOut() {
        PersonaOutPutDTO p  = new PersonaOutPutDTO();
        p.set_id(this.get_id());
        p.setUsuario(this.getUsuario());
        p.setName(this.getName());
        p.setSurname(this.getSurname());
        p.setCompany_email(this.getCompany_email());
        p.setImagen_url(this.getImagen_url());
        p.setCreated_date(this.getCreated_date());
        p.setTermination_date(this.getTermination_date());
        p.setActive(this.getActive());
        p.setCity(this.getCity());
        p.setPersonal_email(this.getPersonal_email());
        p.setPassword(this.getPassword());
        return p;
    }

}
