package com.example.ale.misactivos.entidades;

public class Departamentos {
    private int id;
    private String nombredpto;
    private int edificioid;
    private String estado;

    public Departamentos( String nombredpto, int edificioid,String estado ) {
        this.nombredpto = nombredpto;
        this.edificioid = edificioid;
        this.estado= estado;
    }

    public Departamentos(int id, String nombredpto, int edificioid, String estado ) {
        this.id = id;
        this.nombredpto = nombredpto;
        this.edificioid = edificioid;
        this.estado= estado;
    }
    public Departamentos(int id, String nombredpto, int edificioid ) {
        this.id = id;
        this.nombredpto = nombredpto;
        this.edificioid = edificioid;

    }
    public Departamentos(String nombredpto, int edificioid ) {
        this.nombredpto = nombredpto;
        this.edificioid = edificioid;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombredpto() {
        return nombredpto;
    }

    public void setNombredpto(String nombredpto) {
        this.nombredpto = nombredpto;
    }

    public int getEdificioid() {
        return edificioid;
    }

    public void setEdificioid(int edificioid) {
        this.edificioid = edificioid;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
