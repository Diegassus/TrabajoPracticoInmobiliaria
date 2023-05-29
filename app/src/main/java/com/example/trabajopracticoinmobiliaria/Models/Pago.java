package com.example.trabajopracticoinmobiliaria.Models;

import java.io.Serializable;

public class Pago implements Serializable {
    private int id;
    private int nro;
    private Contrato contrato;
    private double importe;
    private String fecha;

    public Pago(int id, int nro, Contrato contrato, double importe, String fecha) {
        this.id = id;
        this.nro = nro;
        this.contrato = contrato;
        this.importe = importe;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
