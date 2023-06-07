package com.example.ej2SpringWeb.ciudad;

import com.example.ej2SpringWeb.ciudad.Ciudad;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ListaCiudades implements CommandLineRunner {
    private ArrayList<Ciudad> ciudades;
    public void addCiudad(Ciudad ciudad) {
        ciudades.add(ciudad);
    }

    public List<Ciudad> obtenerCiudades() {
        return this.ciudades;
    }

    @Override
    public void run(String... args) throws Exception {
        ciudades=new ArrayList<Ciudad>();
    }

    public String verCiudades() {
        StringBuilder sb = new StringBuilder();
        for (Ciudad ciudad: ciudades) {
            sb.append(ciudad.getNombre()+"\t"+ciudad.getHabitantes()+"\n");
        }
        return sb.toString();
    }
}
