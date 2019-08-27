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

public class onClicEditaEdificio implements View.OnClickListener {

    EditText nombredificio;
    AlertDialog.Builder builder;
    View vistaformEdificios;
    Edificios edificio;
    @Override
    public void onClick(View v) {
        final Context context= v.getContext();

        showDialogEditaEdificio("EDIFICIOS", "Registro de Edificio",context);
    }

    private void showDialogEditaEdificio(String title, String mensaje, final Context context) {

        builder = new AlertDialog.Builder(context, R.style.Theme_MaterialComponents_NoActionBar_Bridge);
        if (title!=null) builder.setTitle(title);
        if (mensaje!=null) builder.setMessage(mensaje);

        vistaformEdificios = LayoutInflater.from(context).inflate(R.layout.layout_crear_edificio, null);

        nombredificio = vistaformEdificios.findViewById(R.id.etNombreEdifico);
        edificio = new ControladorEdificios(context).leerEdificio(vistaformEdificios.getId());
        nombredificio.setText(edificio.getNombreedificio());
        builder.setView(vistaformEdificios);
        Toast.makeText(context,"Los datos son :"+edificio.getNombreedificio(),Toast.LENGTH_SHORT).show();
        builder.setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int datosid) {



            if (edificio.getNombreedificio()!="") {


            }else{
                Toast.makeText(context,"Los datos no se pueden recuperar",Toast.LENGTH_SHORT).show();
            }
        }
    });
    AlertDialog dialog =  builder.create();
        dialog.show();
    //
}

    private void EditarEdificio(String name, Context context) {

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
