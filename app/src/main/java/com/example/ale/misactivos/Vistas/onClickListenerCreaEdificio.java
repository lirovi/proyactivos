package com.example.ale.misactivos.Vistas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ale.misactivos.Operaciones.ControladorEdificios;
import com.example.ale.misactivos.R;
import com.example.ale.misactivos.entidades.Edificios;

public class onClickListenerCreaEdificio implements View.OnClickListener {


    @Override
    public void onClick(View v) {
        final Context context= v.getContext();
        LayoutInflater inflater= ( LayoutInflater ) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup root;
        final View vistaformEdificios = inflater.inflate(R.layout.layout_crear_edificio, null, false);

        final EditText nombredificio = vistaformEdificios.findViewById(R.id.etNombreEdifico);

        new AlertDialog.Builder(context)
                .setView(vistaformEdificios)
                .setTitle("Crear Edificios")
                .setPositiveButton("Crear", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nomedificio= nombredificio.getText().toString();

                        Edificios edificio= new Edificios();
                        edificio.setNombreedificio(nomedificio);

                        boolean edificioCreado = new ControladorEdificios(context).crear(edificio);
                        if(edificioCreado){
                            Toast.makeText(context,"El edificio se ha creado correctamente",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context,"El edificio NO se ha creado",Toast.LENGTH_SHORT).show();
                        }
                        ((EdificiosActivity) context).contadorregistros();
                    }
                }).show();
    }
}
