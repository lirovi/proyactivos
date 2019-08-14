package com.example.ale.misactivos.Operaciones;

public class CreaTablas {

    //constantes tabla cambios
    public static final String CREAR_TABLA_CAMBIOS="Create table cambios(" +
            "id integer primary key autoincrement," +
            "dolar real, " +
            "fecha String," +
            "fecha_modificacion String," +
            "ufv real," +
            "usuario String)";

    //constantes tabla usuarios
    public static final String CREAR_TABLA_USUARIOS="Create table usuarios(" +
            "id integer primary key autoincrement," +
            "Nombre text," +
            "Apellido text," +
            "Usuario text," +
            "Password text)";

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
            "ORIGEN String)";



    //constantes tabla profesion
    public static final String CREAR_TABLA_PROFESIONES="Create table profesiones(" +
            "id integer primary key autoincrement," +
            "nombreprof text)";

    //constantes tabla tiposdocumentos
    public static final String CREAR_TABLA_TIPODOCUMENTOS="Create table tiposdocumentos(" +
            "id integer primary key autoincrement," +
            "nombredoc text," +
            "sigla text)";

    //constantes tabla cargos
    public static final String CREAR_TABLA_CARGOS="Create table cargos(" +
            "id integer primary key autoincrement," +
            "nombrecargo text)";

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
            "sexo text)";

    //constantes tabla Edificios
    public static final String CREAR_TABLA_EDIFICIOS="Create table edificios(" +
            "id integer primary key autoincrement," +
            "nombreedificio text)";

    //constantes tabla Departamentos
    public static final String CREAR_TABLA_DEPARTAMENTOS="Create table departamentos(" +
            "id integer primary key autoincrement," +
            "nombredpto text," +
            "edificioid integer)";

    //constantes tabla OFicinas
    public static final String CREAR_TABLA_OFICINAS="Create table oficinas(" +
            "id integer primary key autoincrement," +
            "nombreoficina text," +
            "dptoid integer)";

    //constantes tabla INVENTARIOS
    public static final String CREAR_TABLA_INVENTARIOS="Create table inventarios(" +
            "id integer primary key autoincrement," +
            "fecha text," +
            "glosa text)";

    //constantes tabla DETALLE INVENTARIOS
    public static final String CREAR_TABLA_DETINVENTARIOS="Create table Detinventarios(" +
            "id integer primary key autoincrement," +
            "inventarioid integer," +
            "fecha_reg text," +
            "activoid text," +
            "observacionid integer," +
            "estadoid integer," +
            "verificado text)";

    //constantes tabla DEVOLUCIONES
    public static final String CREAR_TABLA_DEVOLUCIONES="Create table devoluciones(" +
            "id integer primary key autoincrement," +
            "fecha text," +
            "funcionarioid integer," +
            "oficinaid integer,"+
            "glosa text)";

    //constantes tabla DETALLE DETDEVOLUCIONES
    public static final String CREAR_TABLA_DETDEVOLUCIONES="Create table detdevoluciones(" +
            "id integer primary key autoincrement," +
            "devolucionid integer," +
            "fecha text," +
            "activoid text," +
            "estadoid text," +
            "observacionid integer," +
            "motivoid integer)";

    //constantes tabla INGRESOS
    public static final String CREAR_TABLA_INGRESOS="Create table ingresos(" +
            "id integer primary key autoincrement," +
            "fechaing text," +
            "glosa text)";

    //constantes tabla DETALLE DETALLE INGRESOS
    public static final String CREAR_TABLA_DETINGRESOS="Create table detingresos(" +
            "id integer primary key autoincrement," +
            "ingresosid integer," +
            "fecha_reg text," +
            "caracteristicas text," +
            "observacionid integer," +
            "motivoid integer)";

    //constantes tabla CUSTODIAS
    public static final String CREAR_TABLA_CUSTODIAS="Create table custodias(" +
            "id integer primary key autoincrement," +
            "fechacustodia text," +
            "funcionarioid integer," +
            "oficinaid integer,"+
            "glosa text)";

    //constantes tabla DETALLE DETALLE CUSTODIAS
    public static final String CREAR_TABLA_DETCUSTODIAS="Create table detcustodias(" +
            "id integer primary key autoincrement," +
            "custodiaid integer," +
            "fecha_reg text," +
            "activoid text," +
            "estadoid integer," +
            "observacionid integer," +
            "motivoid integer)";

    //constantes tabla TIPOPROFESIONES
    public static final String CREAR_TABLA_TIPOPROFESIONES="Create table tipoprofesiones(" +
            "id integer primary key autoincrement," +
            "nombre text)";

    //constantes tabla OBSERVACIONES
    public static final String CREAR_TABLA_OBSERVACIONES="Create table OBSERVACIONES(" +
            "id integer primary key autoincrement," +
            "nombre text)";

    //constantes tabla MOTIVOS
    public static final String CREAR_TABLA_MOTIVOS="Create table motivos(" +
            "id integer primary key autoincrement," +
            "nombre text)";


    //constantes tabla ESTADOS
    public static final String CREAR_TABLA_ESTADOS="Create table estados(" +
            "id integer primary key autoincrement," +
            "nombre text)";

}
