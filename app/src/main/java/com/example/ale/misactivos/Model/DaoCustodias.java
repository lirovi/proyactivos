package com.example.ale.misactivos.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ale.misactivos.Operaciones.ConexionSqliteHelper;
import com.example.ale.misactivos.entidades.Custodias;
import java.util.ArrayList;
import static com.example.ale.misactivos.Operaciones.CreaTablas.NOMBREDB;

public class DaoCustodias {

    ConexionSqliteHelper conexion;
    // SQLiteDatabase cx;
    ArrayList<Custodias> lista= new ArrayList<>();
    Custodias cus;
    Context ct;

    //String tabla="create table if not exists estados(id integer primary key autoincrement, nombre text)";

    public DaoCustodias(Context c) {
        this.ct = c;
        conexion= new ConexionSqliteHelper(c,NOMBREDB,null,1);

        //cx.execSQL(tabla);
    }

    public boolean insertar(Custodias c){
        SQLiteDatabase db=conexion.getWritableDatabase();
        ContentValues datos= new ContentValues();
        datos.put("cpbte", c.getCpbte());
        datos.put("gestion", c.getGestion());
        datos.put("fechacustodia", c.getFechacustodia());
        datos.put("custodioid", c.getCustodioid());
        datos.put("edificioid", c.getedificioid());
        datos.put("glosa", c.getGlosa());
        datos.put("estado","A");
        long res = db.insert("custodias",null,datos);
        db.close();
        if (res>0) {
            return true;
        }else return false;
    }

    public boolean eliminar(int id){
        SQLiteDatabase db=conexion.getWritableDatabase();
        ContentValues dato = new ContentValues();
        dato.put("estado","X");
        long res = db.update("custodias",dato,"id="+id,null);
        db.close();
        if (res>0) {
            return true;
        }else return false;

    }

    public boolean editar(Custodias c){
        SQLiteDatabase db=conexion.getWritableDatabase();
        ContentValues datos= new ContentValues();
        datos.put("cpbte", c.getCpbte());
        datos.put("gestion", c.getGestion());
        datos.put("fechacustodia", c.getFechacustodia());
        datos.put("custodioid", c.getCustodioid());
        datos.put("oficinaid", c.getedificioid());
        datos.put("glosa", c.getGlosa());

        long res = db.update("custodias",datos,"id="+c.getId(),null);
        db.close();
        if (res>0) {
            return true;
        }else return false;
    }
    public boolean limpiarTabla(){
        SQLiteDatabase db=conexion.getWritableDatabase();
        long res=0;
        Cursor cursor= db.rawQuery("select * from custodias",null);
        if(cursor != null && cursor.getCount()>0){
            res =  db.delete("custodias", null, null);
        }
        cursor= db.rawQuery("DELETE FROM SQLITE_SEQUENCE WHERE NAME = 'custodias'",null);
        cursor.close();
        db.close();

        return true;

    }
    public ArrayList<Custodias> verTodos(){
        SQLiteDatabase db=conexion.getReadableDatabase();
        lista.clear();
        Cursor cursor= db.rawQuery("select * from custodias where estado='A'",null);
        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                lista.add(new Custodias(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),""));

            }while(cursor.moveToNext());

        }
        db.close();
        cursor.close();
        return lista;
    }

    public Custodias verUno(int position){
        SQLiteDatabase db=conexion.getReadableDatabase();
        Cursor cursor= db.rawQuery("select * from custodias",null);
        cursor.moveToPosition(position);
        cus= new Custodias(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),"");
        db.close();
        cursor.close();
        return cus;
    }
}
