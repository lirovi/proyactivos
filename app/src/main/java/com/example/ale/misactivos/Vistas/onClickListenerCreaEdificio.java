package com.example.ale.misactivos.Vistas;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;

import com.example.ale.misactivos.Operaciones.ControladorEdificios;
import com.example.ale.misactivos.R;
import com.example.ale.misactivos.entidades.Edificios;

public class onClickListenerCreaEdificio implements View.OnClickListener {


    @Override
    public void onClick(View v) {
        final Context context= v.getContext();

       showDialogCrearEdificio("EDIFICIOS", "Registro de Edificio",context);
    }

    private void showDialogCrearEdificio(String title, String mensaje,final Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.Theme_MaterialComponents_NoActionBar_Bridge);
        if (title!=null) builder.setTitle(title);
        if (mensaje!=null) builder.setMessage(mensaje);

       View vistaformEdificios = LayoutInflater.from(context).inflate(R.layout.layout_crear_edificio, null);

        final EditText nombredificio = vistaformEdificios.findViewById(R.id.etNombreEdifico);
        builder.setView(vistaformEdificios);

        builder.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name= nombredificio.getText().toString().trim();
                if(name.length()>0)
                {
                   CrearEdificio(name, context);
                }else{
                    Toast.makeText(context,"El dato es requerido",Toast.LENGTH_SHORT).show();
                }
            }
        });
        AlertDialog dialog =  builder.create();
        dialog.show();
        //
    }

    private void CrearEdificio(String name, Context context) {

        Edificios edificio= new Edificios();
        edificio.setNombreedificio(name);

        boolean edificioCreado = new ControladorEdificios(context).crear(edificio);
        if(edificioCreado){
            Toast.makeText(context,"El edificio se ha creado correctamente",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"El edificio NO se ha creado",Toast.LENGTH_SHORT).show();
        }
    }


}
