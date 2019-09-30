package com.example.ale.misactivos.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.ale.misactivos.Operaciones.ConexionSqliteHelper;
import com.example.ale.misactivos.entidades.Tipodocumentos;

import java.util.ArrayList;

public class DaoTipoDocumento {
    ConexionSqliteHelper conexion;
    // SQLiteDatabase cx;
    ArrayList<Tipodocumentos> lista= new ArrayList<Tipodocumentos>();
    Tipodocumentos td;
    Context ct;
    String nombreBD="DBActivoss";

    public DaoTipoDocumento(Context c) {
        this.ct = c;
        conexion= new ConexionSqliteHelper(c,nombreBD,null,1);
    }

    public boolean insertar(Tipodocumentos td){
        SQLiteDatabase db = conexion.getWritableDatabase();
        long res=0;
        try {

            ContentValues datos = new ContentValues();
            datos.put("nombredoc", td.getNombredoc());
            datos.put("sigla", td.getSigla());
            datos.put("estado", "A");
            res = db.insert("tiposdocumentos", null, datos);
        }catch (Exception e){
            Log.e("MYDB", "Error al insertar TiposDoc");
        }
        db.close();
        if (res>0) {
            return true;
        }else {
            Toast.makeText(ct.getApplicationContext(),"Registro fallido",Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public boolean eliminar(int id){
        long res=0;
        try{
            SQLiteDatabase db=conexion.getWritableDatabase();
            ContentValues dato = new ContentValues();
            dato.put("estado","X");
            res = db.update("Tiposdocumentos",dato,"id="+id,null);
            db.close();
        } catch (Exception e) {
            Log.e("MYDB", "Error al eliminar TiposDoc");
        }
        if (res>0) {
            return true;
        }else return false;

    }

    public boolean editar(Tipodocumentos td){
        long res=0;
        try{
            SQLiteDatabase db=conexion.getWritableDatabase();
            ContentValues datos= new ContentValues();
            datos.put("nombredoc", td.getNombredoc());
            datos.put("sigla", td.getSigla());

             res = db.update("tiposdocumentos",datos,"id="+td.getId(),null);
            db.close();
        } catch (Exception e) {
            Log.e("MYDB", "Error al editar TiposDoc");
        }
        if (res>0) {
            return true;
        }else return false;
    }

    public ArrayList<Tipodocumentos> verTodos(){
        SQLiteDatabase db=conexion.getReadableDatabase();
        lista.clear();
        try {
            Cursor cursor = db.rawQuery("select * from tiposdocumentos where estado='A'", null);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    lista.add(new Tipodocumentos(cursor.getInt(0), cursor.getString(1),cursor.getString(2)));

                } while (cursor.moveToNext());

            }

            cursor.close();
        }catch (Exception e){
            Log.e("MYDB", "Error al listar TipoDoc - verTodos");
        }
        db.close();
        return lista;
    }

    public Tipodocumentos verUno(int position){
        SQLiteDatabase db=conexion.getReadableDatabase();
        Cursor cursor= db.rawQuery("select * from tiposdocumentos",null);
        cursor.moveToPosition(position);
        td=new Tipodocumentos(cursor.getInt(0),cursor.getString(1),"");
        db.close();
        cursor.close();
        return td;
    }
}
