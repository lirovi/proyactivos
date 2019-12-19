package com.example.ale.misactivos.entidades;

public class Custodias {
    private int id;
    private String cpbte;
    private int gestion;
    private String fechacustodia;
    private String custodioid;
    private String edificioid;
    private String glosa;
    private String estado;

    public Custodias(int id, String cpbte, int gestion, String fechacustodia, String custodioid, String edificioid, String glosa, String estado) {
        this.id = id;
        this.cpbte = cpbte;
        this.gestion = gestion;
        this.fechacustodia = fechacustodia;
        this.custodioid = custodioid;
        this.edificioid = edificioid;
        this.glosa = glosa;
        this.estado = estado;
    }
    public Custodias( String cpbte, int gestion, String fechacustodia, String custodioid, String edificioid, String glosa) {

        this.cpbte = cpbte;
        this.gestion = gestion;
        this.fechacustodia = fechacustodia;
        this.custodioid = custodioid;
        this.edificioid = edificioid;
        this.glosa = glosa;
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

    public String getFechacustodia() {
        return fechacustodia;
    }

    public void setFechacustodia(String fechacustodia) {
        this.fechacustodia = fechacustodia;
    }

    public String getCustodioid() {
        return custodioid;
    }

    public void setCustodioid(String custodioid) {
        this.custodioid = custodioid;
    }

    public String getedificioid() {
        return edificioid;
    }

    public void setedificioid(String edificioid) {
        this.edificioid = edificioid;
    }

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
