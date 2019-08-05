package com.example.ale.misactivos.entidades;

public class Detcustodias {
    private int id;
    private int custodiaid;
    private String activoid;
    private String fecha_reg;;
    private int estadoid;
    private int observacionid;
    private int motivoid;

    public Detcustodias(int id, int custodiaid, String activoid, String fecha_reg, int estadoid, int observacionid, int motivoid) {
        this.id = id;
        this.custodiaid = custodiaid;
        this.activoid = activoid;
        this.fecha_reg = fecha_reg;
        this.estadoid = estadoid;
        this.observacionid = observacionid;
        this.motivoid = motivoid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustodiaid() {
        return custodiaid;
    }

    public void setCustodiaid(int custodiaid) {
        this.custodiaid = custodiaid;
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

    public int getMotivoid() {
        return motivoid;
    }

    public void setMotivoid(int motivoid) {
        this.motivoid = motivoid;
    }
}
