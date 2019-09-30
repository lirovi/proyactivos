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

import com.example.ale.misactivos.Model.DaoFuncionario;
import com.example.ale.misactivos.Model.DaoOficina;
import com.example.ale.misactivos.Model.Validar;
import com.example.ale.misactivos.R;
import com.example.ale.misactivos.entidades.Funcionarios;
import com.example.ale.misactivos.entidades.Oficinas;


import java.util.ArrayList;

public class AdaptadorFuncionario extends BaseAdapter {
    ArrayList<Funcionarios> lista;
    DaoFuncionario daof;
    Funcionarios fu;
    Activity a;
    EditText nombredit,apeuedit,apededit,diredit,teledit,cargoedit,profedit,nrodocedit,nacionaedit;
    Spinner spIdTipodoc,sexoedit;
    TextView tituloform;
    TextView nombre,apeu,aped,direc,telef,cargoid,profesionid,nrodoc,nac,sexo,id_func,idTipoDoc;

    Button guardar,cancelar;
    Spinner spineer_id_tipodocedit;
    int id=0;

    public AdaptadorFuncionario(ArrayList<Funcionarios> lista, DaoFuncionario dao, Activity a) {
        this.lista = lista;
        this.daof = dao;
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
    public Funcionarios getItem(int position) {
        fu=lista.get(position);
        return fu;
    }

    @Override
    public long getItemId(int position) {
        fu=lista.get(position);
        return fu.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater li = ( LayoutInflater ) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.item_funcionario, null);
        }

        fu = lista.get(position);
        id_func = v.findViewById(R.id.tvIdFuncItem);
        nombre = v.findViewById(R.id.tvNomFuncItem);
        apeu = v.findViewById(R.id.tvNomFuncItem);
        aped = v.findViewById(R.id.tvNomFuncItem);
        direc = v.findViewById(R.id.tvNomFuncItem);
        telef = v.findViewById(R.id.tvNomFuncItem);
        cargoid = v.findViewById(R.id.tvNomFuncItem);
        profesionid = v.findViewById(R.id.tvNomFuncItem);
        idTipoDoc = v.findViewById(R.id.tvIdTipoDocItem);
        nrodoc = v.findViewById(R.id.tvNomFuncItem);
        nac = v.findViewById(R.id.tvNomFuncItem);
        sexo = v.findViewById(R.id.tvNomFuncItem);

        Button editar = v.findViewById(R.id.btnEdit);
        Button eliminar = v.findViewById(R.id.btnDel);
        id_func.setText(""+fu.getId());
        //Log.i("Datos:",String.valueOf(d.getId()));
        nombre.setText(fu.getNombre());
        apeu.setText(fu.getApellidou());
        // Log.i("Datos:",of.getNombreoficina());
       // idTipoDoc.setText(""+td.get());

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
                dialog.setContentView(R.layout.dialogo_funcionario);
                dialog.show();
                nombredit = dialog.findViewById(R.id.etNombre_d);
                apeuedit  = dialog.findViewById(R.id.etApellidou_d);
                apededit = dialog.findViewById(R.id.etApellidod_d);
                diredit = dialog.findViewById(R.id.etDireccion_d);
                cargoedit = dialog.findViewById(R.id.etCargo_d);
                profedit = dialog.findViewById(R.id.etProfesion_d);
                spIdTipodoc = dialog.findViewById(R.id.spinnerTipoDoc);
                nrodocedit = dialog.findViewById(R.id.etNroDoc_d);
                nacionaedit = dialog.findViewById(R.id.etNacionalidad_d);
                sexoedit = dialog.findViewById(R.id.spinnerSexo);
                teledit= dialog.findViewById(R.id.etTelefono_d);
                tituloform=dialog.findViewById(R.id.tvTitulo_d);
                guardar =  dialog.findViewById(R.id.d_agregar);
                cancelar = dialog.findViewById(R.id.d_cancelar);
                //ubicar el elemento seleccioando de la lista
                tituloform.setText("EDICION DE REGISTRO");
                guardar.setText("Guardar");
                fu=lista.get(pos);
                setId(fu.getId());
                apeuedit.setText(fu.getApellidou());
                apededit.setText(fu.getApellidod());
                diredit.setText(fu.getDireccion());
                teledit.setText(fu.getTelefono());
                cargoedit.setText(fu.getCargoid());
                profedit.setText(fu.getProfesionid());
                nrodocedit.setText(fu.getNrodoc());
                nacionaedit.setText(fu.getNacionalidad());
                sexoedit.setSelection(1);



                //recuperar los datos de la lista y pasar a los edittext del formulario dialogo
                nombredit.setText(fu.getNombre());
                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            boolean b_nombre= Validar.ValidaTexto(nombredit.getText().toString().trim());
                            boolean b_apeuedit = Validar.ValidaTexto(nombredit.getText().toString().trim());
                            String s_nombre = nombredit.getText().toString().trim();
                            String s_apeu = apeuedit.getText().toString().trim();

                            if(b_nombre) {   //funcion para validar tipos de datos: ValidaTexto, ValidaNumero, ValidaEmail,ValidaFecha, ValidaDireccion, ValidaTelefono
                                fu = new Funcionarios(getId(),s_nombre,s_apeu);
                                daof.editar(fu);
                                lista = daof.verTodos();
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
                fu=lista.get(pos);
                setId(fu.getId());
                AlertDialog.Builder del=new AlertDialog.Builder(a);
                del.setMessage("Eliminar registro?:"+fu.getNombre()+" "+fu.getApellidod());
                del.setCancelable(false);
                del.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        daof.eliminar(getId());
                        lista=daof.verTodos();
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
