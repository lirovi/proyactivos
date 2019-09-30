package com.example.ale.misactivos.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.ale.misactivos.Operaciones.ConexionSqliteHelper;
import com.example.ale.misactivos.entidades.Departamentos;
import com.example.ale.misactivos.entidades.Estados;
import com.example.ale.misactivos.entidades.Oficinas;

import java.util.ArrayList;

public class DaoOficina {
    ConexionSqliteHelper conexion;
    // SQLiteDatabase cx;
    ArrayList<Oficinas> lista= new ArrayList<Oficinas>();
    Oficinas of;
    Context ct;
    String nombreBD="DBActivos";

    public DaoOficina(Context c) {
        this.ct = c;
        conexion= new ConexionSqliteHelper(c,nombreBD,null,1);

        //cx.execSQL(tabla);
    }

    public boolean insertar(Oficinas o){
        long res=0;
        try{
            SQLiteDatabase db=conexion.getWritableDatabase();
            ContentValues datos= new ContentValues();
            datos.put("nombreoficina", o.getNombreoficina());
            datos.put("edificioid", o.getOficinaid());
            datos.put("estado","A");
            res = db.insert("Oficinas",null,datos);
            db.close();

        } catch (Exception e) {
            Log.e("MYDB", "Error al insertar oficina");
        }
        if (res>0) {
            return true;
        }else return false;
    }

    public boolean eliminar(int id){
        long res=0;
        try{
            SQLiteDatabase db=conexion.getWritableDatabase();
            ContentValues datos= new ContentValues();
            datos.put("estado","X");
            //res = db.delete("oficinas","id="+id,null);   //Elimina definitivamente el registro
            res = db.update("oficinas",datos,"id="+id,null); //marca con X el estado del registro X= baja A= alta
            db.close();
        } catch (Exception e) {
            Log.e("MYDB", "Error al eliminar oficina");
        }
        if (res>0) {
            return true;
        }else return false;
    }

    public boolean editar(Oficinas o){
        long res=0;
        try{
            SQLiteDatabase db=conexion.getWritableDatabase();
            ContentValues datos= new ContentValues();
            datos.put("nombredpto", o.getNombreoficina());

            res = db.update("oficinas",datos,"id="+o.getId(),null);
            db.close();

        } catch (Exception e) {
            Log.e("MYDB", "Error al editar oficina");
        }
        if (res>0) {
            return true;
        }else return false;
    }

    public ArrayList<Oficinas> verTodos(){
        Cursor cursor;
        try{
            SQLiteDatabase db=conexion.getReadableDatabase();
            lista.clear();
            cursor= db.rawQuery("select * from oficinas",null);
            if(cursor!=null && cursor.getCount()>0){
                cursor.moveToFirst();
                do{
                    lista.add(new Oficinas(cursor.getInt(0),cursor.getString(1),cursor.getInt(2)));

                }while(cursor.moveToNext());

            }
            db.close();
            cursor.close();
        } catch (Exception e) {
            Log.e("MYDB", "Error al listar oficinas - verTodos");
        }
        return lista;
    }

    public Oficinas verUno(int position){
        Cursor cursor;
        try{
        SQLiteDatabase db=conexion.getReadableDatabase();
        cursor= db.rawQuery("select * from oficinas",null);
        cursor.moveToPosition(position);
        of=new Oficinas(cursor.getInt(0),cursor.getString(1),cursor.getInt(2));
        db.close();
        cursor.close();
        } catch (Exception e) {
            Log.e("MYDB", "Error al listar UN dpto - verUno");
        }
        return of;
    }
}
