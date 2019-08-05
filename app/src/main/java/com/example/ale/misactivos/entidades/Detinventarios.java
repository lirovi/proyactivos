package com.example.ale.misactivos.entidades;

public class Detinventarios {

    private int id;
    private int inventarioid;
    private String activoid;
    private String fecha_reg;;
    private int estadoid;
    private int observacionid;
    private String verificado;

    public Detinventarios(int id, int inventarioid, String activoid, String fecha_reg, int estadoid, int observacionid, String verificado) {
        this.id = id;
        this.inventarioid = inventarioid;
        this.activoid = activoid;
        this.fecha_reg = fecha_reg;
        this.estadoid = estadoid;
        this.observacionid = observacionid;
        this.verificado = verificado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInventarioid() {
        return inventarioid;
    }

    public void setInventarioid(int inventarioid) {
        this.inventarioid = inventarioid;
    }

    public String getActivoid() {
        return activoid;
    }

    public void setActivoid(String activoid) {
        this.activoid = activoid;
    }

    public String getFecha_reg() {
        return fecha_reg;
    }

    public void setFecha_reg(String fecha_reg) {
        this.fecha_reg = fecha_reg;
    }

    public int getEstadoid() {
        return estadoid;
    }

    public void setEstadoid(int estadoid) {
        this.estadoid = estadoid;
    }

    public int getObservacionid() {
        return observacionid;
    }

    public void setObservacionid(int observacionid) {
        this.observacionid = observacionid;
    }

    public String getVerificado() {
        return verificado;
    }

    public void setVerificado(String verificado) {
        this.verificado = verificado;
    }
}
