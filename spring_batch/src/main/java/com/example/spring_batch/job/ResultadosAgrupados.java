package com.example.spring_batch.job;

import com.example.spring_batch.models.ResultadoTiempo;
import com.example.spring_batch.models.Tiempo;

import java.util.HashMap;
import java.util.Map;

public class ResultadosAgrupados {
    private Map<String, ResultadoTiempo> resultados = new HashMap<>();

    public void agregarTiempo(Tiempo tiempo) {
        String key = tiempo.getLocalidad() + "-" + tiempo.getFecha().getMonthValue();
        ResultadoTiempo resultado = resultados.get(key);

        if (resultado == null) {
            // Crear un nuevo resultado si no existe
            String riesgo = calcularRiesgo(tiempo.getTemperatura());
            resultado = new ResultadoTiempo(
                    tiempo.getLocalidad(),
                    tiempo.getFecha().getMonthValue(),
                    tiempo.getFecha().getYear(),
                    1,
                    tiempo.getTemperatura(),
                    riesgo
            );
        } else {
            // Actualizar el resultado existente
            int numMediciones = resultado.getNumeroMediciones() + 1;
            double tempPromedio = ((resultado.getTemperaturaMedia() * resultado.getNumeroMediciones()) + tiempo.getTemperatura()) / numMediciones;
            String riesgo = calcularRiesgo(tempPromedio);

            resultado.setNumeroMediciones(numMediciones);
            resultado.setTemperaturaMedia(tempPromedio);
            resultado.setRiesgo(riesgo);
        }

        resultados.put(key, resultado);
    }

    public Map<String, ResultadoTiempo> getResultados() {
        return resultados;
    }

    private String calcularRiesgo(double temperatura) {
        if (temperatura >= 36) {
            return "ALTO";
        } else if (temperatura >= 32) {
            return "MEDIO";
        } else if (temperatura >= 24) {
            return "BAJO";
        } else {
            return "NINGUNO";
        }
    }
}

