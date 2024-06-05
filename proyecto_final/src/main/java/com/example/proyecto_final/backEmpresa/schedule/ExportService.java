package com.example.proyecto_final.backEmpresa.schedule;

import com.example.proyecto_final.backEmpresa.repositories.ReservaRepository;
import com.example.proyecto_final.commons.entityReserva.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class ExportService {
    @Autowired
    private ReservaRepository reservaRepository;

    public void exportReservasToCSV(String filePath) throws IOException {
        List<Reserva> reservas = reservaRepository.findAll();

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append("idReserva;idCliente;clienteNombre;clienteApellidos;clienteTelefono;clienteEmail;fecha;idAutobus;hora;destino\n");

            for (Reserva reserva : reservas) {
                writer.append(String.valueOf(reserva.getIdReserva())).append(';')
                        .append(String.valueOf(reserva.getCliente().getIdCliente())).append(';')
                        .append(reserva.getCliente().getNombre()).append(';')
                        .append(reserva.getCliente().getApellido()).append(';')
                        .append(reserva.getCliente().getTelefono()).append(';')
                        .append(reserva.getCliente().getEmail()).append(';')
                        .append(reserva.getFecha().toString()).append(';')
                        .append(String.valueOf(reserva.getAutobus().getIdAutobus())).append(';')
                        .append(String.valueOf(reserva.getAutobus().getHora())).append(';')
                        .append(reserva.getAutobus().getDestino()).append('\n');
            }
        }
    }
}

