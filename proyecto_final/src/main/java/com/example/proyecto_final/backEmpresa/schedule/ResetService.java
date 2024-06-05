package com.example.proyecto_final.backEmpresa.schedule;

import com.example.proyecto_final.backEmpresa.repositories.AutobusRepository;
import com.example.proyecto_final.backEmpresa.repositories.ReservaRepository;
import com.example.proyecto_final.commons.entityBus.Autobus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResetService {
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private AutobusRepository autobusRepository;

    public void resetDatabase() {
        reservaRepository.deleteAll();

        List<Autobus> autobuses = autobusRepository.findAll();
        for (Autobus autobus : autobuses) {
            autobus.setCapacidad(40); // Resetear la capacidad a 40
            autobus.getReservas().clear(); // Limpiar las reservas asociadas
            autobusRepository.save(autobus);
        }
    }
}

