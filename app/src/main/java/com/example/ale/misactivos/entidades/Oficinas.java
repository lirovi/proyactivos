package com.example.ale.misactivos.entidades;

public class Oficinas {
    private int id;
    private String nombreoficina;
    private int dptoid;

    public Oficinas(int id, String nombreoficina, int dptoid) {
        this.id = id;
        this.nombreoficina = nombreoficina;
        this.dptoid = dptoid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreoficina() {
        return nombreoficina;
    }

    public void setNombreoficina(String nombreoficina) {
        this.nombreoficina = nombreoficina;
    }

    public int getDptoid() {
        return dptoid;
    }

    public void setDptoid(int dptoid) {
        this.dptoid = dptoid;
    }
}
