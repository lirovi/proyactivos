package com.example.ale.misactivos.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.ale.misactivos.Operaciones.ConexionSqliteHelper;
import com.example.ale.misactivos.entidades.Activos;

import java.util.ArrayList;

import static com.example.ale.misactivos.Operaciones.CreaTablas.NOMBREDB;

public class DaoActivos {

        ConexionSqliteHelper conexion;
        ArrayList<Activos> lista= new ArrayList<Activos>();
        Activos activos;
        Context ct;

        public DaoActivos(Context c) {
            this.ct = c;
            conexion= new ConexionSqliteHelper(c,NOMBREDB,null,1);
        }
        public boolean insertarActivo(Activos a){
            long res=0;
            Log.i("MyDB","Est.Fisico: "+a.getESTADOFISICO());
            Log.i("MyDB","OBS: "+a.getOBSERVACION());
            double va = getValorDouble(a.getVALOR());
            String valres="0";
            if(a.getVALOR_RESIDUAL()==null ||a.getVALOR_RESIDUAL().isEmpty())
                valres="0";
            else
                valres=a.getVALOR_RESIDUAL();

            double va_r = getValorDouble(valres);

            try{
                SQLiteDatabase db=conexion.getWritableDatabase();
                ContentValues datos= new ContentValues();
                datos.put("Codigo", a.getCodigo());
                datos.put("correlativo", a.getCORRELATIVO());
                datos.put("tipo", a.getTIPO());
                datos.put("descripcion", a.getDESCRIPCION());
                datos.put("unidad", a.getUNIDAD());
                datos.put("fecha_ing", a.getFECHA_INGRESO());
                datos.put("valor", va);
                datos.put("valor_residual", va_r);
                datos.put("estadofisico", a.getESTADOFISICO());
                datos.put("estadobd", a.getESTADO_BD());
                datos.put("observacion", a.getOBSERVACION());
                datos.put("grupo",a.getGRUPO());
                datos.put("auxiliar", a.getAUXILIAR());
                datos.put("gestion_ing", a.getGESTION_INGRESO());
                datos.put("partida", a.getPARTIDA());
                datos.put("glosa", a.getGLOSA());
                datos.put("color", a.getCOLOR());
                datos.put("serie", a.getSERIE());
                datos.put("marca", a.getMARCA());
                datos.put("modelo",a.getMODELO());
                datos.put("nroplaca", a.getPLACA());
                datos.put("gestion_baja", a.getGESTION_BAJA());
                datos.put("baja", a.getBAJA());
                datos.put("ubi_geog", a.getUBI_GEOGRAFICA());
                datos.put("origen", a.getORIGEN());
                datos.put("cambio", "N");
                datos.put("ruta_imagen", a.getRUTA_IMAGEN());
                datos.put("estado", "A");

                res = db.insert("Activos",null,datos);
                db.close();

            } catch (Exception e) {
                Log.e("MYDB", "Error: "+e.getMessage());
            }
            if (res>0) {
                return true;
            }else return false;
        }
        public double getValorDouble(String dato){
        if(dato.contains(",")){
            return Double.parseDouble(dato.replace(",", ".").trim());
        }
        return Double.parseDouble(dato.trim());
    }

        public boolean eliminar(String codigo){
            long res=0;
            try{
                SQLiteDatabase db=conexion.getWritableDatabase();
                ContentValues datos= new ContentValues();
                datos.put("estado","X");
                //res = db.delete("Funcionarios","id="+id,null);   //Elimina definitivamente el registro
                res = db.update("Activos",datos,"codigo="+codigo,null); //marca con X el estado del registro X= baja A= alta
                db.close();
            } catch (Exception e) {
                Log.e("MYDB", "Error al eliminar el Activo");
            }
            if (res>0) {
                return true;
            }else return false;
        }

        public boolean editar(Activos a){
            int res=0;
            double va = getValorDouble(a.getVALOR());
            double va_r = getValorDouble(a.getVALOR_RESIDUAL());
            try{
                SQLiteDatabase db=conexion.getWritableDatabase();
                ContentValues datos= new ContentValues();
                /*datos.put("Codigo", a.getCodigo());
                datos.put("correlativo", Integer.parseInt(a.getCORRELATIVO().toString()));
                datos.put("tipo", a.getTIPO());
                datos.put("descripcion", a.getDESCRIPCION());
                datos.put("unidad", a.getUNIDAD());
                datos.put("fecha_ing", a.getFECHA_INGRESO());
                datos.put("valor", va);
                datos.put("valor_residual", va_r);*/
                datos.put("estadofisico", a.getESTADOFISICO());
                //datos.put("estadobd", a.getESTADO_BD());
                datos.put("observacion", a.getOBSERVACION());
               /* datos.put("grupo",a.getGRUPO());
                datos.put("auxiliar", a.getAUXILIAR());
                datos.put("gestion_ing", Integer.parseInt(a.getGESTION_INGRESO().toString()));
                datos.put("partida", a.getPARTIDA());*/
                datos.put("glosa", a.getGLOSA());
                datos.put("color", a.getCOLOR());
                datos.put("serie", a.getSERIE());
                datos.put("marca", a.getMARCA());
                datos.put("modelo",a.getMODELO());
                datos.put("nroplaca", a.getPLACA());
               /* datos.put("gestion_baja", Integer.parseInt(a.getGESTION_BAJA().toString()));
                datos.put("baja", a.getBAJA());
                datos.put("ubi_geog", a.getUBI_GEOGRAFICA());
                datos.put("origen", a.getORIGEN());

                datos.put("ruta_imagen", a.getRUTA_IMAGEN()); //a.getRUTA_IMAGEN());
                datos.put("estado", a.getESTADO());*/
               datos.put("cambio", a.getCAMBIO());
                String[] args = new String[]{a.getCodigo().toString()};
                 res = db.update("Activos",datos,"codigo  = ?",args);
                db.close();

            } catch (Exception e) {
                Log.e("MYDB", "Error al editar Activo: "+e.getMessage());
            }
            if (res>0) {

                return true;
            }else return false;
        }

        public ArrayList<Activos> verTodos(){

            try{
                SQLiteDatabase db = conexion.getReadableDatabase();
                lista.clear();
                Cursor cursor = db.rawQuery("select * from Activos",null);
                if(cursor!=null && cursor.getCount()>0){
                    cursor.moveToFirst();
                    do{
                        lista.add(new Activos(
                                cursor.getString(1),
                                cursor.getInt(2),
                               cursor.getString(3),
                                cursor.getString(4),
                                cursor.getString(5),
                                cursor.getString(6),
                                cursor.getString(7),
                                cursor.getString(8),
                                cursor.getString(9),
                                cursor.getString(10),
                                cursor.getString(11),
                                cursor.getString(12),
                                cursor.getString(13),
                                cursor.getInt(14),
                                cursor.getString(15),
                                cursor.getString(16),
                                cursor.getString(17),
                                cursor.getString(18),
                                cursor.getString(19),
                                cursor.getString(20),
                                cursor.getString(21),
                                cursor.getString(22),
                                cursor.getInt(23),
                                cursor.getString(24),
                                cursor.getString(25)));

                    }while(cursor.moveToNext());

                }
                db.close();
                cursor.close();
            } catch (Exception e) {
                Toast.makeText(ct,"Error al listar Activos - verTodos",Toast.LENGTH_LONG).show();
            }
            return lista;
        }
    public ArrayList<Activos> verActivosCambios(){

        try{
            SQLiteDatabase db = conexion.getReadableDatabase();
            lista.clear();
            Cursor cursor = db.rawQuery("select * from Activos where cambio='S'",null);
            if(cursor!=null && cursor.getCount()>0){
                cursor.moveToFirst();
                do{
                    lista.add(new Activos(
                            cursor.getString(1),
                            cursor.getString(4),
                            cursor.getString(9),
                            cursor.getString(11),
                            cursor.getString(16),
                            cursor.getString(17),
                            cursor.getString(18),
                            cursor.getString(19),
                            cursor.getString(20),
                            cursor.getString(21)));

                }while(cursor.moveToNext());

            }
            db.close();
            cursor.close();
        } catch (Exception e) {
            Toast.makeText(ct,"Error al listar Activos - Con cambios",Toast.LENGTH_LONG).show();
        }
        return lista;
    }
        public boolean limpiarTabla(){
            SQLiteDatabase db=conexion.getWritableDatabase();
            long res=0;
            Cursor cursor= db.rawQuery("select * from Activos",null);
            if(cursor != null && cursor.getCount()>0) {
                 res= db.delete("activos", "correlativo>0", null);
            }
            cursor= db.rawQuery("DELETE FROM SQLITE_SEQUENCE WHERE NAME = 'activos'",null);
            db.close();
            cursor.close();

            return true;

        }
        public ArrayList<Activos> verActivosXfiltro(String codigo){
            Cursor cursor;
            try{
                SQLiteDatabase db=conexion.getReadableDatabase();
                lista.clear();
                //cursor= db.rawQuery("SELECT personas.* " +
                //                        "from efectos_custodia , personas " +
                //                        "where efectos_custodia.funcionario=personas.documento " +
                //                        "and efectos_custodia.edificio="+codedificios,null);
                cursor= db.rawQuery("SELECT Activos.* " +
                        "from custodias , funcionarios " +
                        "where custodias.custodioid=funcionarios.id " +
                        "and custodias.edificioid="+codigo,null);
                if(cursor!=null && cursor.getCount()>0){
                    cursor.moveToFirst();
                    do{
                        lista.add(new Activos(cursor.getString(1),cursor.getInt(2),
                                cursor.getString(3),cursor.getString(4)));/*,
                                cursor.getString(4),cursor.getString(5),
                                cursor.getFloat(6),cursor.getFloat(7),
                                cursor.getInt(8),cursor.getString(9),
                                cursor.getInt(10),cursor.getString(11),
                                cursor.getString(12),cursor.getInt(13),
                                cursor.getString(14),cursor.getString(15),
                                cursor.getString(16),cursor.getString(17),
                                cursor.getString(18),cursor.getString(19),
                                cursor.getString(20),cursor.getString(21),
                                cursor.getInt(22),cursor.getString(23),
                                cursor.getString(24)));*/

                    }while(cursor.moveToNext());

                }
                db.close();
                cursor.close();
            } catch (Exception e) {
                Log.e("MYDB", "Error al listar Activos - verTodos");
            }
            return lista;
        }

        public Activos verUno(int position){
            Cursor cursor;
            try{
                SQLiteDatabase db=conexion.getReadableDatabase();
                cursor= db.rawQuery("select * from Activos ",null);
                cursor.moveToPosition(position);
                activos=new Activos(cursor.getString(1),cursor.getInt(2),
                        cursor.getString(3),cursor.getString(4));/*,
                        cursor.getString(4),cursor.getString(5),
                        cursor.getFloat(6),cursor.getFloat(7),
                        cursor.getInt(8),cursor.getString(9),
                        cursor.getInt(10),cursor.getString(11),
                        cursor.getString(12),cursor.getInt(13),
                        cursor.getString(14),cursor.getString(15),
                        cursor.getString(16),cursor.getString(17),
                        cursor.getString(18),cursor.getString(19),
                        cursor.getString(20),cursor.getString(21),
                        cursor.getInt(22),cursor.getString(23),
                        cursor.getString(24));*/

                db.close();
                cursor.close();
            } catch (Exception e) {
                Log.e("MYDB", "Error al listar UN Activo - verUno");
            }
            return activos;
        }
    public Activos verUnoXCodigo(String Cod){
        Cursor cursor;
        try{
            SQLiteDatabase db=conexion.getReadableDatabase();
            cursor= db.rawQuery("select * from Activos where codigo="+Cod,null);
            cursor.moveToFirst();
            activos=new Activos(cursor.getString(1),cursor.getInt(2),
                    cursor.getString(3),cursor.getString(4));/*,
                        cursor.getString(4),cursor.getString(5),
                        cursor.getFloat(6),cursor.getFloat(7),
                        cursor.getInt(8),cursor.getString(9),
                        cursor.getInt(10),cursor.getString(11),
                        cursor.getString(12),cursor.getInt(13),
                        cursor.getString(14),cursor.getString(15),
                        cursor.getString(16),cursor.getString(17),
                        cursor.getString(18),cursor.getString(19),
                        cursor.getString(20),cursor.getString(21),
                        cursor.getInt(22),cursor.getString(23),
                        cursor.getString(24));*/

            db.close();
            cursor.close();
        } catch (Exception e) {
            Log.e("MYDB", "Error al listar UN Activo - verUnoXcodigo");
        }
        return activos;
    }


    public ArrayList<Activos> verActivosXfiltro2(String edi, String fun) {
        Cursor cursor;
        try{
            SQLiteDatabase db=conexion.getReadableDatabase();
            lista.clear();
            cursor= db.rawQuery("SELECT Activos.* " +
                    "from custodias , funcionarios " +
                    "where custodias.custodioid=funcionarios.id " +
                    "and custodias.edificioid="+edi.trim()+"custodioid="+fun.trim(),null);
            if(cursor!=null && cursor.getCount()>0){
                cursor.moveToFirst();
                do{
                    lista.add(new Activos(cursor.getString(1),cursor.getInt(2),
                            cursor.getString(3),cursor.getString(4)));/*,
                                cursor.getString(4),cursor.getString(5),
                                cursor.getFloat(6),cursor.getFloat(7),
                                cursor.getInt(8),cursor.getString(9),
                                cursor.getInt(10),cursor.getString(11),
                                cursor.getString(12),cursor.getInt(13),
                                cursor.getString(14),cursor.getString(15),
                                cursor.getString(16),cursor.getString(17),
                                cursor.getString(18),cursor.getString(19),
                                cursor.getString(20),cursor.getString(21),
                                cursor.getInt(22),cursor.getString(23),
                                cursor.getString(24)));*/

                }while(cursor.moveToNext());
            }
            db.close();
            cursor.close();
        } catch (Exception e) {
            Log.e("MYDB", "Error al listar Activos ");
        }
        return lista;
    }
}

