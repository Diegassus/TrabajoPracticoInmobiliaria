package com.example.trabajopracticoinmobiliaria.Models;

import java.io.Serializable;
import java.util.Objects;

public class Contrato implements Serializable {
    private int id;
    private String desde;
    private String hasta;
    private double mensualidad;
    private Inquilino arrendatario;
    private Inmueble bien;

    public Contrato(int id, String desde, String hasta, double mensualidad, Inquilino arrendatario, Inmueble bien) {
        this.id = id;
        this.desde = desde;
        this.hasta = hasta;
        this.mensualidad = mensualidad;
        this.arrendatario = arrendatario;
        this.bien = bien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesde() {
        return desde;
    }

    public void setDesde(String desde) {
        this.desde = desde;
    }

    public String getHasta() {
        return hasta;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
    }

    public double getMensualidad() {
        return mensualidad;
    }

    public void setMensualidad(double mensualidad) {
        this.mensualidad = mensualidad;
    }

    public Inquilino getArrendatario() {
        return arrendatario;
    }

    public void setArrendatario(Inquilino arrendatario) {
        this.arrendatario = arrendatario;
    }

    public Inmueble getBien() {
        return bien;
    }

    public void setBien(Inmueble bien) {
        this.bien = bien;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contrato contrato = (Contrato) o;
        return id == contrato.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
