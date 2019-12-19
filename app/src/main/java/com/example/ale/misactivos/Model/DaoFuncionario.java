package com.example.ale.misactivos.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.ale.misactivos.Operaciones.ConexionSqliteHelper;
import com.example.ale.misactivos.entidades.Funcionarios;

import java.util.ArrayList;

import static com.example.ale.misactivos.Operaciones.CreaTablas.NOMBREDB;

public class DaoFuncionario {
    ConexionSqliteHelper conexion;
    ArrayList<Funcionarios> lista= new ArrayList<Funcionarios>();
    Funcionarios fu;
    Context ct;
    String nombreBD=NOMBREDB;

    public DaoFuncionario(Context c) {
        this.ct = c;
        conexion= new ConexionSqliteHelper(c,nombreBD,null,1);
    }

    public boolean insertar(Funcionarios func){
        long res=0;
        try{
            SQLiteDatabase db=conexion.getWritableDatabase();
            ContentValues datos= new ContentValues();
            datos.put("nombre", func.getNombre());
            datos.put("apellidou", func.getApellidou());
            datos.put("apellidod", func.getApellidod());
            datos.put("direccion", func.getDireccion());
            datos.put("telefono", func.getTelefono());
            datos.put("cargoid", 1);
            datos.put("profesionid", 1);
            datos.put("tipodocid", 1);
            datos.put("nrodoc", func.getNrodoc());
            datos.put("nacionalidad", func.getNacionalidad());
            datos.put("sexo", "1");
            datos.put("estado","A");
            res = db.insert("Funcionarios",null,datos);
            db.close();

        } catch (Exception e) {
            Log.e("MYDB", "Error al insertar Funcionarios");
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
            //res = db.delete("Funcionarios","id="+id,null);   //Elimina definitivamente el registro
            res = db.update("Funcionarios",datos,"id="+id,null); //marca con X el estado del registro X= baja A= alta
            db.close();
        } catch (Exception e) {
            Log.e("MYDB", "Error al eliminar Funcionarios");
        }
        if (res>0) {
            return true;
        }else return false;
    }

    public boolean editar(Funcionarios func){
        long res=0;
        try{
            SQLiteDatabase db=conexion.getWritableDatabase();
            ContentValues datos= new ContentValues();
            datos.put("nombre", func.getNombre());
            datos.put("apellidou", func.getApellidou());
            datos.put("apellidod", func.getApellidod());
            datos.put("direccion", func.getDireccion());
            datos.put("telefono", func.getTelefono());
            datos.put("cargoid", 1);
            datos.put("profesionid", 1);
            datos.put("tipodocid", 1);
            datos.put("nrodoc", func.getNrodoc());
            datos.put("nacionalidad", func.getNacionalidad());
            datos.put("sexo", "1");

            res = db.update("Funcionarios",datos,"id="+func.getId(),null);
            db.close();

        } catch (Exception e) {
            Log.e("MYDB", "Error al editar Funcionarios");
        }
        if (res>0) {
            return true;
        }else return false;
    }

    public ArrayList<Funcionarios> verTodos(){

        try{
            SQLiteDatabase db = conexion.getReadableDatabase();
            lista.clear();
            Cursor cursor = db.rawQuery("select id, nrodoc, nombre, apellidou from funcionarios where estado='A'",null);
            if(cursor!=null && cursor.getCount()>0){
                cursor.moveToFirst();
                do{
                    lista.add(new Funcionarios(cursor.getString(1),cursor.getString(2),cursor.getString(3)));

                }while(cursor.moveToNext());

            }
            db.close();
            cursor.close();
        } catch (Exception e) {
            Log.e("MYDB", "Error al listar Funcionarios - verTodos");
        }
        return lista;
    }
    public boolean limpiarTabla(){
       SQLiteDatabase db=conexion.getWritableDatabase();
        long res=0;
        Cursor cursor= db.rawQuery("select * from funcionarios",null);
        if(cursor != null && cursor.getCount()>0) {
            res = db.delete("funcionarios", "id>" + 0, null);
        }
        cursor.close();
        db.close();
        /*if (res>0) {
            return true;
        }else return false;*/
        return true;

    }


    public Funcionarios verUno(int position){
        Cursor cursor;
        try{
            SQLiteDatabase db=conexion.getReadableDatabase();
            cursor= db.rawQuery("select * from Funcionarios where estado='A'",null);
            cursor.moveToPosition(position);
            fu=new Funcionarios(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
            db.close();
            cursor.close();
        } catch (Exception e) {
            Log.e("MYDB", "Error al listar UN Funcionario - verUno");
        }
        return fu;
    }

    public ArrayList<Funcionarios> verTodosXfiltro(String ed) {
        Cursor cursor;
        try{
            SQLiteDatabase db=conexion.getReadableDatabase();
            lista.clear();
            String sql="Select * from funcionarios where nrodoc in ( SELECT c.custodioid from Custodias c " +
                    " Where c.estado='A' and c.edificioid like '"+ed+"%')";
            cursor= db.rawQuery(sql,null);
            //cursor= db.rawQuery("SELECT funcionarios.* " +
             //       "from custodias , funcionarios " +
               //     "where custodias.custodioid=funcionarios.id " +
                //    "and custodias.edificioid="+codedificios,null);
            if(cursor!=null && cursor.getCount()>0){
                cursor.moveToFirst();
                do{
                    lista.add(new Funcionarios(cursor.getString(9),cursor.getString(1),cursor.getString(2)));

                }while(cursor.moveToNext());

            }
            db.close();
            cursor.close();
        } catch (Exception e) {
            Log.e("MYDB", "Error :"+ e.getMessage());
        }
        return lista;
    }
}
