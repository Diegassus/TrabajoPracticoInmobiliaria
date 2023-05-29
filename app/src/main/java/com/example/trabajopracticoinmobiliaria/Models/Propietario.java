package com.example.trabajopracticoinmobiliaria.Models;

import androidx.annotation.NonNull;

public class Propietario {
    private String nombre;
    private String apellido;
    private String clave;
    private String correo;
    private String telefono;
    private int id;
    private String dni;
    private boolean estado;

    public Propietario(String nombre, String apellido, String clave, String correo, String telefono, int id, String dni, boolean estado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.clave = clave;
        this.correo = correo;
        this.telefono = telefono;
        this.id = id;
        this.dni = dni;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @NonNull
    @Override
    public String toString() {
        return "Propietario{" + "nombre=" + nombre + ", apellido=" + apellido + ", clave=" + clave + ", correo=" + correo + ", telefono=" + telefono + ", id=" + id + ", dni=" + dni + ", estado=" + estado + '}';
    }
}

