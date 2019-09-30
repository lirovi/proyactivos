package com.example.ale.misactivos.entidades;

public class Funcionarios {
    private int id;
    private String nombre;
    private String apellidou;
    private String apellidod;
    private String direccion;
    private String telefono;
    private String cargoid;
    private String profesionid;
    private String tipodocid;
    private String nrodoc;
    private String nacionalidad;
    private String sexo;

    public Funcionarios(int id, String nombre, String apellidou) {
        this.id = id;
        this.nombre = nombre;
        this.apellidou = apellidou;
    }
    public Funcionarios( String nombre, String apellidou, String apellidod, String direccion, String telefono, String cargoid, String profesionid, String tipodocid, String nrodoc, String nacionalidad, String sexo) {

        this.nombre = nombre;
        this.apellidou = apellidou;
        this.apellidod = apellidod;
        this.direccion = direccion;
        this.telefono = telefono;
        this.cargoid = cargoid;
        this.profesionid = profesionid;
        this.tipodocid = tipodocid;
        this.nrodoc = nrodoc;
        this.nacionalidad = nacionalidad;
        this.sexo = sexo;
    }
    public Funcionarios(int id, String nombre, String apellidou, String apellidod, String direccion, String telefono, String cargoid, String profesionid, String tipodocid, String nrodoc, String nacionalidad, String sexo) {
        this.id = id;
        this.nombre = nombre;
        this.apellidou = apellidou;
        this.apellidod = apellidod;
        this.direccion = direccion;
        this.telefono = telefono;
        this.cargoid = cargoid;
        this.profesionid = profesionid;
        this.tipodocid = tipodocid;
        this.nrodoc = nrodoc;
        this.nacionalidad = nacionalidad;
        this.sexo = sexo;
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

    public String getApellidou() {
        return apellidou;
    }

    public void setApellidou(String apellidou) {
        this.apellidou = apellidou;
    }

    public String getApellidod() {
        return apellidod;
    }

    public void setApellidod(String apellidod) {
        this.apellidod = apellidod;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCargoid() {
        return cargoid;
    }

    public void setCargoid(String cargoid) {
        this.cargoid = cargoid;
    }

    public String getProfesionid() {
        return profesionid;
    }

    public void setProfesionid(String profesionid) {
        this.profesionid = profesionid;
    }

    public String getTipodocid() {
        return tipodocid;
    }

    public void setTipodocid(String tipodocid) {
        this.tipodocid = tipodocid;
    }

    public String getNrodoc() {
        return nrodoc;
    }

    public void setNrodoc(String nrodoc) {
        this.nrodoc = nrodoc;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
