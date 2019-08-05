package com.example.ale.misactivos.entidades;

public class Devoluciones {
    private int id;
    private String fecha;
    private int funcionarioid;
    private int oficinaid;
    private String glosa;

    public Devoluciones(int id, String fecha, int funcionarioid, int oficinaid, String glosa) {
        this.id = id;
        this.fecha = fecha;
        this.funcionarioid = funcionarioid;
        this.oficinaid = oficinaid;
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

    public int getFuncionarioid() {
        return funcionarioid;
    }

    public void setFuncionarioid(int funcionarioid) {
        this.funcionarioid = funcionarioid;
    }

    public int getOficinaid() {
        return oficinaid;
    }

    public void setOficinaid(int oficinaid) {
        this.oficinaid = oficinaid;
    }

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }
}
