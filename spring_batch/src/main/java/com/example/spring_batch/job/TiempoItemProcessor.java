package com.example.spring_batch.job;

import com.example.spring_batch.models.ResultadoTiempoRepository;
import com.example.spring_batch.models.Tiempo;
import com.example.spring_batch.models.ResultadoTiempo;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoField;
import java.util.Map;

public class TiempoItemProcessor implements ItemProcessor<Tiempo, ResultadoTiempo> {

    private ResultadosAgrupados resultadosAgrupados = new ResultadosAgrupados();
    @Autowired
    ResultadoTiempoRepository resultadoTiempoRepository;

    @Override
    public ResultadoTiempo process(final Tiempo tiempo) {
        if (tiempo.getTemperatura() > 50 || tiempo.getTemperatura() < -20) {
            return null;
        }

        resultadosAgrupados.agregarTiempo(tiempo);

        // Devuelve null para que no se procese la salida directamente,
        // ya que estamos acumulando los resultados en `resultadosAgrupados`.
        return null;
    }

    public Map<String, ResultadoTiempo> getResultadosFinales() {
        return resultadosAgrupados.getResultados();
    }
}
