package com.example.ale.misactivos.entidades;

import java.io.Serializable;

public class Edificios  {
    private int id;
    private String nombreedificio;
    private String estado;

    public Edificios(String nombreedificio) {
        this.nombreedificio = nombreedificio;
    }
    public Edificios(int id, String nombreedificio) {
        this.id = id;
        this.nombreedificio = nombreedificio;
    }

    public Edificios(int id, String nombreedificio, String estado) {
        this.id = id;
        this.nombreedificio = nombreedificio;
        this. estado= estado;
    }

    public Edificios() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreedificio() {
        return nombreedificio;
    }

    public void setNombreedificio(String nombreedificio) {
        this.nombreedificio = nombreedificio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
