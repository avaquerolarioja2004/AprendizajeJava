package com.example.spring_batch.job;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class JobRunner implements ApplicationRunner {

    private final Job importTiempoResultadoJob;
    private final Job filterHighTemperaturesJob;
    private final JobLauncher jobLauncher;

    @Autowired
    public JobRunner(Job importTiempoResultadoJob, Job filterHighTemperaturesJob, JobLauncher jobLauncher) {
        this.importTiempoResultadoJob = importTiempoResultadoJob;
        this.filterHighTemperaturesJob = filterHighTemperaturesJob;
        this.jobLauncher = jobLauncher;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        JobExecution importExecution = jobLauncher.run(importTiempoResultadoJob, jobParameters);

        while (importExecution.isRunning()) {
            Thread.sleep(1000);
        }

        jobLauncher.run(filterHighTemperaturesJob, jobParameters);
    }
}