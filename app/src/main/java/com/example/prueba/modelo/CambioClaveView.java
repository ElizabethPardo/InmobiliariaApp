package com.example.prueba.modelo;

import java.io.Serializable;

public class CambioClaveView implements Serializable {

private  String claveVieja;
private String claveNueva;
private String claveRepeticion;

    public CambioClaveView() {

    }

    public CambioClaveView(String claveVieja, String claveNueva, String claveRepeticion) {
        this.claveVieja = claveVieja;
        this.claveNueva = claveNueva;
        this.claveRepeticion = claveRepeticion;
    }

    public String getClaveVieja() {
        return claveVieja;
    }

    public void setClaveVieja(String claveVieja) {
        this.claveVieja = claveVieja;
    }

    public String getClaveNueva() {
        return claveNueva;
    }

    public void setClaveNueva(String claveNueva) {
        this.claveNueva = claveNueva;
    }

    public String getClaveRepeticion() {
        return claveRepeticion;
    }

    public void setClaveRepeticion(String claveRepeticion) {
        this.claveRepeticion = claveRepeticion;
    }
}
