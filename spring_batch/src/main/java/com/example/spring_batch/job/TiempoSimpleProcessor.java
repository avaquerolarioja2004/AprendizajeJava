package com.example.spring_batch.job;

import com.example.spring_batch.models.Tiempo;
import org.springframework.batch.item.ItemProcessor;

public class TiempoSimpleProcessor implements ItemProcessor<Tiempo, Tiempo> {
    @Override
    public Tiempo process(Tiempo item) throws Exception {
        if (item.getTemperatura() >= 55 || item.getTemperatura() <= -20) {
            return item;
        } else {
            return null;
        }
    }
}