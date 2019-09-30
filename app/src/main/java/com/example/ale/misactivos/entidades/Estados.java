package com.example.ale.misactivos.entidades;

public class Estados {
    private int id;
    private String nombre;
    private String estado;


    public Estados( String nombre) {
        this.nombre = nombre;
    }
    public Estados(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    public Estados(int id, String nombre,String estado) {
        this.id = id;
        this.nombre = nombre;
        this.estado= estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
