package com.example.ale.misactivos.Operaciones;

public class CreaTablas {

    //constantes tabla CARGOS
    public static final String  CREAR_TABLA_CARGOS="CREATE TABLE cargos (" +
    "id INTEGER primary key autoincrement," +
            "nombrecargo VARCHAR(25)," +
            "estado VARCHAR(1))";

    //constantes tabla PROFESIONES
    public static final String  CREAR_TABLA_PROFESIONES="CREATE TABLE profesiones (" +
    "id INTEGER primary key autoincrement," +
            "nombreprof VARCHAR (20)," +
            "estado     VARCHAR (1))";

    //constantes tabla TIPODOCUMENTOS
    public static final String CREAR_TABLA_TIPODOC="CREATE TABLE tiposdocumentos (" +
    "id INTEGER primary key autoincrement," +
            "nombredoc VARCHAR (50)," +
            "sigla     VARCHAR (10)," +
            "estado    VARCHAR (1))";

    //constantes tabla FUNCIONARIOS
    public static final String CREAR_TABLA_FUNCIONARIOS="CREATE TABLE funcionarios (" +
    "id  INTEGER primary key autoincrement," +
            "nombre       VARCHAR (25)," +
            "apellidou    VARCHAR (20)," +
            "apellidod    VARCHAR (20)," +
            "direccion    VARCHAR (100)," +
            "telefono     VARCHAR (15)," +
            "cargoid      INTEGER," +
            "profesionid  INTEGER," +
            "tipodocid    INTEGER," +
            "nrodoc       VARCHAR (15)," +
            "nacionalidad VARCHAR (15)," +
            "sexo         VARCHAR (1)," +
            "estado       VARCHAR (1)," +
            "FOREIGN KEY (cargoid) REFERENCES cargos (id)," +
            "FOREIGN KEY (profesionid) REFERENCES profesiones (id)," +
            "FOREIGN KEY (tipodocid)REFERENCES tiposdocumentos (id))";

    //constantes tabla CAMBIOS
    public static final String  CREAR_TABLA_CAMBIOS="CREATE TABLE cambios (" +
    "id INTEGER primary key autoincrement," +
            "dolar REAL," +
            "fecha VARCHAR(10)," +
            "fecha_modif VARCHAR(10)," +
            "ufv REAL," +
            "usuarioid INTEGER," +
            "estado VARCHAR(1)," +
            "FOREIGN KEY (usuarioid) REFERENCES funcionarios (id))";

    //constantes tabla EDIFICIOS
    public static final String  CREAR_TABLA_EDIFICIOS="CREATE TABLE edificios (" +
    "id INTEGER primary key autoincrement," +
            "nombreedificio VARCHAR (50)," +
            "estado         VARCHAR (1))";

    //constantes tabla OFICINAS
    public static final String  CREAR_TABLA_OFICINAS="CREATE TABLE oficinas (" +
    "id INTEGER primary key autoincrement," +
            "nombreoficina VARCHAR(50)," +
            "estado VARCHAR(1)," +
            "edificioid INTEGER," +
            "FOREIGN KEY (edificioid) REFERENCES edificios (id))";

    //constantes tabla USUARIOS // almacena datos del usuario del equipo  movil
    public static final String  CREAR_TABLA_USUARIOS="CREATE TABLE usuarios ( " +
    "id INTEGER primary key autoincrement," +
            "Nombre VARCHAR(20)," +
            "Apellido VARCHAR(15)," +
            "Usuario VARCHAR(15)," +
            "Password VARCHAR(15)," +
            "estado VARCHAR(1))";

    //constantes tabla ESTADOS  //estado fisico del acivo
    public static final String  CREAR_TABLA_ESTADOS="CREATE TABLE estados (" +
    "id INTEGER primary key autoincrement," +
            "nombre VARCHAR(25)," +
            "estado VARCHAR(1))";

    //constantes tabla OBSERVACIONES
    public static final String  CREAR_TABLA_OBS="CREATE TABLE observaciones (" +
    "id INTEGER primary key autoincrement," +
            "nombre VARCHAR(25)," +
            "estado VARCHAR(1))";

    //constantes tabla ACTIVOS
    public static final String  CREAR_TABLA_ACTIVOS="CREATE TABLE activos (" +
    "Codigo VARCHAR(20) primary key," +
            "correlativo INTEGER," +
            "tipo VARCHAR(30)," +
            "descripcion VARCHAR(250)," +
            "unidad VARCHAR(15)," +
            "fecha_ing VARCHAR(10)," +
            "valor REAL," +
            "valor_residual REAL," +
            "estadofisicoid INTEGER," +
            "estadobd VARCHAR(1)," +
            "observacionid INTEGER," +
            "grupo VARCHAR(10)," +
            "auxiliar VARCHAR(10)," +
            "gestion_ing INTEGER," +
            "partida VARCHAR(10)," +
            "glosa VARCHAR(250)," +
            "color VARCHAR(10)," +
            "serie VARCHAR(15)," +
            "marca VARCHAR(20)," +
            "modelo VARCHAR(20)," +
            "nroplaca VARCHAR(10)," +
            "baja VARCHAR(10)," +
            "gestion_baja INTEGER," +
            "ubi_geog VARCHAR(150)," +
            "origen VARCHAR(50)," +
            "FOREIGN KEY (observacionid) REFERENCES observaciones (id)," +
            "FOREIGN KEY (estadofisicoid) REFERENCES estados (id))";

    //constantes tabla DEVOLUCIONES
    public static final String  CREAR_TABLA_DEVOLUCIONES="CREATE TABLE devoluciones (" +
    "id INTEGER primary key autoincrement," +
            "fecha VARCHAR(10)," +
            "custodioid INTEGER," +
            "oficinaid INTEGER," +
            "glosa VARCHAR(250)," +
            "estado VARCHAR(1)," +
            "FOREIGN KEY (custodioid) REFERENCES funcionarios (id)," +
            "FOREIGN KEY (oficinaid) REFERENCES oficinas (id))";

    //constantes tabla MOTIVOS
    public static final String  CREAR_TABLA_MOTIVOS="CREATE TABLE motivos (" +
    "id INTEGER primary key autoincrement," +
            "nombre VARCHAR(50)," +
            "estado VARCHAR(1))";

    //constantes tabla DETDEVOLUCIONES
    public static final String  CREAR_TABLA_DETDEVOLUCIONES="CREATE TABLE detdevoluciones (" +
    "id INTEGER primary key autoincrement," +
            "devolucionid INTEGER," +
            "fecha VARCHAR(10)," +
            "activoid VARCHAR(20)," +
            "estadoid INTEGER," +
            "obsid INTEGER," +
            "motivoid INTEGER," +
            "estado VARCHAR(1)," +
            "FOREIGN KEY (motivoid) REFERENCES motivos (id)," +
            "FOREIGN KEY (devolucionid) REFERENCES devoluciones (id)," +
            "FOREIGN KEY (estadoid) REFERENCES estados (id)," +
            "FOREIGN KEY (activoid) REFERENCES activos (Codigo)," +
            "FOREIGN KEY (obsid) REFERENCES observaciones (id))";

    //constantes tabla INVENTARIOS
    public static final String  CREAR_TABLA_INVENTARIOS="CREATE TABLE inventarios (" +
    "id INTEGER primary key autoincrement," +
            "fecha VARCHAR(10)," +
            "glosa VARCHAR(250)," +
            "estado VARCHAR(1)," +
            "oficinaid INTEGER," +
            "custodioid INTEGER," +
            "FOREIGN KEY (custodioid) REFERENCES funcionarios (id)," +
            "FOREIGN KEY (oficinaid) REFERENCES oficinas (id))";

    //constantes tabla DETINVENTARIOS
    public static final String  CREAR_TABLA_DETINVENTARIOS="CREATE TABLE detinventarios (" +
    "id INTEGER primary key autoincrement," +
            "inventarioid INTEGER," +
            "fecha_reg VARCHAR(10)," +
            "activoid VARCHAR(20)," +
            "obsid INTEGER," +
            "estadoid INTEGER," +
            "verificado VARCHAR(1)," +
            "estado VARCHAR(1)," +
            "FOREIGN KEY (estadoid) REFERENCES estados (id)," +
            "FOREIGN KEY (inventarioid) REFERENCES inventarios (id)," +
            "FOREIGN KEY (obsid) REFERENCES observaciones (id)," +
            "FOREIGN KEY (activoid) REFERENCES activos (Codigo))";

    //constantes tabla CUSTODIAS
    public static final String  CREAR_TABLA_CUSTODIAS="CREATE TABLE custodias (" +
    "id INTEGER primary key autoincrement," +
            "fechacustodia VARCHAR(10)," +
            "custodioid INTEGER," +
            "oficinaid INTEGER," +
            "glosa VARCHAR(250)," +
            "estado VARCHAR(1)," +
            "FOREIGN KEY (custodioid) REFERENCES funcionarios (id)," +
            "FOREIGN KEY (oficinaid) REFERENCES oficinas (id))";

    //constantes tabla DETCUSTODIAS
    public static final String  CREAR_TABLA_DETCUSTODIAS="CREATE TABLE detcustodias (" +
    "id INTEGER primary key autoincrement," +
            "custodiasid INTEGER," +
            "fecha_reg VARCHAR(10)," +
            "activoid VARCHAR(20)," +
            "motivoid INTEGER," +
            "estado VARCHAR(1)," +
            "FOREIGN KEY (custodiasid) REFERENCES custodias (id)," +
            "FOREIGN KEY (motivoid) REFERENCES motivos (id)," +
            "FOREIGN KEY (activoid) REFERENCES activos (Codigo))";

    //constantes tabla INGRESOS
    public static final String  CREAR_TABLA_INGRESOS="CREATE TABLE ingresos (" +
    "id INTEGER primary key autoincrement," +
            "fechaing VARCHAR(10)," +
            "glosa VARCHAR(250)," +
            "estado VARCHAR(1))";

    //constantes tabla DETINGRESOS
    public static final String  CREAR_TABLA_DETINGRESOS="CREATE TABLE detingresos (" +
    "id INTEGER primary key autoincrement," +
            "ingresosid INTEGER NOT NULL," +
            "fecha_reg VARCHAR(10) NOT NULL," +
            "caracteristicas VARCHAR(250) NOT NULL," +
            "observacionid INTEGER," +
            "motivoid INTEGER," +
            "activoid VARCHAR(20) NOT NULL," +
            "FOREIGN KEY (ingresosid) REFERENCES ingresos (id)," +
            "FOREIGN KEY (activoid) REFERENCES activos (Codigo)," +
            "FOREIGN KEY (motivoid) REFERENCES motivos (id))";
/*
    //constantes tabla cambios
    public static final String CREAR_TABLA_CAMBIOS="Create table cambios(" +
            "id integer primary key autoincrement," +
            "dolar real, " +
            "fecha String," +
            "fecha_modificacion String," +
            "ufv real," +
            "usuario String,"+
            "estado text)";

    //constantes tabla usuarios
    public static final String CREAR_TABLA_USUARIOS="Create table usuarios(" +
            "id integer primary key autoincrement," +
            "Nombre text," +
            "Apellido text," +
            "Usuario text," +
            "Password text,"+
            "estado text)";

    //constantes tabla activo fijos
    public static final String CREAR_TABLA_ACTIVOS="CREATE TABLE activos(" +
            "Codigo String primary key," +
            "CORRELATIVO integer," +
            "TIPO String," +
            "DESCRIPCION String," +
            "UNIDAD String," +
            "FECHA_INGRESO String," +
            "VALOR real," +
            "VALOR_RESIDUAL real," +
            "ESTADO_FISICO String," +
            "ESTADO_BD String," +
            "OBSERVACIONES String," +
            "GRUPO String," +
            "AUXILIAR String," +
            "GESTION_INGRESO integer," +
            "PARTIDA String," +
            "GLOSA String," +
            "COLOR String," +
            "SERIE String," +
            "MARCA String," +
            "MODELO String," +
            "PLACA String," +
            "BAJA String," +
            "GESTION_BAJA integer," +
            "UBI_GEOGRAFICA String," +
            "ORIGEN String,"+
            "estado text)";

    //constantes tabla profesion
    public static final String CREAR_TABLA_PROFESIONES="Create table profesiones(" +
            "id integer primary key autoincrement," +
            "nombreprof text,"+
            "estado text)";

    //constantes tabla tiposdocumentos
    public static final String CREAR_TABLA_TIPODOCUMENTOS="Create table tiposdocumentos(" +
            "id integer primary key autoincrement," +
            "nombredoc text," +
            "sigla text,"+
            "estado text)";

    //constantes tabla cargos
    public static final String CREAR_TABLA_CARGOS="Create table cargos(" +
            "id integer primary key autoincrement," +
            "nombrecargo text,"+
            "estado text)";

    //constantes tabla funcionarios
    public static final String CREAR_TABLA_FUNCIONARIOS="Create table funcionarios(" +
            "id integer primary key autoincrement," +
            "nombre text," +
            "apellidou text," + //apellido uno
            "apellidod text," + //apellido dos
            "direccion text," +
            "telefono text," +
            "cargoid text," +
            "profesionid text," +
            "tipodocid text," + //CI, RUN, Libreta militar
            "nrodoc text," + //nro documento
            "nacionalidad text," +
            "sexo text,"+
            "estado text)";

    //constantes tabla Edificios
    public static final String CREAR_TABLA_EDIFICIOS="Create table edificios(" +
            "id integer primary key autoincrement," +
            "nombreedificio text,"+
            "estado text)";

    //constantes tabla Departamentos
    public static final String CREAR_TABLA_DEPARTAMENTOS="Create table departamentos(" +
            "id integer primary key autoincrement," +
            "nombredpto text," +
            "edificioid integer,"+
            "estado text)";

    //constantes tabla OFicinas
    public static final String CREAR_TABLA_OFICINAS="Create table oficinas(" +
            "id integer primary key autoincrement," +
            "nombreoficina text," +
            "dptoid integer,"+
            "estado text)";

    //constantes tabla INVENTARIOS
    public static final String CREAR_TABLA_INVENTARIOS="Create table inventarios(" +
            "id integer primary key autoincrement," +
            "fecha text," +
            "glosa text,"+
            "estado text)";

    //constantes tabla DETALLE INVENTARIOS
    public static final String CREAR_TABLA_DETINVENTARIOS="Create table Detinventarios(" +
            "id integer primary key autoincrement," +
            "inventarioid integer," +
            "fecha_reg text," +
            "activoid text," +
            "observacionid integer," +
            "estadoid integer," +
            "verificado text,"+
            "estado text)";

    //constantes tabla DEVOLUCIONES
    public static final String CREAR_TABLA_DEVOLUCIONES="Create table devoluciones(" +
            "id integer primary key autoincrement," +
            "fecha text," +
            "funcionarioid integer," +
            "oficinaid integer,"+
            "glosa text,"+
            "estado text)";

    //constantes tabla DETALLE DETDEVOLUCIONES
    public static final String CREAR_TABLA_DETDEVOLUCIONES="Create table detdevoluciones(" +
            "id integer primary key autoincrement," +
            "devolucionid integer," +
            "fecha text," +
            "activoid text," +
            "estadoid text," +
            "observacionid integer," +
            "motivoid integer,"+
            "estado text)";

    //constantes tabla INGRESOS
    public static final String CREAR_TABLA_INGRESOS="Create table ingresos(" +
            "id integer primary key autoincrement," +
            "fechaing text," +
            "glosa text,"+
            "estado text)";

    //constantes tabla DETALLE DETALLE INGRESOS
    public static final String CREAR_TABLA_DETINGRESOS="Create table detingresos(" +
            "id integer primary key autoincrement," +
            "ingresosid integer," +
            "fecha_reg text," +
            "caracteristicas text," +
            "observacionid integer," +
            "motivoid integer"+
            "estado text)";

    //constantes tabla CUSTODIAS
    public static final String CREAR_TABLA_CUSTODIAS="Create table custodias(" +
            "id integer primary key autoincrement," +
            "fechacustodia text," +
            "funcionarioid integer," +
            "oficinaid integer,"+
            "glosa text,"+
            "estado text)";

    //constantes tabla DETALLE DETALLE CUSTODIAS
    public static final String CREAR_TABLA_DETCUSTODIAS="Create table detcustodias(" +
            "id integer primary key autoincrement," +
            "custodiaid integer," +
            "fecha_reg text," +
            "activoid text," +
            "estadoid integer," +
            "observacionid integer," +
            "motivoid integer,"+
            "estado text)";

    //constantes tabla TIPOPROFESIONES
    public static final String CREAR_TABLA_TIPOPROFESIONES="Create table tipoprofesiones(" +
            "id integer primary key autoincrement," +
            "nombre text,"+
            "estado text)";

    //constantes tabla OBSERVACIONES
    public static final String CREAR_TABLA_OBSERVACIONES="Create table observaciones(" +
            "id integer primary key autoincrement," +
            "nombre text,"+
            "estado text)";

    //constantes tabla MOTIVOS
    public static final String CREAR_TABLA_MOTIVOS="Create table motivos(" +
            "id integer primary key autoincrement," +
            "nombre text,"+
            "estado text)";


    //constantes tabla ESTADOS
    public static final String CREAR_TABLA_ESTADOS="Create table estados(" +
            "id integer primary key autoincrement," +
            "nombre text,"+
            "estado text)";*/
}
