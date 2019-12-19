package com.example.ale.misactivos.entidades;

public class Detcustodias {
    private int id;
    private String cpbte ;
    private int gestion;
    private String activoid;
    private String estadoactual;
    private String estado;

    public Detcustodias(int id, String cpbte, int gestion, String activoid, String estadoactual, String estado) {
        this.id= id;
        this.cpbte = cpbte;
        this.gestion = gestion;
        this.activoid = activoid;
        this.estadoactual = estadoactual;
        this.estado = estado;
    }
    public Detcustodias(String cpbte, int gestion, String activoid, String estadoactual, String estado) {
        this.cpbte = cpbte;
        this.gestion = gestion;
        this.activoid = activoid;
        this.estadoactual = estadoactual;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpbte() {
        return cpbte;
    }

    public void setCpbte(String cpbte) {
        this.cpbte = cpbte;
    }

    public int getGestion() {
        return gestion;
    }

    public void setGestion(int gestion) {
        this.gestion = gestion;
    }

    public String getActivoid() {
        return activoid;
    }

    public void setActivoid(String activoid) {
        this.activoid = activoid;
    }

    public String getEstadoactual() {
        return estadoactual;
    }

    public void setEstadoactual(String estadoactual) {
        this.estadoactual = estadoactual;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
