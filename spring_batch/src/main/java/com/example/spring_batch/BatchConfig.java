package com.example.spring_batch;

import com.example.spring_batch.job.JobCompletionNotificationListener;
import com.example.spring_batch.job.TiempoItemProcessor;
import com.example.spring_batch.job.TiempoSimpleProcessor;
import com.example.spring_batch.models.ResultadoTiempo;
import com.example.spring_batch.models.ResultadoTiempoRepository;
import com.example.spring_batch.models.Tiempo;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing(dataSourceRef = "batchDataSource", transactionManagerRef = "batchTransactionManager")
@RequiredArgsConstructor
public class BatchConfig extends DefaultBatchConfiguration {

    private static final String ERROR_FILE_PATH = "REGISTROS_ERRONEOS.CSV";

    private ResultadoTiempoRepository resultadoTiempoRepository;

    @Autowired
    public BatchConfig(ResultadoTiempoRepository resultadoTiempoRepository) {
        this.resultadoTiempoRepository = resultadoTiempoRepository;
    }

    @Bean
    public JobLauncher jobLauncher(JobRepository jobRepository) throws Exception {
        TaskExecutorJobLauncher jobLauncher = new TaskExecutorJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
        jobLauncher.afterPropertiesSet();
        return jobLauncher;
    }

    @Bean
    public LineMapper<Tiempo> lineMapper() {
        DefaultLineMapper<Tiempo> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(",");
        tokenizer.setNames("localidad","fecha","temperatura");
        tokenizer.setStrict(false);

        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(new CustomBeanWrapperFieldSetMapper());

        return lineMapper;
    }

    @Bean
    public FlatFileItemReader<Tiempo> reader() {
        FlatFileItemReader<Tiempo> reader = new FlatFileItemReader<>();
        reader.setName("tiempoItemReader");
        reader.setLinesToSkip(1);
        reader.setResource(new ClassPathResource("datos.csv"));
        reader.setLineMapper(lineMapper());
        return reader;
    }

    @Bean
    public FlatFileItemWriter<Tiempo> errorWriter() {
        FileSystemResource resource = new FileSystemResource(ERROR_FILE_PATH);
        System.out.println("Error file path: " + resource.getFile().getAbsolutePath());
        return new FlatFileItemWriterBuilder<Tiempo>()
                .name("errorItemWriter")
                .resource(resource)
                .delimited()
                .names("localidad", "fecha", "temperatura")
                .build();
    }


    @Bean
    public TiempoItemProcessor processor() {
        return new TiempoItemProcessor();
    }

    @Bean
    public TiempoSimpleProcessor processorSimple() {
        return new TiempoSimpleProcessor();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("importVistorsStep", jobRepository)
                .<Tiempo, ResultadoTiempo>chunk(100, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(items -> {
                })
                .build();
    }

    @Bean
    public Job importTiempoResultadoJob(Step step1, JobCompletionNotificationListener listener, JobRepository jobRepository) {
        return new JobBuilder("importTiempoResultadoJob", jobRepository)
                .listener(listener)
                .start(step1)
                .build();
    }

    @Bean
    public Step step2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("importVistorsStep", jobRepository)
                .<Tiempo, Tiempo>chunk(100, transactionManager)
                .reader(reader())
                .processor(processorSimple())
                .writer(errorWriter())
                .build();

    }


    @Bean
    public Job filterHighTemperaturesJob(Step step2, JobCompletionNotificationListener listener, JobRepository jobRepository) {
        return new JobBuilder("filterHighTemperaturesJob", jobRepository)
                .listener(listener)
                .start(step2)
                .build();
    }

}
