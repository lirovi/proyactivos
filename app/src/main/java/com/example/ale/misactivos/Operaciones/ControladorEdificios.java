package com.example.ale.misactivos.Operaciones;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.ale.misactivos.entidades.Edificios;

public class ControladorEdificios extends ConexionSqliteHelper {

    public ControladorEdificios(Context context) {
        super(context, "DBActivos", null, 1);
    }

    public boolean crear(Edificios edificios){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nombreedificio",edificios.getNombreedificio());

        boolean edificioCreado = db.insert("edificios",null,valores)>0;
        db.close();
        return edificioCreado;
    }
     public int contador(){
         SQLiteDatabase db =this.getReadableDatabase();
         String sql="Select * from edificios";

         int resultado=db.rawQuery(sql,null).getCount();
         db.close();

         return resultado;
     }
}
