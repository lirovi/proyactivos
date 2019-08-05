package com.example.ale.misactivos.entidades;

public class Inventarios {
    private int id;
    private String fecha;
    private String glosa;

    public Inventarios(int id, String fecha, String glosa) {
        this.id = id;
        this.fecha = fecha;
        this.glosa = glosa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }
}
