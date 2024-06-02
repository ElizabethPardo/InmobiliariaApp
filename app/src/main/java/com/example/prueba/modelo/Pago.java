package com.example.prueba.modelo;

import java.io.Serializable;

public class Pago implements Serializable {

    private int id;
    private int nroPago;
    private String fechaPago;
    private Double importe;
    private int contratoId;
    private Contrato contrato;


    public Pago() {
    }

    public Pago(int nroPago, String fechaPago, Double importe) {
        this.nroPago = nroPago;
        this.fechaPago = fechaPago;
        this.importe = importe;

    }

    public Pago(int id, int nroPago, String fechaPago, Double importe) {
        this.id = id;
        this.nroPago = nroPago;
        this.fechaPago = fechaPago;
        this.importe = importe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNroPago() {
        return nroPago;
    }

    public void setNroPago(int nroPago) {
        this.nroPago = nroPago;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public int getContratoId() {
        return contratoId;
    }

    public void setContratoId(int contratoId) {
        this.contratoId = contratoId;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
}
