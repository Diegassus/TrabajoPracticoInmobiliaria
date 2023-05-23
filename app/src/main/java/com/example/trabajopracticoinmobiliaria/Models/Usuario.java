package com.example.trabajopracticoinmobiliaria.Models;

public class Usuario {
    private String Correo;
    private String Clave;

    public Usuario(String nombre, String clave) {
        this.Correo = nombre;
        this.Clave = clave;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        Clave = clave;
    }
}
