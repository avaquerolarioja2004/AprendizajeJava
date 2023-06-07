package com.example.ej2SpringWeb.controladores;

import com.example.ej2SpringWeb.persona.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/get/bean")
public class GetBeans {

    @Autowired
    @Qualifier("bean1")
    private Persona bean1;

    @Autowired
    @Qualifier("bean2")
    private Persona bean2;

    @Autowired
    @Qualifier("bean3")
    private Persona bean3;


    @GetMapping("/{bean}")
    public String getBean( @PathVariable String bean) {
        if(bean.equals("bean1")) {
            return bean1.toString();
        }else if(bean.equals("bean2")) {
            return bean2.toString();
        } else if(bean.equals("bean3")) {
            return bean3.toString();
        } else {
            return "No existe el bean";
        }
    }
}
