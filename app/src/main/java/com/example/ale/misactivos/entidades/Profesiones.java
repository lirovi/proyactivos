package com.example.ale.misactivos.entidades;

public class Profesiones {
    private int id;
    private String nombreprof;
    private String estado;


    public Profesiones(){

    }
    public Profesiones( String nombreprof) {
        this.nombreprof = nombreprof;
    }
    public Profesiones(int id, String nombreprof) {
        this.id = id;
        this.nombreprof = nombreprof;
    }
    public Profesiones(int id, String nombreprof, String estado) {
        this.id = id;
        this.nombreprof = nombreprof;
        this.estado= estado;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
