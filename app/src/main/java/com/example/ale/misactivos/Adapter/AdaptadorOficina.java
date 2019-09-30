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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ale.misactivos.Model.DaoOficina;
import com.example.ale.misactivos.Model.Validar;
import com.example.ale.misactivos.R;
import com.example.ale.misactivos.entidades.Departamentos;
import com.example.ale.misactivos.entidades.Edificios;
import com.example.ale.misactivos.entidades.Oficinas;

import java.util.ArrayList;

public class AdaptadorOficina extends BaseAdapter {
    ArrayList<Oficinas> lista;
    DaoOficina dao;
    Oficinas of;
    Activity a;
    TextView nombre,id_dpto,tituloform,idEdificio;
    EditText nombredit, id_edificio;
    Button guardar,cancelar;
    Spinner id_edificioedit;
    int id=0;


    public AdaptadorOficina(ArrayList<Oficinas> lista, DaoOficina dao, Activity a) {
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
    public Oficinas getItem(int position) {
        of=lista.get(position);
        return of;
    }

    @Override
    public long getItemId(int position) {
        of=lista.get(position);
        return of.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater li = ( LayoutInflater ) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.item_oficina, null);
        }

        of = lista.get(position);
        id_dpto = v.findViewById(R.id.tvIdOficinaItem);
        nombre = v.findViewById(R.id.tvNomOfinaItem);
        idEdificio = v.findViewById(R.id.tvIdEdificioItem);
        Button editar = v.findViewById(R.id.btnEdit);
        Button eliminar = v.findViewById(R.id.btnDel);
        id_dpto.setText(""+of.getId());
        //Log.i("Datos:",String.valueOf(d.getId()));
        nombre.setText(of.getNombreoficina());
       // Log.i("Datos:",of.getNombreoficina());
       idEdificio.setText(""+of.getOficinaid());

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
                dialog.setContentView(R.layout.dialogo_oficina);
                dialog.show();
                nombredit= dialog.findViewById(R.id.etNombre_d);
                id_edificioedit= dialog.findViewById(R.id.spIdedif_d);
                tituloform=dialog.findViewById(R.id.tvTitulo_d);
                guardar =  dialog.findViewById(R.id.d_agregar);
                cancelar = dialog.findViewById(R.id.d_cancelar);
                //ubicar el elemento seleccioando de la lista
                tituloform.setText("EDICION DE REGISTRO");
                guardar.setText("Guardar");
                of=lista.get(pos);
                setId(of.getId());

                //recuperar los datos de la lista y pasar a los edittext del formulario dialogo
                nombredit.setText(of.getNombreoficina());
                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            if(Validar.ValidaTexto(nombredit.getText().toString().trim())) {   //funcion para validar tipos de datos: ValidaTexto, ValidaNumero, ValidaEmail,ValidaFecha, ValidaDireccion, ValidaTelefono
                                of = new Oficinas(getId(),nombredit.getText().toString().trim(),(int)id_edificioedit.getSelectedItem());
                                dao.editar(of);
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
                Toast.makeText(a,"Editando registro: "+nombredit.getText(),Toast.LENGTH_SHORT).show();
            }
        });


        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Dialogo para confirmar
                int pos=Integer.parseInt(v.getTag().toString());
                of=lista.get(pos);
                setId(of.getId());
                AlertDialog.Builder del=new AlertDialog.Builder(a);
                del.setMessage("Eliminar registro?:"+of.getNombreoficina());
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
