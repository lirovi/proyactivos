package com.example.ale.misactivos.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ale.misactivos.Operaciones.ConexionSqliteHelper;
import com.example.ale.misactivos.entidades.Profesiones;

import java.util.ArrayList;

import static com.example.ale.misactivos.Operaciones.CreaTablas.NOMBREDB;


public class DaoProfesiones {
    ConexionSqliteHelper conexion;
    ArrayList<Profesiones> lista= new ArrayList<Profesiones>();
    Profesiones p;
    Context ct;

    public DaoProfesiones(Context c) {
        this.ct = c;
        conexion= new ConexionSqliteHelper(c,NOMBREDB,null,1);
    }

    public boolean insertar(Profesiones p){
        SQLiteDatabase db=conexion.getWritableDatabase();
        ContentValues datos= new ContentValues();
        datos.put("nombreprof", p.getNombreprof());
        datos.put("estado","A");
        long res = db.insert("Profesiones",null,datos);
        db.close();
        if (res>0) {
            return true;
        }else return false;
    }

    public boolean eliminar(int id){
        SQLiteDatabase db=conexion.getWritableDatabase();
        ContentValues dato = new ContentValues();
        dato.put("estado","X");
        long res = db.update("Profesiones",dato,"id="+id,null);
        db.close();
        if (res>0) {
            return true;
        }else return false;

    }

    public boolean editar(Profesiones p){
        SQLiteDatabase db=conexion.getWritableDatabase();
        ContentValues datos= new ContentValues();
        datos.put("nombreprof", p.getNombreprof());

        long res = db.update("Profesiones",datos,"id="+p.getId(),null);
        db.close();
        if (res>0) {
            return true;
        }else return false;
    }

    public ArrayList<Profesiones> verTodos(){
        SQLiteDatabase db=conexion.getReadableDatabase();
        lista.clear();
        Cursor cursor= db.rawQuery("select * from Profesiones where estado='A'",null);
        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                lista.add(new Profesiones(cursor.getInt(0),cursor.getString(1)));

            }while(cursor.moveToNext());

        }
        db.close();
        cursor.close();
        return lista;
    }

    public Profesiones verUno(int position){
        SQLiteDatabase db=conexion.getReadableDatabase();
        Cursor cursor= db.rawQuery("select * from Profesiones",null);
        cursor.moveToPosition(position);
        p=new Profesiones(cursor.getInt(0),cursor.getString(1),"");
        db.close();
        cursor.close();
        return p;
    }
}
