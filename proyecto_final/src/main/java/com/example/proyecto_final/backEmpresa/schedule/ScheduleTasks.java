package com.example.proyecto_final.backEmpresa.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.time.LocalDate;

@Component
public class ScheduleTasks {
    @Autowired
    private ExportService exportService;

    @Autowired
    private ResetService resetService;

    @Scheduled(cron = "0 0 0 * * ?") // Ejecutar a medianoche todos los días
    public void performDailyTasks() {
        try {
            exportService.exportReservasToCSV("C:\\Users\\angel.vaquero\\Desktop\\TestGuardado\\reservas_"+LocalDate.now()+".csv");
            resetService.resetDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
