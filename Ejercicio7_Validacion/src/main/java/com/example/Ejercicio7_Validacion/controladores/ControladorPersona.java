package com.example.Ejercicio7_Validacion.controladores;

import com.example.Ejercicio7_Validacion.persona.Persona;
import com.example.Ejercicio7_Validacion.persona.dto.PersonaInPutDTO;
import com.example.Ejercicio7_Validacion.persona.dto.PersonaOutPutDTO;
import com.example.Ejercicio7_Validacion.persona.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
//@RequestMapping("/api/persona")
public class ControladorPersona {

    @Autowired
    PersonaService personaService;

    @GetMapping("{id}")
    public PersonaOutPutDTO getPersonaId(@PathVariable(value = "id") int id) throws Exception {
        return personaService.getPersonaId(id);
    }

    @GetMapping("nombre/{nombre}")
    public List getPersonaNombre(@PathVariable String nombre) throws Exception {
        return personaService.getPersonaNombre(nombre);
    }

    @GetMapping("getall")
    public List getPersona() throws Exception {
        return personaService.getPersona();
    }

    @PostMapping("addperson")
    public PersonaOutPutDTO addPersona(@RequestBody PersonaInPutDTO persona) throws Exception {
        return personaService.addPersona(persona);
    }

    @DeleteMapping("{id}")
    public PersonaOutPutDTO deletePersona(@PathVariable int id) throws Exception {
        return personaService.deletePersona(id);
    }

    @PutMapping("{id}")
    public PersonaOutPutDTO updatePersona(@PathVariable int id, @RequestBody PersonaInPutDTO persona) throws Exception {
        return personaService.updatePersona(id, persona);
    }

    @GetMapping("customQuery")
    public List getPersonaCustomQuery(@RequestParam String ordenar,@RequestParam(required = false, defaultValue = "id") String paramOrdenar,@RequestParam(required = false) String usuario, @RequestParam(required = false) String name, @RequestParam(required = false) String surname, @RequestParam(required = false) Date fechaCreacion, @RequestParam int pagina,@RequestParam(required = false, defaultValue = "10") int limite) throws Exception {
        HashMap<String, Object> parametros = new HashMap<>();
        if (usuario != null) {
            parametros.put("usuario", usuario);
        }
        if (name != null) {
            parametros.put("name", name);
        }
        if (surname != null) {
            parametros.put("surname", surname);
        }
        if (fechaCreacion != null) {
            parametros.put("fechaCreacion", fechaCreacion);
        }
        if(paramOrdenar.equals("usuario") || paramOrdenar.equals("name") || paramOrdenar.equals(("id"))) {
            List l= personaService.getPersonaCustomQuery(ordenar,paramOrdenar, parametros,pagina,limite);
            List<PersonaOutPutDTO> l2 = new ArrayList<>();
            for (Object o : l) {
                Persona p= (Persona) o;
                PersonaOutPutDTO pOut = p.cambiaFormasOut();
                l2.add(pOut);
            }
            return l2;
        }else{
            throw new Exception("El parametro de ordenar no es valido");
        }

    }

}
