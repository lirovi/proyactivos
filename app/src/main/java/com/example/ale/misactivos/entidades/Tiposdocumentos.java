package com.example.ale.misactivos.entidades;

public class Tiposdocumentos {
    private int id;
    private String nombredoc;
    private String sigla;

    public Tiposdocumentos(int id, String nombredoc, String sigla) {
        this.id = id;
        this.nombredoc = nombredoc;
        this.sigla = sigla;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombredoc() {
        return nombredoc;
    }

    public void setNombredoc(String nombredoc) {
        this.nombredoc = nombredoc;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}
