package com.example.ale.misactivos.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ale.misactivos.Model.DaoProfesiones;
import com.example.ale.misactivos.Model.Validar;
import com.example.ale.misactivos.R;
import com.example.ale.misactivos.entidades.Profesiones;

import java.util.ArrayList;

public class AdaptadorProf extends BaseAdapter {
    ArrayList<Profesiones> lista;
    DaoProfesiones dao;
    Profesiones p;
    Activity a;
    TextView nombre,id_Profesiones,tituloform;
    EditText nombredit;
    Button guardar,cancelar;
    int id=0;


    public AdaptadorProf(ArrayList<Profesiones> lista, DaoProfesiones dao, Activity a) {
        this.lista = lista;
        this.dao = dao;
        this.a = a;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Profesiones getItem(int position) {
        p=lista.get(position);
        return p;
    }

    @Override
    public long getItemId(int position) {
        p=lista.get(position);
        return p.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater li = ( LayoutInflater ) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.item_profesiones, null);
        }

        p = lista.get(position);
        id_Profesiones = v.findViewById(R.id.tvIdProfItem);
        nombre = v.findViewById(R.id.tvNomProfItem);
        Button editar = v.findViewById(R.id.btnEdit);
        Button eliminar = v.findViewById(R.id.btnDel);
        id_Profesiones.setText(""+p.getId());
        nombre.setText(p.getNombreprof());

        editar.setTag(position);
        eliminar.setTag(position);

        // Accion boton editar
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos=Integer.parseInt(v.getTag().toString());
                Context context;
                final Dialog dialog= new Dialog(a);
                dialog.setTitle("Editar registro");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialogo);
                dialog.show();
                nombredit= dialog.findViewById(R.id.etNombre_d);
                tituloform=dialog.findViewById(R.id.tvTitulo_d);
                guardar =  dialog.findViewById(R.id.d_agregar);
                cancelar = dialog.findViewById(R.id.d_cancelar);
                //ubicar el elemento seleccioando de la lista
                tituloform.setText("EDICION DE REGISTRO");
                guardar.setText("Guardar");
                p=lista.get(pos);
                setId(p.getId());
                //recuperar los datos de la lista y pasar a los edittext del formulario dialogo
                nombredit.setText(p.getNombreprof());
                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            if(Validar.ValidaTexto(nombredit.getText().toString().trim())) {   //funcion para validar tipos de datos: ValidaTexto, ValidaNumero, ValidaEmail,ValidaFecha, ValidaDireccion, ValidaTelefono
                                p = new Profesiones(getId(), nombredit.getText().toString().trim());
                                dao.editar(p);
                                lista = dao.verTodos();
                                notifyDataSetChanged();
                            }else{
                                Toast.makeText(a,"Verifique los datos",Toast.LENGTH_SHORT).show();
                            }
                            dialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(a,"Error",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                Toast.makeText(a,"Editando registro: "+nombre.getText(),Toast.LENGTH_SHORT).show();
            }
        });


        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Dialogo para confirmar
                int pos=Integer.parseInt(v.getTag().toString());
                p=lista.get(pos);
                setId(p.getId());
                AlertDialog.Builder del=new AlertDialog.Builder(a);
                del.setMessage("Eliminar registro?:"+p.getNombreprof());
                del.setCancelable(false);
                del.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dao.eliminar(getId());
                        lista=dao.verTodos();
                        notifyDataSetChanged();
                    }
                });
                del.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                del.show();
            }
        });
        return v;
    }
}
