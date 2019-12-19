package com.example.ale.misactivos.Operaciones;

public class CreaTablas {
    public static final String NOMBREDB="DBActivof";

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
            "id INTEGER primary key autoincrement, " +
            "codigo VARCHAR (20)," +
            "nombreedificio VARCHAR (50)," +
            "estado VARCHAR (1))";

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
            "id integer primary key autoincrement," +
            "codigo VARCHAR(20) unique," +
            "correlativo INTEGER," +
            "tipo VARCHAR(30)," +
            "descripcion VARCHAR(250)," +
            "unidad VARCHAR(15)," +
            "fecha_ing VARCHAR(10)," +
            "valor REAL," +
            "valor_residual REAL," +
            "estadofisico VARCHAR(15)," +
            "estadobd VARCHAR(1)," +
            "observacion VARCHAR(250)," +
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
            "cambio VARCHAR(1)," +
            "ruta_imagen VARCHAR(70)," +
            "estado VARCHAR(1))";
            //"FOREIGN KEY (observacionid) REFERENCES observaciones (id)," +
            //"FOREIGN KEY (estadofisicoid) REFERENCES estados (id))";

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
            "gestion Integer," +
            "glosa VARCHAR(250)," +
            "estado VARCHAR(1))";


    //constantes tabla DETINVENTARIOS
    public static final String  CREAR_TABLA_DETINVENTARIOS="CREATE TABLE detinventarios (" +
    "id INTEGER primary key autoincrement," +
            "inventarioid INTEGER," +
            "gestion INTEGER," +
            "activoid VARCHAR(20)," +
            "edificioid INTEGER," +
            "custodioid INTEGER," +
            "verificado VARCHAR(1)," +
            "estado VARCHAR(20)," +
            "observacion VARCHAR(100)," +
            "fechareg VARCHAR(10)," +
            "FOREIGN KEY (edificioid) REFERENCES edificios (id)," +
            "FOREIGN KEY (inventarioid) REFERENCES inventarios (id)," +
            "FOREIGN KEY (custodioid) REFERENCES personas (id)," +
            "FOREIGN KEY (activoid) REFERENCES activos (Codigo))";

    //constantes tabla CUSTODIAS
    public static final String  CREAR_TABLA_CUSTODIAS="CREATE TABLE custodias (" +
    "id INTEGER primary key autoincrement," +
            "cpbte VARCHAR(20)," +
            "gestion INTEGER," +
            "fechacustodia VARCHAR(10)," +
            "custodioid VARCHAR(10)," +
            "edificioid VARCHAR(10)," +
            "glosa VARCHAR(250)," +
            "estado VARCHAR(1))";

    //constantes tabla DETCUSTODIAS
    public static final String  CREAR_TABLA_DETCUSTODIAS="CREATE TABLE detcustodias (" +
    "id INTEGER primary key autoincrement," +
            "cpbte VARCHAR(20)," +
            "gestion INTEGER," +
           //"custodiasid INTEGER," +
            //"fecha_reg VARCHAR(10)," +
            "activoid VARCHAR(20)," +
            "estadoactual VARCHAR(20)," +
            "estado VARCHAR(20))";
            //"FOREIGN KEY (custodiasid) REFERENCES custodias (id)," +
           // "FOREIGN KEY (activoid) REFERENCES activos (Codigo))";

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

}
