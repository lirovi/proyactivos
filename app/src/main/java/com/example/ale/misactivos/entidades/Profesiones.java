package com.example.ale.misactivos.entidades;

public class Profesiones {
    private int id;
    private String nombreprof;

    public Profesiones(int id, String nombreprof) {
        this.id = id;
        this.nombreprof = nombreprof;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreprof() {
        return nombreprof;
    }

    public void setNombreprof(String nombreprof) {
        this.nombreprof = nombreprof;
    }
}
