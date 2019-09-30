package com.example.ale.misactivos.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ale.misactivos.Operaciones.ConexionSqliteHelper;
import com.example.ale.misactivos.entidades.Estados;
import com.example.ale.misactivos.entidades.Estados;

import java.util.ArrayList;

public class DaoEstado {
    ConexionSqliteHelper conexion;
    // SQLiteDatabase cx;
     ArrayList<Estados> lista= new ArrayList<Estados>();
     Estados e;
     Context ct;
     String nombreBD="DBActivos";
     //String tabla="create table if not exists estados(id integer primary key autoincrement, nombre text)";

    public DaoEstado(Context c) {
        this.ct = c;
        conexion= new ConexionSqliteHelper(c,nombreBD,null,1);

        //cx.execSQL(tabla);
    }

    public boolean insertar(Estados p){
        SQLiteDatabase db=conexion.getWritableDatabase();
        ContentValues datos= new ContentValues();
        datos.put("nombre", p.getNombre());
        datos.put("estado","A");
        long res = db.insert("Estados",null,datos);
        db.close();
        if (res>0) {
            return true;
        }else return false;
    }

    public boolean eliminar(int id){
        SQLiteDatabase db=conexion.getWritableDatabase();
        ContentValues dato = new ContentValues();
        dato.put("estado","X");
        long res = db.update("Estados",dato,"id="+id,null);
        db.close();
        if (res>0) {
            return true;
        }else return false;

    }

    public boolean editar(Estados p){
        SQLiteDatabase db=conexion.getWritableDatabase();
        ContentValues datos= new ContentValues();
        datos.put("nombre", p.getNombre());

        long res = db.update("Estados",datos,"id="+p.getId(),null);
        db.close();
        if (res>0) {
            return true;
        }else return false;
    }

    public ArrayList<Estados> verTodos(){
        SQLiteDatabase db=conexion.getReadableDatabase();
        lista.clear();
        Cursor cursor= db.rawQuery("select * from Estados",null);
        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                lista.add(new Estados(cursor.getInt(0),cursor.getString(1),""));

            }while(cursor.moveToNext());

        }
        db.close();
        cursor.close();
        return lista;
    }

    public Estados verUno(int position){
        SQLiteDatabase db=conexion.getReadableDatabase();
        Cursor cursor= db.rawQuery("select * from Estados",null);
        cursor.moveToPosition(position);
        e=new Estados(cursor.getInt(0),cursor.getString(1),"");
        db.close();
        cursor.close();
        return e;
    }
}
