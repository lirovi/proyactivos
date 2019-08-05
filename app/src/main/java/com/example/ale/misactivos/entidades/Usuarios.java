package com.example.ale.misactivos.entidades;

public class Usuarios {
    private int id ;
    private String nombre;
    private String apellido;
    private String usuaario;
    private String password;

    public Usuarios(int id, String nombre, String apellido, String usuaario, String password) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuaario = usuaario;
        this.password = password;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsuaario() {
        return usuaario;
    }

    public void setUsuaario(String usuaario) {
        this.usuaario = usuaario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
