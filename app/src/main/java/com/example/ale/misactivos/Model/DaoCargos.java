package com.example.ale.misactivos.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ale.misactivos.Operaciones.ConexionSqliteHelper;
import com.example.ale.misactivos.entidades.Cargos;
import com.example.ale.misactivos.entidades.Estados;

import java.util.ArrayList;

import static com.example.ale.misactivos.Operaciones.CreaTablas.NOMBREDB;

public class DaoCargos {

       ConexionSqliteHelper conexion;
        // SQLiteDatabase cx;
        ArrayList<Cargos> lista= new ArrayList<>();
        Cargos c;
        Context ct;

        //String tabla="create table if not exists estados(id integer primary key autoincrement, nombre text)";

        public DaoCargos(Context c) {
            this.ct = c;
            conexion= new ConexionSqliteHelper(c,NOMBREDB,null,1);

            //cx.execSQL(tabla);
        }

        public boolean insertar(Cargos c){
            SQLiteDatabase db=conexion.getWritableDatabase();
            ContentValues datos= new ContentValues();
            datos.put("nombrecargo", c.getNombrecargo());
            datos.put("estado","A");
            long res = db.insert("cargos",null,datos);
            db.close();
            if (res>0) {
                return true;
            }else return false;
        }

        public boolean eliminar(int id){
            SQLiteDatabase db=conexion.getWritableDatabase();
            ContentValues dato = new ContentValues();
            dato.put("estado","X");
            long res = db.update("cargos",dato,"id="+id,null);
            db.close();
            if (res>0) {
                return true;
            }else return false;

        }

        public boolean editar(Cargos p){
            SQLiteDatabase db=conexion.getWritableDatabase();
            ContentValues datos= new ContentValues();
            datos.put("nombrecargo", p.getNombrecargo());

            long res = db.update("cargos",datos,"id="+p.getId(),null);
            db.close();
            if (res>0) {
                return true;
            }else return false;
        }

        public ArrayList<Cargos> verTodos(){
            SQLiteDatabase db=conexion.getReadableDatabase();
            lista.clear();
            Cursor cursor= db.rawQuery("select * from cargos where estado='A'",null);
            if(cursor!=null && cursor.getCount()>0){
                cursor.moveToFirst();
                do{
                    lista.add(new Cargos(cursor.getInt(0),cursor.getString(1),""));

                }while(cursor.moveToNext());

            }
            db.close();
            cursor.close();
            return lista;
        }

        public Cargos verUno(int position){
            SQLiteDatabase db=conexion.getReadableDatabase();
            Cursor cursor= db.rawQuery("select * from cargos",null);
            cursor.moveToPosition(position);
            c= new Cargos(cursor.getInt(0),cursor.getString(1),"");
            db.close();
            cursor.close();
            return c;
        }
    }
