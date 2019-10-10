package com.example.ale.misactivos.Operaciones;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.ale.misactivos.R;
import com.example.ale.misactivos.entidades.Usuarios;

import static com.example.ale.misactivos.Operaciones.CreaTablas.NOMBREDB;

public class OnClickCrearLogin implements View.OnClickListener {

    //Usuarios usuario=new Usuarios();
    private TextInputEditText nom,ape,usu,pas,pas2;
    private String snom,sape,susu,spas,spas2;

        @Override
    public void onClick(View v) {
        final Context context= v.getContext();

        LayoutInflater inflater= ( LayoutInflater ) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formularioCrearCuenta = inflater.inflate(R.layout.activity_create_account,null,false);


           /* usuario.setNombre(formularioCrearCuenta.findViewById(R.id.nombre).toString());
            usuario.setApellido(formularioCrearCuenta.findViewById(R.id.apellido).toString());
            usuario.setUsuario(formularioCrearCuenta.findViewById(R.id.user).toString());
            usuario.setPassword(formularioCrearCuenta.findViewById(R.id.password_create_account).toString());
            Toast.makeText(context,"El registro esta en progreso", Toast.LENGTH_SHORT).show();
            String nom=usuario.getNombre();
            String ape=usuario.getApellido();
            String usu=usuario.getUsuario();
            String pas=usuario.getPassword();*/
             nom = (TextInputEditText) formularioCrearCuenta.findViewWithTag(R.id.nombre);
             ape = (TextInputEditText)formularioCrearCuenta.findViewById(R.id.apellido);
             usu = (TextInputEditText)formularioCrearCuenta.findViewById(R.id.user);
             pas = (TextInputEditText)formularioCrearCuenta.findViewById(R.id.password_create_account);
             pas2 = (TextInputEditText)formularioCrearCuenta.findViewById(R.id.confirm_password);


             snom= nom.getText().toString();
             sape= ape.getText().toString();
             susu= usu.getText().toString();
             spas= pas.getText().toString();
             spas2= pas2.getText().toString();

            Toast.makeText(context,"NOMBRE: "+snom +" APELLIDO:"+ape +"Las contraseñas:"+pas +" y :"+pas2+" son diferentes", Toast.LENGTH_LONG).show();

            if(!snom.isEmpty()&& !sape.isEmpty() && !susu.isEmpty() && !spas.isEmpty() && !spas2.isEmpty()){
                if(pas.equals(pas2)) {
                    insertarReg(snom, sape, susu, spas,context);
                    Toast.makeText(context,"El registro fue exitoso", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context,"NOMBRE: "+snom +" APELLIDO:"+sape +"Las contraseñas:"+spas +" y :"+spas2+" son diferentes", Toast.LENGTH_SHORT).show();
                }

            }else {
                //Toast.makeText(context, "Complete los datos faltantes", Toast.LENGTH_LONG).show();
            }
    }

    private void insertarReg(String nom, String ape, String usu, String pas,Context context){
        ConexionSqliteHelper admin= new ConexionSqliteHelper(context,NOMBREDB,null,1);
        SQLiteDatabase db=admin.getWritableDatabase();
        ContentValues reg= new ContentValues();
        reg.put("Nombre",nom);
        reg.put("Apellido",ape);
        reg.put("Usuario",usu);
        reg.put("Password",pas);
        db.insert("usuarios",null,reg);
        db.close();
    }

}
