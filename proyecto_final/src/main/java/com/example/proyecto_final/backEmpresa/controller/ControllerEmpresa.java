package com.example.proyecto_final.backEmpresa.controller;

import com.example.proyecto_final.backEmpresa.repositories.AutobusRepository;
import com.example.proyecto_final.backEmpresa.repositories.ClienteRepository;
import com.example.proyecto_final.backEmpresa.repositories.ReservaRepository;
import com.example.proyecto_final.backEmpresa.schedule.ScheduleTasks;
import com.example.proyecto_final.commons.entityBus.Autobus;
import com.example.proyecto_final.commons.entityCliente.Cliente;
import com.example.proyecto_final.commons.entityReserva.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/admin")
public class ControllerEmpresa {
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ReservaRepository reservaRepository;
    @Autowired
    AutobusRepository autobusRepository;
    @Autowired
    private ScheduleTasks tasks;

    @PostMapping("/init")
    public void start(){
        int[] horas={8,10,12,14,16,18,20,22};
        String[] destinos = {"Valencia", "Madrid", "Barcelona", "Bilbao"};
        Random random = new Random();
        for (int i=0;i<20;i++){
            Autobus autobus = new Autobus();
            autobus.setCapacidad(40);
            autobus.setHora(horas[random.nextInt(horas.length)]);
            autobus.setDestino(destinos[random.nextInt(destinos.length)]);
            autobusRepository.save(autobus);
        }
    }

    @GetMapping("/getAllInfo")
    public String getAllInfo(){
        List<Cliente> clientes = clienteRepository.findAll();
        List<Autobus> autobuses = autobusRepository.findAll();
        List<Reserva> reservas = reservaRepository.findAll();
        StringBuilder outCliente=new StringBuilder("clienteId;nombre;apellido;email;telefono;reservas\n");
        StringBuilder outAutobus=new StringBuilder("autobusId;destino;hora;capacidad;reservas\n");
        StringBuilder outReserva=new StringBuilder("reservaId;fecha;cliente;autobus\n");

        for(Cliente c:clientes){
            outCliente.append(c.getIdCliente()+";");
            outCliente.append(c.getNombre()+";");
            outCliente.append(c.getApellido()+";");
            outCliente.append(c.getEmail()+";");
            outCliente.append(c.getTelefono()+";");
            outCliente.append(c.getReservas());
            outCliente.append("\n");
        }

        for (Autobus b:autobuses){
            outAutobus.append(b.getIdAutobus()+";");
            outAutobus.append(b.getDestino()+";");
            outAutobus.append(b.getHora()+";");
            outAutobus.append(b.getCapacidad()+";");
            outAutobus.append(b.getReservas());
            outAutobus.append("\n");
        }

        for(Reserva r:reservas){
            outReserva.append(r.getIdReserva()+";");
            outReserva.append(r.getFecha()+";");
            outReserva.append(r.getCliente()+";");
            outReserva.append(r.getAutobus());
            outReserva.append("\n");
        }

        return outReserva+"\n"+outCliente+"\n"+outAutobus;
    }

    @GetMapping("/schedule")
    public void Schedule(){
        tasks.performDailyTasks();
    }

}
