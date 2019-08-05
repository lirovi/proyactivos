package com.example.ale.misactivos.entidades;

public class Detdevoluciones {

    private int id;
    private int devolucionid;
    private String activoid;
    private String fecha;;
    private int estadoid;
    private int observacionid;
    private int motivoid;

    public Detdevoluciones(int id, int devolucionid, String activoid, String fecha, int estadoid, int observacionid, int motivoid) {
        this.id = id;
        this.devolucionid = devolucionid;
        this.activoid = activoid;
        this.fecha = fecha;
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

    public int getDevolucionid() {
        return devolucionid;
    }

    public void setDevolucionid(int devolucionid) {
        this.devolucionid = devolucionid;
    }

    public String getActivoid() {
        return activoid;
    }

    public void setActivoid(String activoid) {
        this.activoid = activoid;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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
