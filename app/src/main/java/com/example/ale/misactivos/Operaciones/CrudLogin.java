package com.example.ale.misactivos.Operaciones;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ale.misactivos.LoginActivity;

public class CrudLogin extends ConexionSqliteHelper{

   EditText etNom, etApe, etUsu, etPas;

    public CrudLogin(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }





  /* public void abrirdb(){
       SQLiteDatabase db= this.getWritableDatabase();
    }
    public void leerdb(){
        SQLiteDatabase db= this.getReadableDatabase();
    }
    public void cerrardb(){
        SQLiteDatabase db= this.getWritableDatabase()();
        db.close();
    }

   public void registrarusuario(View view){
      abrirdb();

        String nom=etNom.getText().toString();
        String ape=etApe.getText().toString();
        String usu=etUsu.getText().toString();
        String pas=etPas.getText().toString();+
        5+

        if(!nom.isEmpty()&& !ape.isEmpty() && !usu.isEmpty() && !pas.isEmpty()){
            insertarReg(nom,ape,usu,pas);

            etNom.setText("");
            etApe.setText("");
            etUsu.setText("");
            etPas.setText("");

            Toast.makeText(this,"El registro fue exitoso", Toast.LENGTH_SHORT);

        }else {
            Toast.makeText(this, "Complete los datos faltantes", Toast.LENGTH_LONG).show();
        }
        cerrardb();
    }

    public void buscar(View view){
       // ConexionSqliteHelper admin= new ConexionSqliteHelper(this,"DBActivos",null,1);
        SQLiteDatabase db= this.getWritableDatabase();

        String usu=etUsu.getText().toString();

        if(!usu.isEmpty()){
            Cursor fila=db.rawQuery("Select Nombre, Apellido from usuarios Where Usuario="+usu, null);

            if(fila.moveToFirst()){
                etNom.setText(fila.getString(0));
                etApe.setText(fila.getString(1));
                etPas.setText(fila.getString(3));

            }else{
                Toast.makeText(getApplicationContext(), "No existe usuario", Toast.LENGTH_LONG).show();

            }

        }else {
            Toast.makeText(getApplicationContext(), "Complete los datos faltantes", Toast.LENGTH_LONG).show();
        }
        cerrardb();
    }

    public void eliminar(View view){
      //  ConexionSqliteHelper admin= new ConexionSqliteHelper(this,"DBActivos",null,1);
        SQLiteDatabase db1= this.getWritableDatabase();

        String usu=etUsu.getText().toString();



        if(!usu.isEmpty()){

            int cantidad=db1.delete("usuarios","usuario="+usu,null);

            etNom.setText("");
            etApe.setText("");
            etUsu.setText("");
            etPas.setText("");
            if(cantidad==1){
                Toast.makeText(getApplicationContext(), "Registro eliminado exitosamente", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(), "No se pudo eliminar el registro", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(getApplicationContext(), "Debe introducir un usuario", Toast.LENGTH_LONG).show();
        }
        db1.close();
    }

    public void Actualizar(View view){
       // ConexionSqliteHelper admin= new ConexionSqliteHelper(this,"DBActivos",null,1);
        SQLiteDatabase db1= this.getWritableDatabase();

        String usu=etUsu.getText().toString();

        if(!usu.isEmpty()){
            Cursor fila=db1.rawQuery("Select Nombre, Apellido from usuarios Where Usuario="+usu, null);

            if(fila.moveToFirst()){
                etNom.setText(fila.getString(0));
                etApe.setText(fila.getString(1));
                etPas.setText(fila.getString(3));

                String nom=etNom.getText().toString();
                String ape=etApe.getText().toString();
                usu=etUsu.getText().toString();
                String pas=etPas.getText().toString();

                ContentValues reg= new ContentValues();
                reg.put("Nombre",nom);
                reg.put("Apellido",ape);
                reg.put("Usuario",usu);
                reg.put("Password",pas);
                int cantidad=db1.update("usuarios",reg,"usuario="+usu,null);


                if(cantidad==1){
                    Toast.makeText(getApplicationContext(), "Registro modificado correctamente", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "No se pudo modifcar el registro", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(getApplicationContext(), "No existe usuario", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(getApplicationContext(), "Complete los datos faltantes", Toast.LENGTH_LONG).show();
        }
        db1.close();
    }/*
    private void insertarReg(String nom, String ape, String usu, String pas){
       // ConexionSqliteHelper admin= new ConexionSqliteHelper(this,"DBActivos",null,1);
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues reg= new ContentValues();
        reg.put("Nombre",nom);
        reg.put("Apellido",ape);
        reg.put("Usuario",usu);
        reg.put("Password",pas);
        db.insert("usuarios",null,reg);
        db.close();
    }
*/

}
