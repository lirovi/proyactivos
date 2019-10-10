package com.example.ale.misactivos.entidades;

public class Oficinas {
    private int id;
    private String nombreoficina;
    private String edificioid;
   private String estado;

    public Oficinas(int id, String nombreoficina, String edificioid, String estado) {
        this.id = id;
        this.nombreoficina = nombreoficina;
        this.edificioid = edificioid;
        this.estado= estado;
    }
    public Oficinas(int id, String nombreoficina, String edificioid) {
        this.id = id;
        this.nombreoficina = nombreoficina;
        this.edificioid = edificioid;

    }
    public Oficinas(String nombreoficina, String edificioid) {

        this.nombreoficina = nombreoficina;
        this.edificioid = edificioid;
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

    public String getEdificioid() {
        return edificioid;
    }

    public void setEdificioid(String edificioid) {
        this.edificioid = edificioid;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
