package com.example.ale.misactivos.entidades;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Sqlite_helper extends SQLiteOpenHelper {

    private Context mContext;

    public Sqlite_helper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            String sqlUsuario="Create table usuarios(_id primary key autoincrement, Nombre text, Apellido text," +
                    " Usuario text, Password text);";

           /* String sqlActivo="CREATE TABLE IF NOT EXISTS ACTIVOS_FIJOS(Codigo VARCHAR(30) primary key," +
                    "  CODIGO_TARIJA VARCHAR(30)," +
                    "  CORRELATIVO integer(15,0)," +
                    "  TIPO VARCHAR(20)," +
                    "  DESCRIPCION VARCHAR(500) NOT NULL," +
                    "  UNIDAD VARCHAR(10)," +
                    "  CANTIDAD float(15,2)," +
                    "  FECHA_INGRESO datetime," +
                    "  FECHA_ACTIVACION datetime," +
                    "  VALOR float(15,2)," +
                    "  VALOR_RESIDUAL float(15,2)," +
                    "  ESTADO_FISICO VARCHAR(30)," +
                    "  ESTADO_BD VARCHAR(20)," +
                    "  OBSERVACIONES VARCHAR(250)," +
                    "  GRUPO VARCHAR(15)," +
                    "  AUXILIAR VARCHAR(15)," +
                    "  GESTION_INGRESO integer," +
                    "  PARTIDA VARCHAR(10)," +
                    "  GLOSA VARCHAR(250)," +
                    "  COLOR VARCHAR(20)," +
                    "  SERIE VARCHAR(40)," +
                    "  MARCA VARCHAR(40)," +
                    "  MODELO VARCHAR(40)," +
                    "  PLACA VARCHAR(30)," +
                    "  ACTA_RECEPCION VARCHAR(20)," +
                    "  GESTION_ACTA integer," +
                    "  NOTA_INGRESO VARCHAR(20)," +
                    "  GESTION_NOTA integer," +
                    "  BAJA VARCHAR(20)," +
                    "  GESTION_BAJA integer," +
                    "  UBI_GEOGRAFICA VARCHAR(20)," +
                    "  SECTOR VARCHAR(11)," +
                    "  CORR_ITEM integer," +
                    "  FECHA_PREVENTIVO datetime," +
                    "  ORIGEN VARCHAR(20) DEFAULT NULL," +
                    "  HOJA_RUTA VARCHAR(10)," +
                    "  PROGRAMA VARCHAR(10)," +
                    "  PROYECTO VARCHAR(10)," +
                    "  ESPECIFICACIONES VARCHAR(500)," +
                    "  PREVENTIVO intger," +
                    "  IMPRESION integer);";*/
            db.execSQL(sqlUsuario);
            /*db.execSQL(sqlActivo);
            abrirdb();

        db.execSQL("INSERT INTO ACTIVOS_FIJOS VALUES ('11-07-0004-000147', NULL, 754, 'ACTIVO', " +
                "'COMPUTADOR PORTATIL MODELO VPCF113FX MARCA SONY SERIE 275148393000760 INCLUYE CARGADOR SERIE 1479683610093368 DISCO DURO TCLADO  E-10154'," +
                        " 'Pieza', 1,'04-06-2010 12:00:00 am', '04-06-2010 12:00:00', 16499.91, 1, 'NUEVO', 'NOTA_INGRESO', NULL, '07','0004', 2015, '43120', NULL," +
                " NULL, NULL, NULL, NULL, NULL, '00001', 2016, '00001', 2016, NULL, NULL, '11', '0', 147," +
                " '01-01-2016 12:00:00', 'PROPIO', '1', '11', '110000001', 'Ninguna', 0, 0)");
        db.execSQL("INSERT INTO ACTIVO_FIJO VALUES ('11-07-0004-000151', NULL, 758, 'ACTIVO'," +
                " 'COMPUTADOR PORTATIL HP DV6-2155DXNºWA781UA#ABAN CORE I3213GHZ4GBDDR3-500GB DISCO DURO CAMARA WEB CAM SERIE S CNF01220ZQ CON MALETIN E-10157'," +
                " 'Pieza', 1, ('29-09-2010 12:00:00 am'), ('29-09-2010 12:00:00 am'), 10101.23, 1, 'NUEVO', 'NOTA_INGRESO', NULL,'07', '0004', 2015, '43120', NULL, NULL, NULL, NULL, NULL," +
                " NULL, '00001', 2016, '00001', 2016, NULL, NULL,'11', '0', 151, ('01-01-2016 12:00:00 am'), 'PROPIO', '1', '11', '110000001', 'Ninguna', 0, 0)");
        db.execSQL("INSERT INTO ACTIVO_FIJO VALUES ('11-07-0004-000185', NULL, 792, 'ACTIVO'," +
                " 'COMPUTADORA CORE 17 GABINETE DELUX COMBO (TECLADO MOUSE)', 'Pieza', 1, ('21-02-2014 12:00:00 am'), ('21-02-2014 12:00:00 am'), 8142.71," +
                " 1,'NUEVO', 'NOTA_INGRESO', NULL, '07', '0004', 2015, '43120', NULL, NULL, NULL, NULL, NULL, NULL, '00001', 2016,'00001', 2016, NULL, NULL, '11', '0', 185, ('01-01-2016 12:00:00 am'), 'PROPIO', '1', '11'," +
                " '110000001','Ninguna', 0, 0)");
        db.execSQL("INSERT INTO ACTIVO_FIJO VALUES ('11-07-0004-000186', NULL, 793, 'ACTIVO', " +
                "'COMPUTADORA CORE 17 GABINETE DELUX COMBO (TECLADO MOUSE)', 'Pieza', 1, ('21-02-2014 12:00:00 am'), ('21-02-2014 12:00:00 am'), 8142.71, 1," +
                "'NUEVO', 'NOTA_INGRESO', NULL, '07', '0004', 2015, '43120', NULL, NULL, NULL, NULL, NULL, NULL, '00001', 2016,'00001', 2016, NULL, NULL, '11', '0', 186, ('01-01-2016 12:00:00 am'), 'PROPIO', '1', '11'," +
                " '110000001','Ninguna', 0, 0)");
        db.execSQL("INSERT INTO ACTIVO_FIJO VALUES ('11-07-0004-000187', NULL, 794, 'ACTIVO'," +
                " 'COMPUTADORA CORE 17 GABINETE DELUX COMBO (TECLADO MOUSE)', 'Pieza', 1," +
                " ('21-02-2014 12:00:00 am'), ('21-02-2014 12:00:00 am'), 8142.71, 1,'NUEVO', 'NOTA_INGRESO', NULL, '07', '0004', 2015, '43120', NULL, NULL, NULL, NULL," +
                " NULL, NULL, '00001', 2016,'00001', 2016, NULL, NULL, '11', '0', 187, ('01-01-2016 12:00:00 am'), 'PROPIO', '1', '11', '110000002','Ninguna', 0, 0)");
        db.execSQL("INSERT INTO ACTIVO_FIJO VALUES ('11-07-0004-000196', NULL, 803, 'ACTIVO'," +
                " 'COMPUTADORA PORTATIL CORE i5 CON LAS SIGUIENTES CARCTERISTICAS: TOSHIBA E55-A5114 COREi5 4200U 1.6GHZ MEMORIA DDR3 6GB, DISCO DURO 750GB, PANTALLA 15.6 FULL HD TECLADO EN INGLES. SERIE: 2E30128P; OC.', " +
                        "'Pieza', 1, ('15-07-2014 12:00:00 am'), ('15-07-2014 12:00:00 am'), 6293.69, 1, 'NUEVO', 'NOTA_INGRESO', NULL, '07', '0004', 2015, '43120'," +
                        " NULL, NULL, NULL, NULL, NULL, NULL, '00001', 2016, '00001', 2016, NULL, NULL, '11', '0', 196, ('01-01-2016 12:00:00 am'), 'PROPIO', '1', '11'," +
                " '110000001', 'Ninguna', 0, 0)");
        db.execSQL("INSERT INTO ACTIVO_FIJO VALUES ('11-07-0004-000204', NULL, 811, 'ACTIVO', 'EQUIPO DE COMPUTACION CON LAS SIGUIENTES CARACTERISTICAS: PROCESADOR CORE I 5 4ta. GO. INTEL DE 3.2Ghz PLACA MADRE ASUS H81M-K  MEMORIA RAM 8GB (86B) DDR3 KINSTENG DISCO DURO 1 TERA TOSHIBA UNIDAD DVD', " +
                "'Pieza', 1, ('15-09-2015 12:00:00 am'), ('15-09-2015 12:00:00 am'), 3135.38, 1, 'NUEVO', 'NOTA_INGRESO', NULL, '07', '0004', 2015, '43120', NULL, NULL, " +
                        "NULL,NULL, NULL, NULL, '00001', 2016, '00001', 2016, NULL, NULL, '11', '0', 204, ('01-01-2016 12:00:00 am'), 'PROPIO', '1', '11', '110000001', 'Ninguna', 0, 0)");
        cerrardb();*/
        /*InputStream is = null;
        try {
            is = mContext.getAssets().open("import.sql");
            if (is != null) {
                db.beginTransaction();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String line = reader.readLine();
                while (!TextUtils.isEmpty(line)) {
                    db.execSQL(line);
                    line = reader.readLine();
                }
                db.setTransactionSuccessful();
            }
        } catch (Exception ex) {
            // Muestra log
        } finally {
            db.endTransaction();
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Muestra log
                }
            }*/



    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void abrirdb(){
        this.getWritableDatabase();
    }
    public void cerrardb(){
        this.close();
    }

    public void insertarReg(String nom, String ape, String usu, String pas){
        ContentValues reg= new ContentValues();
        reg.put("Nombre",nom);
        reg.put("Apellido",ape);
        reg.put("Usuario",usu);
        reg.put("Password",pas);
        this.getWritableDatabase().insert("usuarios",null,reg);
    }

    public Cursor verificaUssPas(String usu, String pas) throws SQLException{
        Cursor mcursor=null;
        mcursor=this.getReadableDatabase().query("usuarios", new String[]{"id",
                "Nombre","Apellido","usuario","Password"},"Usuario like '"+usu+"'  " +
                "and Password like '"+pas+"'",null,null,null,null);

        return mcursor;

    }
}
