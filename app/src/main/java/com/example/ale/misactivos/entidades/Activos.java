package com.example.ale.misactivos.entidades;

import java.io.Serializable;

public class Activos implements Serializable {
    private String Codigo;
    private Integer CORRELATIVO;
    private String TIPO;
    private String DESCRIPCION;
    private String UNIDAD;
    private String FECHA_INGRESO;
    private String VALOR;
    private String VALOR_RESIDUAL;
    private String ESTADOFISICO;
    private String ESTADO_BD;
    private String OBSERVACION;
    private String GRUPO;
    private String AUXILIAR;
    private Integer GESTION_INGRESO;
    private String PARTIDA;
    private String GLOSA;
    private String COLOR;
    private String SERIE;
    private String MARCA;
    private String MODELO;
    private String PLACA;
    private Integer GESTION_BAJA;
    private String BAJA;
    private String UBI_GEOGRAFICA;
    private String ORIGEN;
    private String RUTA_IMAGEN;
    private String CAMBIO;
    private String ESTADO;

    public Activos()
    {

    }

    public Activos(String CODIGO, Integer CORRELATIVO, String TIPO, String DESCRIPCION) {
        this.Codigo = CODIGO;
        this.CORRELATIVO = CORRELATIVO;
        this.TIPO = TIPO;
        this.DESCRIPCION = DESCRIPCION;
    }
    public Activos(String CODIGO, String DESCRIPCION, String FECHA_INGRESO ) {
        this.Codigo = CODIGO;
        this.DESCRIPCION = DESCRIPCION;
        this.FECHA_INGRESO= FECHA_INGRESO;
    }
    public Activos(String CODIGO, String DESCRIPCION, String FECHA_INGRESO,String RUTA_IMAGEN ) {
        this.Codigo = CODIGO;
        this.DESCRIPCION = DESCRIPCION;
        this.RUTA_IMAGEN= RUTA_IMAGEN;
        this.FECHA_INGRESO= FECHA_INGRESO;
    }

    public Activos(String codigo, Integer CORRELATIVO, String TIPO, String DESCRIPCION, String UNIDAD, String FECHA_INGRESO, String VALOR, String VALOR_RESIDUAL, String ESTADOFISICO, String ESTADO_BD, String OBSERVACION, String GRUPO, String AUXILIAR, Integer GESTION_INGRESO, String PARTIDA, String GLOSA, String COLOR, String SERIE, String MARCA, String MODELO, String PLACA, String BAJA, Integer GESTION_BAJA, String UBI_GEOGRAFICA, String ORIGEN) {
        Codigo = codigo;
        this.CORRELATIVO = CORRELATIVO;
        this.TIPO = TIPO;
        this.DESCRIPCION = DESCRIPCION;
        this.UNIDAD = UNIDAD;
        this.FECHA_INGRESO = FECHA_INGRESO;
        this.VALOR = VALOR;
        this.VALOR_RESIDUAL = VALOR_RESIDUAL;
        this.ESTADOFISICO = ESTADOFISICO;
        this.ESTADO_BD = ESTADO_BD;
        this.OBSERVACION = OBSERVACION;
        this.GRUPO = GRUPO;
        this.AUXILIAR = AUXILIAR;
        this.GESTION_INGRESO = GESTION_INGRESO;
        this.PARTIDA = PARTIDA;
        this.GLOSA = GLOSA;
        this.COLOR = COLOR;
        this.SERIE = SERIE;
        this.MARCA = MARCA;
        this.MODELO = MODELO;
        this.PLACA = PLACA;
        this.BAJA = BAJA;
        this.GESTION_BAJA = GESTION_BAJA;
        this.UBI_GEOGRAFICA = UBI_GEOGRAFICA;
        this.ORIGEN = ORIGEN;
    }

    public Activos(String codigo, Integer CORRELATIVO, String TIPO, String DESCRIPCION, String UNIDAD, String FECHA_INGRESO, String VALOR, String VALOR_RESIDUAL, String ESTADOFISICO, String ESTADO_BD, String OBSERVACION, String GRUPO, String AUXILIAR, Integer GESTION_INGRESO, String PARTIDA, String GLOSA, String COLOR, String SERIE, String MARCA, String MODELO, String PLACA, String BAJA, Integer GESTION_BAJA, String UBI_GEOGRAFICA, String ORIGEN,String RUTA_IMAGEN, String CAMBIO, String ESTADO) {
        Codigo = codigo;
        this.CORRELATIVO = CORRELATIVO;
        this.TIPO = TIPO;
        this.DESCRIPCION = DESCRIPCION;
        this.UNIDAD = UNIDAD;
        this.FECHA_INGRESO = FECHA_INGRESO;
        this.VALOR = VALOR;
        this.VALOR_RESIDUAL = VALOR_RESIDUAL;
        this.ESTADOFISICO = ESTADOFISICO;
        this.ESTADO_BD = ESTADO_BD;
        this.OBSERVACION = OBSERVACION;
        this.GRUPO = GRUPO;
        this.AUXILIAR = AUXILIAR;
        this.GESTION_INGRESO = GESTION_INGRESO;
        this.PARTIDA = PARTIDA;
        this.GLOSA = GLOSA;
        this.COLOR = COLOR;
        this.SERIE = SERIE;
        this.MARCA = MARCA;
        this.MODELO = MODELO;
        this.PLACA = PLACA;
        this.BAJA = BAJA;
        this.GESTION_BAJA = GESTION_BAJA;
        this.UBI_GEOGRAFICA = UBI_GEOGRAFICA;
        this.ORIGEN = ORIGEN;
        this.RUTA_IMAGEN= RUTA_IMAGEN;
        this.CAMBIO=CAMBIO;
        this.ESTADO= ESTADO;
    }
    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }


    public Integer getCORRELATIVO() {
        return CORRELATIVO;
    }

    public void setCORRELATIVO(Integer CORRELATIVO) {
        this.CORRELATIVO = CORRELATIVO;
    }

    public String getTIPO() {
        return TIPO;
    }

    public void setTIPO(String TIPO) {
        this.TIPO = TIPO;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public String getUNIDAD() {
        return UNIDAD;
    }

    public void setUNIDAD(String UNIDAD) {
        this.UNIDAD = UNIDAD;
    }

    public String getFECHA_INGRESO() {
        return FECHA_INGRESO;
    }

    public void setFECHA_INGRESO(String FECHA_INGRESO) {
        this.FECHA_INGRESO = FECHA_INGRESO;
    }


    public String getVALOR() {
        return VALOR;
    }

    public void setVALOR(String VALOR) {
        this.VALOR = VALOR;
    }

    public String getVALOR_RESIDUAL() {
        return VALOR_RESIDUAL;
    }

    public void setVALOR_RESIDUAL(String VALOR_RESIDUAL) {
        this.VALOR_RESIDUAL = VALOR_RESIDUAL;
    }

    public String getESTADOFISICO() {
        return ESTADOFISICO;
    }

    public void setESTADOFISICO(String ESTADOFISICO) {
        this.ESTADOFISICO = ESTADOFISICO;
    }

    public String getESTADO_BD() {
        return ESTADO_BD;
    }

    public void setESTADO_BD(String ESTADO_BD) {
        this.ESTADO_BD = ESTADO_BD;
    }

    public String getOBSERVACION() {
        return OBSERVACION;
    }

    public void setOBSERVACION(String OBSERVACION) {
        this.OBSERVACION = OBSERVACION;
    }

    public String getGRUPO() {
        return GRUPO;
    }

    public void setGRUPO(String GRUPO) {
        this.GRUPO = GRUPO;
    }

    public String getAUXILIAR() {
        return AUXILIAR;
    }

    public void setAUXILIAR(String AUXILIAR) {
        this.AUXILIAR = AUXILIAR;
    }

    public Integer getGESTION_INGRESO() {
        return GESTION_INGRESO;
    }

    public void setGESTION_INGRESO(Integer GESTION_INGRESO) {
        this.GESTION_INGRESO = GESTION_INGRESO;
    }

    public String getPARTIDA() {
        return PARTIDA;
    }

    public void setPARTIDA(String PARTIDA) {
        this.PARTIDA = PARTIDA;
    }

    public String getGLOSA() {
        return GLOSA;
    }

    public void setGLOSA(String GLOSA) {
        this.GLOSA = GLOSA;
    }

    public String getCOLOR() {
        return COLOR;
    }

    public void setCOLOR(String COLOR) {
        this.COLOR = COLOR;
    }

    public String getSERIE() {
        return SERIE;
    }

    public void setSERIE(String SERIE) {
        this.SERIE = SERIE;
    }

    public String getMARCA() {
        return MARCA;
    }

    public void setMARCA(String MARCA) {
        this.MARCA = MARCA;
    }

    public String getMODELO() {
        return MODELO;
    }

    public void setMODELO(String MODELO) {
        this.MODELO = MODELO;
    }

    public String getPLACA() {
        return PLACA;
    }

    public void setPLACA(String PLACA) {
        this.PLACA = PLACA;
    }

    public String getBAJA() {
        return BAJA;
    }

    public void setBAJA(String BAJA) {
        this.BAJA = BAJA;
    }

    public Integer getGESTION_BAJA() {
        return GESTION_BAJA;
    }

    public void setGESTION_BAJA(Integer GESTION_BAJA) {
        this.GESTION_BAJA = GESTION_BAJA;
    }

    public String getUBI_GEOGRAFICA() {
        return UBI_GEOGRAFICA;
    }

    public void setUBI_GEOGRAFICA(String UBI_GEOGRAFICA) {
        this.UBI_GEOGRAFICA = UBI_GEOGRAFICA;
    }

    public String getORIGEN() {
        return ORIGEN;
    }

    public void setORIGEN(String ORIGEN) {
        this.ORIGEN = ORIGEN;
    }

    public String getRUTA_IMAGEN() {
        return RUTA_IMAGEN;
    }

    public void setIMAGEN(int IMAGEN) {
        this.RUTA_IMAGEN = RUTA_IMAGEN;
    }

    public String getCAMBIO() {
        return CAMBIO;
    }

    public void setCAMBIO(String CAMBIO) {
        this.CAMBIO = CAMBIO;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }
}
