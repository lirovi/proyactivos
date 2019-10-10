package com.example.ale.misactivos.entidades;

public class Cargos {
    private int id;
    private String nombrecargo;
    private String estado;

    public Cargos(int id, String nombrecargo, String estado) {
        this.id = id;
        this.nombrecargo = nombrecargo;
        this.estado= estado;
    }
    public Cargos(int id, String nombrecargo) {
        this.id = id;
        this.nombrecargo = nombrecargo;
    }
    public Cargos(String nombrecargo) {
        this.nombrecargo = nombrecargo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombrecargo() {
        return nombrecargo;
    }

    public void setNombrecargo(String nombrecargo) {
        this.nombrecargo = nombrecargo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
