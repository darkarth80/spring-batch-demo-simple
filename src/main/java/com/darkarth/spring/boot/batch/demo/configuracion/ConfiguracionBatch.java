package com.darkarth.spring.boot.batch.demo.configuracion;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class ConfiguracionBatch extends DefaultBatchConfigurer {

    /**
     * Este método vacío permite configurar el proyecto sin ningún 
     * datasource. De esa forma Spring Batch no envía error al iniciarlo.
     */
    @Override
    public void setDataSource(DataSource dataSource) {}

}