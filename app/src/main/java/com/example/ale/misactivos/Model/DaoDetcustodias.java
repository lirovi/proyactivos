package com.example.ale.misactivos.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ale.misactivos.Operaciones.ConexionSqliteHelper;
import com.example.ale.misactivos.entidades.Detcustodias;

import java.util.ArrayList;

import static com.example.ale.misactivos.Operaciones.CreaTablas.NOMBREDB;

public class DaoDetcustodias {
    ConexionSqliteHelper conexion;
    ArrayList<Detcustodias> lista= new ArrayList<>();
    Detcustodias dc;
    Context ct;

    public DaoDetcustodias(Context c) {
        this.ct = c;
        conexion= new ConexionSqliteHelper(c,NOMBREDB,null,1);

    }

    public boolean insertar(Detcustodias dc){
        SQLiteDatabase db=conexion.getWritableDatabase();
        ContentValues datos= new ContentValues();
        datos.put("cpbte",dc.getCpbte());
        datos.put("gestion", dc.getGestion());
        datos.put("activoid", dc.getActivoid());
        datos.put("estadoactual", dc.getEstadoactual());
        datos.put("estado", dc.getEstado());

        long res = db.insert("detcustodias",null,datos);
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
        long res = db.update("detcustodias",dato,"id="+id,null);
        db.close();
        if (res>0) {
            return true;
        }else return false;

    }
    public boolean limpiarTabla(){
        SQLiteDatabase db=conexion.getWritableDatabase();
        long res=0;
        Cursor cursor= db.rawQuery("select * from detcustodias",null);
        if(cursor != null && cursor.getCount()>0){
            res =  db.delete("detcustodias", null, null);
        }
        cursor= db.rawQuery("DELETE FROM SQLITE_SEQUENCE WHERE NAME = detcustodias",null);
        cursor.close();
        db.close();

        return true;

    }

    public boolean editar(Detcustodias dc){
        SQLiteDatabase db=conexion.getWritableDatabase();
        ContentValues datos= new ContentValues();
        datos.put("cpbte",dc.getCpbte());
        datos.put("gestion", dc.getGestion());
        datos.put("activoid", dc.getActivoid());
        datos.put("estadoactual", dc.getEstadoactual());
        datos.put("estado", dc.getEstado());

        long res = db.update("Detcustodias",datos,"id="+dc.getCpbte()+" and gestion="+dc.getGestion(),null);
        db.close();
        if (res>0) {
            return true;
        }else return false;
    }

    public ArrayList<Detcustodias> verTodos(){
        SQLiteDatabase db = conexion.getReadableDatabase();
        lista.clear();
        Cursor cursor= db.rawQuery("select * from Detcustodias ",null);
        if(cursor != null && cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                lista.add(new Detcustodias(cursor.getString(1),cursor.getInt(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)));

            }while(cursor.moveToNext());

        }
        db.close();
        cursor.close();
        return lista;
    }

    public Detcustodias verUno(int position){
        SQLiteDatabase db=conexion.getReadableDatabase();
        Cursor cursor= db.rawQuery("select * from detcustodias where estado='A'",null);
        cursor.moveToPosition(position);
        dc=new Detcustodias(cursor.getString(1),cursor.getInt(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
        db.close();
        cursor.close();
        return dc;
    }
}
