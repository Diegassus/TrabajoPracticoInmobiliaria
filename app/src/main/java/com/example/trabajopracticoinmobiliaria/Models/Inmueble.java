package com.example.trabajopracticoinmobiliaria.Models;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Objects;

public class Inmueble implements Serializable{
    private String direccion;
    private int superficie, propietarioId, id, grupoId, uso, tipo, ambientes;
    private boolean disponible;
    private String lat, lng;
    private double precio;
    private Propietario duenio;
    private String foto;

    public Inmueble() {
    }

    public Inmueble(String direccion, int superficie, int propietarioId, int id, int grupoId, int uso, int tipo, int ambientes, boolean disponible, String lat, String lng, double precio, Propietario duenio, String foto) {
        this.direccion = direccion;
        this.superficie = superficie;
        this.propietarioId = propietarioId;
        this.id = id;
        this.grupoId = grupoId;
        this.uso = uso;
        this.tipo = tipo;
        this.ambientes = ambientes;
        this.disponible = disponible;
        this.lat = lat;
        this.lng = lng;
        this.precio = precio;
        this.duenio = duenio;
        this.foto = foto;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getSuperficie() {
        return superficie;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public int getPropietarioId() {
        return propietarioId;
    }

    public void setPropietarioId(int propietarioId) {
        this.propietarioId = propietarioId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(int grupoId) {
        this.grupoId = grupoId;
    }

    public int getUso() {
        return uso;
    }

    public void setUso(int uso) {
        this.uso = uso;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(int ambientes) {
        this.ambientes = ambientes;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Propietario getDuenio() {
        return duenio;
    }

    public void setDuenio(Propietario duenio) {
        this.duenio = duenio;
    }

    @NonNull
    @Override
    public String toString() {
        return this.direccion;
    }
}
