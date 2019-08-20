package com.darkarth.spring.boot.batch.demo.modelo;

public class Fila {

    private String columna1;

    private String columna2;

    public Fila() {}

    public String getColumna1() {
        return this.columna1;
    }

    public void setColumna1(String columna1) {
        this.columna1 = columna1;
    }

    public String getColumn2() {
        return this.columna2;
    }

    public void setColumna2(String columna2) {
        this.columna2 = columna2;
    }

    public String toString() {
        return columna1 + " - " + columna2;
    }

}