package com.example.spring_batch;

import com.example.spring_batch.models.Tiempo;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomBeanWrapperFieldSetMapper extends BeanWrapperFieldSetMapper<Tiempo> {
    @Override
    public Tiempo mapFieldSet(FieldSet fieldSet) {
        Tiempo tiempo = new Tiempo();
        String fechaString = fieldSet.readString("fecha");
        LocalDate fecha = LocalDate.parse(fechaString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        tiempo.setFecha(fecha);
        tiempo.setLocalidad(fieldSet.readString("localidad"));
        tiempo.setTemperatura(fieldSet.readInt("temperatura"));
        return tiempo;
    }
}

