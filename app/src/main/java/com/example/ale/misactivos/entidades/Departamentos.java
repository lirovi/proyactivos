package com.example.ale.misactivos.entidades;

public class Departamentos {
    private int id;
    private String nombredpto;
    private int edificioid;

    public Departamentos(int id, String nombredpto, int edificioid) {
        this.id = id;
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
}
