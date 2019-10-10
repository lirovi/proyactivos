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

import static com.example.ale.misactivos.Operaciones.CreaTablas.NOMBREDB;

public class DaoOficina {
    ConexionSqliteHelper conexion;
    // SQLiteDatabase cx;
    ArrayList<Oficinas> lista= new ArrayList<Oficinas>();
    Oficinas of;
    Context ct;


    public DaoOficina(Context c) {
        this.ct = c;
        conexion= new ConexionSqliteHelper(c,NOMBREDB,null,1);

    }

    public boolean insertar(Oficinas o){
        long res=0;
        try{
            SQLiteDatabase db=conexion.getWritableDatabase();
            ContentValues datos= new ContentValues();
            datos.put("nombreoficina", o.getNombreoficina());
            datos.put("estado","A");
            datos.put("edificioid", o.getEdificioid());
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
            datos.put("nombreoficina", o.getNombreoficina());
            datos.put("edificioid",o.getEdificioid());

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
            cursor= db.rawQuery("select oficinas.id,oficinas.nombreoficina,oficinas.estado,edificios.nombreedificio " +
                    "from oficinas , edificios  \n" +
                    "where oficinas.edificioid = edificios.codigo and oficinas.estado='A'",null);

            if(cursor!=null && cursor.getCount()>0){
                cursor.moveToFirst();
                do{
                    String nombre=cursor.getString(1);
                    if(nombre.length()>15){
                        nombre=nombre.substring(0,15);
                    }
                    String codigo=cursor.getString(3);
                    if(codigo.length()>15){
                        codigo=codigo.substring(0,15);
                    }
                    lista.add(new Oficinas(cursor.getInt(0),nombre,codigo));

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
        cursor= db.rawQuery("select oficinas.id,oficinas.nombreoficina,oficinas.estado,edificios.nombreedificio \n" +
                "from oficinas , edificios  \n" +
                "where oficinas.edificioid = edificios.codigo and oficinas.estado='A'",null);
        cursor.moveToPosition(position);
            String nombre=cursor.getString(1);
            if(nombre.length()>15){
                nombre=nombre.substring(0,15);
            }
            String codigo=cursor.getString(3);
            if(codigo.length()>15){
                codigo=codigo.substring(0,15);
            }
            of= new Oficinas(cursor.getInt(0),nombre,codigo);
        //of=new Oficinas(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
        db.close();
        cursor.close();
        } catch (Exception e) {
            Log.e("MYDB", "Error al listar UNA Oficina - verUno");
        }
        return of;
    }
}
