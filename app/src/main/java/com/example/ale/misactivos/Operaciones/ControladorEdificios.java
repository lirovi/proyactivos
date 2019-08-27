package com.example.ale.misactivos.Operaciones;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ale.misactivos.entidades.Edificios;

import java.util.ArrayList;
import java.util.List;

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
         String sql = "Select * from edificios";

         int resultado = db.rawQuery(sql,null).getCount();
         db.close();

         return resultado;
     }

     public List<Edificios> leer(){
        List<Edificios> listaEdificios= new ArrayList<>();

        String sql="Select * from Edificios Order by id Desc";

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do{
                String columnName;
                int id = cursor.getColumnIndex("id");
                String nombre=cursor.getString(cursor.getColumnIndex("nombreedificio"));

                Edificios edificios = new Edificios();
                edificios.setId(id);
                edificios.setNombreedificio(nombre);

                listaEdificios.add(edificios);


            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listaEdificios;
     }
    public Edificios leerEdificio(Integer datoid){
        Edificios objedificio= new Edificios();

        String sql="Select * from Edificios where id="+datoid;

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(sql,null);

        if(cursor.getCount()>0){
               String columnName;
                int id = cursor.getColumnIndex("id");
                String nombre=cursor.getString(cursor.getColumnIndex("nombreedificio"));

                objedificio.setId(id);
                objedificio.setNombreedificio(nombre);

        }
        cursor.close();
        db.close();
        return objedificio;
    }
}
