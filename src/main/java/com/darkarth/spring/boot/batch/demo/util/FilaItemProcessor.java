package com.darkarth.spring.boot.batch.demo.util;

import com.darkarth.spring.boot.batch.demo.modelo.Fila;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class FilaItemProcessor implements ItemProcessor<Fila, String> {

  private static final Logger LOGGER = LoggerFactory.getLogger(FilaItemProcessor.class);

  @Override
  public String process(Fila fila) throws Exception {
    String filas = "Filas: " + fila.toString() + ".";
    LOGGER.info("Resultado: '{}'", filas);
    return filas;
  }
  
}