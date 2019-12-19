package com.example.ale.misactivos.entidades;

public class cambios {

    private int id;
    private float dolar;
    private String fecha;
    private String fecha_modificacion;
    private float ufv;
    private String usuario;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public cambios(int id, float dolar, String fecha, String fecha_modificacion, float ufv, String usuario) {
        this.id = id;
        this.dolar = dolar;
        this.fecha = fecha;
        this.fecha_modificacion = fecha_modificacion;
        this.ufv = ufv;
        this.usuario = usuario;
    }

    public float getDolar() {
        return dolar;
    }

    public void setDolar(float dolar) {
        this.dolar = dolar;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha_modificacion() {
        return fecha_modificacion;
    }

    public void setFecha_modificacion(String fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }

    public float getUfv() {
        return ufv;
    }

    public void setUfv(float ufv) {
        this.ufv = ufv;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }



}