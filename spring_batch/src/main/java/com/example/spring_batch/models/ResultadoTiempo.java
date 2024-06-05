package com.example.spring_batch.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResultadoTiempo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String localidad;
    private int mes;
    private int año;
    private int numeroMediciones;
    private double temperaturaMedia;
    private String riesgo;

    public ResultadoTiempo(String string, int anInt, int anInt1, int anInt2, double aDouble, String string1) {
        this.localidad = string;
        this.mes = anInt;
        this.año = anInt1;
        this.numeroMediciones = anInt2;
        this.temperaturaMedia = aDouble;
        this.riesgo = string1;
    }
}
