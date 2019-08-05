package com.example.ale.misactivos.entidades;

public class Edificios {
    private int id;
    private String nombreedificio;

    public Edificios(int id, String nombreedificio) {
        this.id = id;
        this.nombreedificio = nombreedificio;
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
}
