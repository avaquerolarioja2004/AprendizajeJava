package com.example.spring_batch.job;

import com.example.spring_batch.models.ResultadoTiempo;
import com.example.spring_batch.models.ResultadoTiempoRepository;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    @Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
    }
    @Autowired
    private TiempoItemProcessor tiempoItemProcessor;

    @Autowired
    private ResultadoTiempoRepository resultadoTiempoRepository;

    @Override
    public void afterJob(JobExecution jobExecution) {
        Map<String, ResultadoTiempo> resultadosFinales = tiempoItemProcessor.getResultadosFinales();
        resultadoTiempoRepository.saveAll(resultadosFinales.values());
    }
}
