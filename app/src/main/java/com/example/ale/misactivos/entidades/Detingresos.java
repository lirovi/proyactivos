package com.example.ale.misactivos.entidades;

public class Detingresos {
    private int id;
    private int ingresosid;
    private String fecha_reg;
    private String caracteristicas;
    private int observacionid;
    private int motivoid;

    public Detingresos(int id, int ingresosid, String fecha_reg, String caracteristicas, int observacionid, int motivoid) {
        this.id = id;
        this.ingresosid = ingresosid;
        this.fecha_reg = fecha_reg;
        this.caracteristicas = caracteristicas;
        this.observacionid = observacionid;
        this.motivoid = motivoid;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIngresosid() {
        return ingresosid;
    }

    public void setIngresosid(int ingresosid) {
        this.ingresosid = ingresosid;
    }

    public String getFecha_reg() {
        return fecha_reg;
    }

    public void setFecha_reg(String fecha_reg) {
        this.fecha_reg = fecha_reg;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
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
