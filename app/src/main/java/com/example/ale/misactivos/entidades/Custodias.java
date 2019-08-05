package com.example.ale.misactivos.entidades;

public class Custodias {
    private int id;
    private String fechacustodia;
    private int funcionarioid;
    private int oficinaid;
    private String glosa;

    public Custodias(int id, String fechacustodia, int funcionarioid, int oficinaid, String glosa) {
        this.id = id;
        this.fechacustodia = fechacustodia;
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

    public String getFechacustodia() {
        return fechacustodia;
    }

    public void setFechacustodia(String fechacustodia) {
        this.fechacustodia = fechacustodia;
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
