package com.example.ale.misactivos.entidades;

import java.io.Serializable;

public class Activos implements Serializable {
    private String Codigo;
    private String CODIGO_TARIJA;
    private Integer CORRELATIVO;
    private String TIPO;
    private String DESCRIPCION;
    private String UNIDAD;
    private Integer CANTIDAD;
    private String FECHA_INGRESO;
    private String FECHA_ACTIVACION;
    private Float VALOR;
    private Float VALOR_RESIDUAL;
    private String ESTADO_FISICO;
    private String ESTADO_BD;
    private String OBSERVACIONES;
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
    private String ACTA_RECEPCION;
    private Integer GESTION_ACTA;
    private String NOTA_INGRESO;
    private Integer GESTION_NOTA;
    private String BAJA;
    private Integer GESTION_BAJA;
    private String UBI_GEOGRAFICA;
    private String SECTOR;
    private Integer CORR_ITEM;
    private String FECHA_PREVENTIVO;
    private String ORIGEN;
    private String HOJA_RUTA;
    private String PROGRAMA;
    private String PROYECTO;
    private String ESPECIFICACIONES;
    private Integer PREVENTIVO;
    private Integer IMPRESION;

    public Activos()
    {
        this.Codigo = Codigo;
        this.CODIGO_TARIJA = CODIGO_TARIJA;
        this.CORRELATIVO = CORRELATIVO;
        this.TIPO = TIPO;
        this.DESCRIPCION = DESCRIPCION;
        this.UNIDAD = UNIDAD;
        this.CANTIDAD = CANTIDAD;
        this.FECHA_INGRESO = FECHA_INGRESO;
        this.FECHA_ACTIVACION = FECHA_ACTIVACION;
        this.VALOR = VALOR;
        this.VALOR_RESIDUAL = VALOR_RESIDUAL;
        this.ESTADO_FISICO = ESTADO_FISICO;
        this.ESTADO_BD = ESTADO_BD;
        this.OBSERVACIONES = OBSERVACIONES;
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
        this.ACTA_RECEPCION = ACTA_RECEPCION;
        this.GESTION_ACTA = GESTION_ACTA;
        this.NOTA_INGRESO = NOTA_INGRESO;
        this.GESTION_NOTA = GESTION_NOTA;
        this.BAJA = BAJA;
        this.GESTION_BAJA = GESTION_BAJA;
        this.UBI_GEOGRAFICA = UBI_GEOGRAFICA;
        this.SECTOR = SECTOR;
        this.CORR_ITEM = CORR_ITEM;
        this.FECHA_PREVENTIVO = FECHA_PREVENTIVO;
        this.ORIGEN = ORIGEN;
        this.HOJA_RUTA = HOJA_RUTA;
        this.PROGRAMA = PROGRAMA;
        this.PROYECTO = PROYECTO;
        this.ESPECIFICACIONES = ESPECIFICACIONES;
        this.PREVENTIVO = PREVENTIVO;
        this.IMPRESION = IMPRESION;
    }



    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getCODIGO_TARIJA() {
        return CODIGO_TARIJA;
    }

    public void setCODIGO_TARIJA(String CODIGO_TARIJA) {
        this.CODIGO_TARIJA = CODIGO_TARIJA;
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

    public Integer getCANTIDAD() {
        return CANTIDAD;
    }

    public void setCANTIDAD(Integer CANTIDAD) {
        this.CANTIDAD = CANTIDAD;
    }

    public String getFECHA_INGRESO() {
        return FECHA_INGRESO;
    }

    public void setFECHA_INGRESO(String FECHA_INGRESO) {
        this.FECHA_INGRESO = FECHA_INGRESO;
    }

    public String getFECHA_ACTIVACION() {
        return FECHA_ACTIVACION;
    }

    public void setFECHA_ACTIVACION(String FECHA_ACTIVACION) {
        this.FECHA_ACTIVACION = FECHA_ACTIVACION;
    }

    public Float getVALOR() {
        return VALOR;
    }

    public void setVALOR(Float VALOR) {
        this.VALOR = VALOR;
    }

    public Float getVALOR_RESIDUAL() {
        return VALOR_RESIDUAL;
    }

    public void setVALOR_RESIDUAL(Float VALOR_RESIDUAL) {
        this.VALOR_RESIDUAL = VALOR_RESIDUAL;
    }

    public String getESTADO_FISICO() {
        return ESTADO_FISICO;
    }

    public void setESTADO_FISICO(String ESTADO_FISICO) {
        this.ESTADO_FISICO = ESTADO_FISICO;
    }

    public String getESTADO_BD() {
        return ESTADO_BD;
    }

    public void setESTADO_BD(String ESTADO_BD) {
        this.ESTADO_BD = ESTADO_BD;
    }

    public String getOBSERVACIONES() {
        return OBSERVACIONES;
    }

    public void setOBSERVACIONES(String OBSERVACIONES) {
        this.OBSERVACIONES = OBSERVACIONES;
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

    public String getACTA_RECEPCION() {
        return ACTA_RECEPCION;
    }

    public void setACTA_RECEPCION(String ACTA_RECEPCION) {
        this.ACTA_RECEPCION = ACTA_RECEPCION;
    }

    public Integer getGESTION_ACTA() {
        return GESTION_ACTA;
    }

    public void setGESTION_ACTA(Integer GESTION_ACTA) {
        this.GESTION_ACTA = GESTION_ACTA;
    }

    public String getNOTA_INGRESO() {
        return NOTA_INGRESO;
    }

    public void setNOTA_INGRESO(String NOTA_INGRESO) {
        this.NOTA_INGRESO = NOTA_INGRESO;
    }

    public Integer getGESTION_NOTA() {
        return GESTION_NOTA;
    }

    public void setGESTION_NOTA(Integer GESTION_NOTA) {
        this.GESTION_NOTA = GESTION_NOTA;
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

    public String getSECTOR() {
        return SECTOR;
    }

    public void setSECTOR(String SECTOR) {
        this.SECTOR = SECTOR;
    }

    public Integer getCORR_ITEM() {
        return CORR_ITEM;
    }

    public void setCORR_ITEM(Integer CORR_ITEM) {
        this.CORR_ITEM = CORR_ITEM;
    }

    public String getFECHA_PREVENTIVO() {
        return FECHA_PREVENTIVO;
    }

    public void setFECHA_PREVENTIVO(String FECHA_PREVENTIVO) {
        this.FECHA_PREVENTIVO = FECHA_PREVENTIVO;
    }

    public String getORIGEN() {
        return ORIGEN;
    }

    public void setORIGEN(String ORIGEN) {
        this.ORIGEN = ORIGEN;
    }

    public String getHOJA_RUTA() {
        return HOJA_RUTA;
    }

    public void setHOJA_RUTA(String HOJA_RUTA) {
        this.HOJA_RUTA = HOJA_RUTA;
    }

    public String getPROGRAMA() {
        return PROGRAMA;
    }

    public void setPROGRAMA(String PROGRAMA) {
        this.PROGRAMA = PROGRAMA;
    }

    public String getPROYECTO() {
        return PROYECTO;
    }

    public void setPROYECTO(String PROYECTO) {
        this.PROYECTO = PROYECTO;
    }

    public String getESPECIFICACIONES() {
        return ESPECIFICACIONES;
    }

    public void setESPECIFICACIONES(String ESPECIFICACIONES) {
        this.ESPECIFICACIONES = ESPECIFICACIONES;
    }

    public Integer getPREVENTIVO() {
        return PREVENTIVO;
    }

    public void setPREVENTIVO(Integer PREVENTIVO) {
        this.PREVENTIVO = PREVENTIVO;
    }

    public Integer getIMPRESION() {
        return IMPRESION;
    }

    public void setIMPRESION(Integer IMPRESION) {
        this.IMPRESION = IMPRESION;
    }


}
