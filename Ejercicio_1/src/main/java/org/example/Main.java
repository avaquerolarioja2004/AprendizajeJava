package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    private static final String RUTA = "C:\\Users\\angel.vaquero\\OneDrive - Bosonit\\Escritorio\\JavaEjercicios\\Ejercicio_1\\src\\main\\resources\\people.csv";

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        List l=m.listarPersonas(RUTA);
        System.out.println("- LISTA DE PERSONAS VÁLIDAS -\n");
        m.leerLista(l);
        System.out.println("\n**********************\n");
        System.out.println("APARTADO A)\n");
        m.leerLista(m.filtroEdad(l));
        System.out.println("\n**********************\n");
        System.out.println("APARTADO B)\n");
        m.leerLista(m.filtroNombre(l));
        System.out.println("\n**********************\n");
        System.out.println("APARTADO C)\n");;
        m.leerLista(m.filtroCiudadMadrid(l));
        System.out.println("\n**********************\n");
        System.out.println("APARTADO D)\n");
        m.leerLista(m.filtroCiudadBarcelona(l));

    }

    public List<Persona> listarPersonas(String direccion) throws IOException {
        List<Persona> personas = new ArrayList<Persona>();
        Persona p;

        BufferedReader br = new BufferedReader(new FileReader(new File(direccion)));
        String linea = br.readLine();
        while (linea != null) {
            String[] campos = (linea.split(":"));
            int comprobar = campos.length;
            if (comprobar == 3) {
                if (campos[0] == null || campos[0].equals("")) {
                    linea = br.readLine();
                    continue;
                } else if (campos[1] == null || campos[1].equals("")) {
                    campos[1] = "*";
                    p = new Persona(campos[0], campos[1], Integer.parseInt(campos[2]));
                    personas.add(p);
                } else if (campos[2] == null || campos[2].equals("")) {
                    campos[2] = "0";
                    p = new Persona(campos[0], campos[1], Integer.parseInt(campos[2]));
                    personas.add(p);
                } else {
                    p = new Persona(campos[0], campos[1], Integer.parseInt(campos[2]));
                    personas.add(p);
                }
            } else {
                if (comprobar == 1) {
                    if (campos[0].matches("[0-9]")) {
                        linea = br.readLine();
                        continue;
                    } else {
                        p = new Persona(campos[0]);
                        personas.add(p);
                    }
                } else {
                    if (campos[1].matches("[0-9]")) {
                        p = new Persona(campos[0], "*", Integer.parseInt(campos[1]));
                        personas.add(p);
                    } else {
                        p = new Persona(campos[0], campos[1], 0);
                        personas.add(p);
                    }
                }
            }
            linea = br.readLine();

        }
        return personas;
    }

    private void leerLista(List<Persona> lista){
        Iterator it = lista.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    private List<Persona> filtroEdad(List<Persona> listaPersonas) {

        return listaPersonas.stream().filter((Persona persona) -> persona.getEdad() > 0 && persona.getEdad() < 25).collect(Collectors.toList());
    }

    private List<Persona> filtroNombre(List<Persona> listaPersonas) {
        List<Persona> nuevaLista = listaPersonas.stream().filter(persona -> persona.getNombre().matches("[aáAÁ].*")).collect(Collectors.toList());
        for (Persona p: nuevaLista) {
            if (p.getCiudad().equals("*")) {
                p.setCiudad("unknown");
            }
        }
        return nuevaLista;
    }

    private List<Persona> filtroCiudadMadrid(List<Persona> listaPersonas) {
        Optional<Persona> op =  listaPersonas.stream().filter(persona -> persona.getCiudad().equals("Madrid")).findFirst();
        if(op.isPresent()){
            return op.stream().toList();
        }else{
            return null;
        }
    }

    private List<Persona> filtroCiudadBarcelona(List<Persona> listaPersonas) {
        Optional<Persona> op =  listaPersonas.stream().filter(persona -> persona.getCiudad().equals("Barcelona")).findFirst();
        if(op.isPresent()){
            return op.stream().toList();
        }else{
            return null;
        }
    }

}