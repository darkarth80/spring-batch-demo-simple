package com.darkarth.spring.boot.batch.demo.config;

import com.darkarth.spring.boot.batch.demo.configuracion.ConfiguracionBatch;
import com.darkarth.spring.boot.batch.demo.configuracion.ConfiguracionJob;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({ConfiguracionBatch.class, ConfiguracionJob.class})
@Configuration
public class ConfiguracionBatchTest {

    @Autowired
    private Job demoSimpleJob;

    @Bean
    public JobLauncherTestUtils jobLauncherTestUtils() throws NoSuchJobException {

        JobLauncherTestUtils jltu = new JobLauncherTestUtils();

        jltu.setJob(demoSimpleJob);

        return jltu;

    }

}