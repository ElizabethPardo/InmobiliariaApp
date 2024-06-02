package com.example.prueba.modelo;

import java.io.Serializable;

public class Contrato implements Serializable {

    private int id;
    private String fechaDesde;
    private String fechaHasta;
    private  int inquilinoId;
    private double monto;
    private Inquilino inquilino;
    private  int inmuebleId;
    private Inmueble inmueble;


    public Contrato(int id,String fechaIngreso, String fechaSalida,double monto, Inmueble innmueble, Inquilino inquilino) {
        this.id=id;
        this.fechaDesde = fechaIngreso;
        this.fechaHasta = fechaSalida;
        this.inmueble = innmueble;
        this.inquilino = inquilino;
        this.monto=monto;
    }

    public Contrato() {
    }

    public Contrato(String fechaIngreso, String fechaSalida, Inquilino inquilino) {
        this.fechaDesde = fechaIngreso;
        this.fechaHasta = fechaSalida;
        this.inquilino=inquilino;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(String fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public String getFechaHasta() {
        return fechaHasta;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void setFechaHasta(String fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public int getInquilinoId() {
        return inquilinoId;
    }

    public void setInquilinoId(int inquilinoId) {
        this.inquilinoId = inquilinoId;
    }

    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public int getInmuebleId() {
        return inmuebleId;
    }

    public void setInmuebleId(int inmuebleId) {
        this.inmuebleId = inmuebleId;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }
}
