package com.example.ale.misactivos.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ale.misactivos.Operaciones.ConexionSqliteHelper;
import com.example.ale.misactivos.entidades.Edificios;

import java.util.ArrayList;

public class DaoEdificios {
    ConexionSqliteHelper conexion;
    ArrayList<Edificios> lista= new ArrayList<Edificios>();
    Edificios e;
    Context ct;
    String nombreBD="DBActivos";
 
    public DaoEdificios(Context c) {
        this.ct = c;
        conexion= new ConexionSqliteHelper(c,nombreBD,null,1);

    }

    public boolean insertar(Edificios e){
        SQLiteDatabase db=conexion.getWritableDatabase();
        ContentValues datos= new ContentValues();
        datos.put("nombreedificio", e.getNombreedificio());
        long res = db.insert("Edificios",null,datos);
        db.close();
        if (res>0) {
            return true;
        }else return false;
    }

    public boolean eliminar(int id){
        SQLiteDatabase db=conexion.getWritableDatabase();
        long res = db.delete("Edificios","id="+id,null);
        db.close();
        if (res>0) {
            return true;
        }else return false;

    }

    public boolean editar(Edificios e){
        SQLiteDatabase db=conexion.getWritableDatabase();
        ContentValues datos= new ContentValues();
        datos.put("nombreedificio", e.getNombreedificio());

        long res = db.update("Edificios",datos,"id="+e.getId(),null);
        db.close();
        if (res>0) {
            return true;
        }else return false;
    }

    public ArrayList<Edificios> verTodos(){
        SQLiteDatabase db=conexion.getReadableDatabase();
        lista.clear();
        Cursor cursor= db.rawQuery("select * from Edificios",null);
        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                lista.add(new Edificios(cursor.getInt(0),cursor.getString(1)));

            }while(cursor.moveToNext());

        }
        db.close();
        cursor.close();
        return lista;
    }

    public Edificios verUno(int position){
        SQLiteDatabase db=conexion.getReadableDatabase();
        Cursor cursor= db.rawQuery("select * from Edificios",null);
        cursor.moveToPosition(position);
        e=new Edificios(cursor.getInt(0),cursor.getString(1));
        db.close();
        cursor.close();
        return e;
    }
}
