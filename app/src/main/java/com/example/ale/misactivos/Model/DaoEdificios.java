package com.example.ale.misactivos.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.renderscript.Sampler;
import android.util.Log;

import com.example.ale.misactivos.Operaciones.ConexionSqliteHelper;
import com.example.ale.misactivos.entidades.Edificios;

import java.util.ArrayList;

import static com.example.ale.misactivos.Operaciones.CreaTablas.NOMBREDB;

public class DaoEdificios {
    ConexionSqliteHelper conexion;
    ArrayList<Edificios> lista= new ArrayList<Edificios>();
    Edificios e;
    Context ct;
 
    public DaoEdificios(Context c) {
        this.ct = c;
        conexion= new ConexionSqliteHelper(c,NOMBREDB,null,1);

    }

    public boolean insertar(Edificios e){
        SQLiteDatabase db=conexion.getWritableDatabase();
        ContentValues datos= new ContentValues();
        datos.put("codigo",e.getCodigo());
        datos.put("nombreedificio", e.getNombreedificio());
        datos.put("estado","A");
        long res = db.insert("edificios",null,datos);
        db.close();
        if (res>0) {
            return true;
        }else return false;
    }

    public boolean eliminar(int id){
        SQLiteDatabase db=conexion.getWritableDatabase();
        //long res = db.delete("Edificios","id="+id,null);
        ContentValues dato= new ContentValues();
        dato.put("estado","X");
        long res = db.update("edificios",dato,"id="+id,null);
        db.close();
        if (res>0) {
            return true;
        }else return false;

    }
    public boolean limpiarTabla(){
        SQLiteDatabase db=conexion.getWritableDatabase();
        long res=0;
        Cursor cursor= db.rawQuery("select * from edificios",null);
        if(cursor != null && cursor.getCount()>0){
            res =  db.delete("edificios", null, null);
        }
        cursor.close();
        db.close();

       return true;

    }

    public boolean editar(Edificios e){
        SQLiteDatabase db=conexion.getWritableDatabase();
        ContentValues datos= new ContentValues();
        datos.put("codigo", e.getCodigo());
        datos.put("nombreedificio", e.getNombreedificio());
        long res = db.update("Edificios",datos,"id="+e.getId(),null);
        db.close();
        if (res>0) {
            return true;
        }else return false;
    }

    public ArrayList<Edificios> verTodos(){
        SQLiteDatabase db = conexion.getReadableDatabase();
        lista.clear();
        Cursor cursor= db.rawQuery("select * from edificios where estado='A'",null);
        if(cursor != null && cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                lista.add(new Edificios(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));
                Log.i("MyDB",""+(cursor.getInt(0)));
                Log.i("MyDB",cursor.getString(1));
                Log.i("MyDB", cursor.getString(2));
            }while(cursor.moveToNext());

        }
        db.close();
        cursor.close();
        return lista;
    }

    public Edificios verUno(int position){
        SQLiteDatabase db=conexion.getReadableDatabase();
        Cursor cursor= db.rawQuery("select * from edificios where estado='A'",null);
        cursor.moveToPosition(position);
        e=new Edificios(cursor.getString(0),cursor.getString(1));
        db.close();
        cursor.close();
        return e;
    }
}
