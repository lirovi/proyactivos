package com.example.ale.misactivos.Operaciones;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.IOException;

public class ConexionSqliteHelper extends SQLiteOpenHelper {

    private Context mContext;

    public ConexionSqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
     /* db.execSQL("DROP TABLE IF EXISTS cambios");
        db.execSQL("DROP TABLE IF EXISTS inventarios");
        db.execSQL("DROP TABLE IF EXISTS detinventarios");
        db.execSQL("DROP TABLE IF EXISTS devoluciones");
        db.execSQL("DROP TABLE IF EXISTS detdevoluciones");
        db.execSQL("DROP TABLE IF EXISTS ingresos");
        db.execSQL("DROP TABLE IF EXISTS detingresos");
        db.execSQL("DROP TABLE IF EXISTS custodias");
        db.execSQL("DROP TABLE IF EXISTS detcustodias");
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        db.execSQL("DROP TABLE IF EXISTS activos");
        db.execSQL("DROP TABLE IF EXISTS cargos");
        db.execSQL("DROP TABLE IF EXISTS funcionarios");
        db.execSQL("DROP TABLE IF EXISTS tipodocumentos");
        db.execSQL("DROP TABLE IF EXISTS profesiones");
        db.execSQL("DROP TABLE IF EXISTS edificios");
        db.execSQL("DROP TABLE IF EXISTS oficinas");
        db.execSQL("DROP TABLE IF EXISTS observaciones");
        db.execSQL("DROP TABLE IF EXISTS estados");
        db.execSQL("DROP TABLE IF EXISTS motivos");*/
        try {
            db.execSQL(CreaTablas.CREAR_TABLA_CAMBIOS);
            db.execSQL(CreaTablas.CREAR_TABLA_USUARIOS);
            db.execSQL(CreaTablas.CREAR_TABLA_PROFESIONES);
            db.execSQL(CreaTablas.CREAR_TABLA_TIPODOC);
            db.execSQL(CreaTablas.CREAR_TABLA_CARGOS);
            db.execSQL(CreaTablas.CREAR_TABLA_FUNCIONARIOS);
            db.execSQL(CreaTablas.CREAR_TABLA_ACTIVOS);
            db.execSQL(CreaTablas.CREAR_TABLA_EDIFICIOS);
            db.execSQL(CreaTablas.CREAR_TABLA_OFICINAS);
            db.execSQL(CreaTablas.CREAR_TABLA_INVENTARIOS);
            db.execSQL(CreaTablas.CREAR_TABLA_DETINVENTARIOS);
            db.execSQL(CreaTablas.CREAR_TABLA_DEVOLUCIONES);
            db.execSQL(CreaTablas.CREAR_TABLA_DETDEVOLUCIONES);
            db.execSQL(CreaTablas.CREAR_TABLA_INGRESOS);
            db.execSQL(CreaTablas.CREAR_TABLA_DETINGRESOS);
            db.execSQL(CreaTablas.CREAR_TABLA_CUSTODIAS);
            db.execSQL(CreaTablas.CREAR_TABLA_DETCUSTODIAS);
            db.execSQL(CreaTablas.CREAR_TABLA_OBS);
            db.execSQL(CreaTablas.CREAR_TABLA_ESTADOS);
            db.execSQL(CreaTablas.CREAR_TABLA_MOTIVOS);
        } catch (Exception e) {
            Log.e("MYDB", "Error al crear las tablas"+e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            db.execSQL("DROP TABLE IF EXISTS cambios");
            db.execSQL("DROP TABLE IF EXISTS inventarios");
            db.execSQL("DROP TABLE IF EXISTS detinventarios");
            db.execSQL("DROP TABLE IF EXISTS devoluciones");
            db.execSQL("DROP TABLE IF EXISTS detdevoluciones");
            db.execSQL("DROP TABLE IF EXISTS ingresos");
            db.execSQL("DROP TABLE IF EXISTS detingresos");
            db.execSQL("DROP TABLE IF EXISTS custodias");
            db.execSQL("DROP TABLE IF EXISTS detcustodias");
            db.execSQL("DROP TABLE IF EXISTS usuarios");
            db.execSQL("DROP TABLE IF EXISTS activos");
            db.execSQL("DROP TABLE IF EXISTS cargos");
            db.execSQL("DROP TABLE IF EXISTS funcionarios");
            db.execSQL("DROP TABLE IF EXISTS departamentos");
           //db.execSQL("DROP TABLE IF EXISTS tipoprofesiones");
            db.execSQL("DROP TABLE IF EXISTS tipodocumentos");
            db.execSQL("DROP TABLE IF EXISTS profesiones");
            //db.execSQL("DROP TABLE IF EXISTS departamentos");
            db.execSQL("DROP TABLE IF EXISTS edificios");
            db.execSQL("DROP TABLE IF EXISTS oficinas");
            db.execSQL("DROP TABLE IF EXISTS tipoprofesiones");
            db.execSQL("DROP TABLE IF EXISTS observaciones");
            db.execSQL("DROP TABLE IF EXISTS estados");
            db.execSQL("DROP TABLE IF EXISTS motivos");
            onCreate(db);
        } catch (Exception e) {
            Log.e("MYDB", "Error al Actualizar las tablas");
        }
    }


}
