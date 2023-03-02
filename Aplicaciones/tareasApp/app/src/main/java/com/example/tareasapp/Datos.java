package com.example.tareasapp;

public class Datos {
    public int estado;
    public String nombre;

    public Datos(int estado, String nombre) {
        this.estado = estado;
        this.nombre = nombre;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
