package com.springBootFormacion.ej1SpringBoot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Controlador {
    private Componente1 componente1;
    private Componente2 componente2;
    private Componente3 componente3;
    public Controlador(Componente1 componente1, Componente2 componente2, Componente3 componente3) {
        this.componente1 = componente1;
        this.componente2 = componente2;
        this.componente3 = componente3;
    }

}
