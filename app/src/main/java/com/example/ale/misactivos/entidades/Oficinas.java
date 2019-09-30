package com.example.ale.misactivos.entidades;

public class Oficinas {
    private int id;
    private String nombreoficina;
    private int oficinaid;
   private String estado;

    public Oficinas(int id, String nombreoficina, int oficinaid, String estado) {
        this.id = id;
        this.nombreoficina = nombreoficina;
        this.oficinaid = oficinaid;
        this.estado= estado;
    }
    public Oficinas(int id, String nombreoficina, int oficinaid) {
        this.id = id;
        this.nombreoficina = nombreoficina;
        this.oficinaid = oficinaid;

    }
    public Oficinas(String nombreoficina, int oficinaid) {

        this.nombreoficina = nombreoficina;
        this.oficinaid = oficinaid;
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

    public int getOficinaid() {
        return oficinaid;
    }

    public void setOficinaid(int oficinaid) {
        this.oficinaid = oficinaid;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
