package com.darkarth.spring.boot.batch.demo.configuracion;

import com.darkarth.spring.boot.batch.demo.modelo.Fila;
import com.darkarth.spring.boot.batch.demo.util.FilaItemProcessor;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.builder.FaultTolerantStepBuilder;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class ConfiguracionJob {

    @Bean
    public Step demoSimpleStep(StepBuilderFactory stepBuilders) {

        StepBuilder sb = stepBuilders.get("demoSimpleStep");

        //Separo los pasos en distintas variables para efectos de mejor lectura.
        //Al final puede ponerse en una sola línea.

        FaultTolerantStepBuilder<Fila, String> ssbChunk = sb.<Fila, String>chunk(10).faultTolerant();

        FaultTolerantStepBuilder<Fila, String> ssbLector = ssbChunk.reader(lector()).faultTolerant();

        FaultTolerantStepBuilder<Fila, String> ssbProcesador = ssbLector.processor(procesador()).faultTolerant();
        
        FaultTolerantStepBuilder<Fila, String> ssbEscritor = ssbProcesador.writer(escritor()).faultTolerant();

        return ssbEscritor.build();
    }

    @Bean
    public Job demoSimpleJob(JobBuilderFactory jobBuilders, StepBuilderFactory stepBuilders) {
        Step sds = demoSimpleStep(stepBuilders);
        return jobBuilders.get("demoSimpleJob").start(sds).build();
    }

    @Bean
    public FlatFileItemReader<Fila> lector() {
        return new FlatFileItemReaderBuilder<Fila>()
        .name("filaItemReader")
        .resource(new ClassPathResource("input/filas.csv"))
        .delimited().names(new String[] {"columna1", "columna2"})
        .targetType(Fila.class).build();
    }

    @Bean
    public FilaItemProcessor procesador() {
        return new FilaItemProcessor();
    }

    @Bean
    public FlatFileItemWriter<String> escritor() {
        return new FlatFileItemWriterBuilder<String>()
        .name("resultadoItemWriter")
        .resource(new FileSystemResource("target/output/resultado.log"))
        .lineAggregator(new PassThroughLineAggregator<>()).build();
    }

}