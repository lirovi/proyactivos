package com.example.ale.misactivos.entidades;

public class Ingresos {
    private int id;
    private String fechaing;
    private String glosa;

    public Ingresos(int id, String fechaing, String glosa) {
        this.id = id;
        this.fechaing = fechaing;
        this.glosa = glosa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaing() {
        return fechaing;
    }

    public void setFechaing(String fechaing) {
        this.fechaing = fechaing;
    }

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }
}
