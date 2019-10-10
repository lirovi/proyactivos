package com.example.ale.misactivos.Vistas;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ale.misactivos.Adapter.AdaptadorCargo;
import com.example.ale.misactivos.Adapter.AdaptadorOficina;
import com.example.ale.misactivos.Adapter.AdaptadorEdificio;
import com.example.ale.misactivos.Model.DaoCargos;
import com.example.ale.misactivos.Model.DaoEdificios;
import com.example.ale.misactivos.Model.DaoOficina;
import com.example.ale.misactivos.Model.Validar;
import com.example.ale.misactivos.Operaciones.ConexionSqliteHelper;
import com.example.ale.misactivos.R;
import com.example.ale.misactivos.entidades.Cargos;
import com.example.ale.misactivos.entidades.Edificios;
import com.example.ale.misactivos.entidades.Oficinas;


import java.util.ArrayList;

import fr.ganfra.materialspinner.MaterialSpinner;

public class CrudOficinaActivity extends AppCompatActivity {
    DaoOficina dao;
    DaoEdificios daoEdificios;

    AdaptadorOficina adapter;
    ArrayList<Oficinas> lista = new ArrayList<>();

    ListView list;
    Oficinas o;
    Spinner spIdEdificio;

    ArrayList<String> listItemEdificios = new ArrayList<>();
    ArrayAdapter<String> adapterEdificios;

    Button agregar, guardar, cancelar;
    EditText nombredit;
    TextView tituloform;
    String codedificioedit = "-";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_oficina);

        dao = new DaoOficina(this);
        daoEdificios = new DaoEdificios(this);
        //lista=new ArrayList();
        lista = dao.verTodos();
        adapter = new AdaptadorOficina(lista, dao, this);
        list = findViewById(R.id.listOficina);
        agregar = findViewById(R.id.btnAdd);

        listItemEdificios.add("Seleccione");
        CargarSpiEdificios();

       // adapterEdificios = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listItemEdificios);
        //adapterEdificios.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // para hacer llamada desde la lista

            }
        });


        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context;
                AlertDialog.Builder mBuilder= new AlertDialog.Builder(CrudOficinaActivity.this);
                View mView=getLayoutInflater().inflate(R.layout.dialogo_oficina,null);
                //mBuilder.setTitle("Nuevo registro");
                nombredit=mView.findViewById(R.id.etNombreOficina_d);
                spIdEdificio = mView.findViewById(R.id.spIdEdificio_d);
                tituloform=mView.findViewById(R.id.tvTitulo_d);

                adapterEdificios= new ArrayAdapter<String>(CrudOficinaActivity.this,android.R.layout.simple_list_item_1, listItemEdificios);
                adapterEdificios.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spIdEdificio.setAdapter(adapterEdificios);

                mBuilder.setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(!spIdEdificio.getSelectedItem().toString().equalsIgnoreCase("Seleccione")){
                            String selected[] =  (spIdEdificio.getSelectedItem().toString().split("-"));
                            codedificioedit=selected[1];
                            Toast.makeText(getApplicationContext(), "Datos Selecionado: "+selected[1]+"-"+nombredit.getText(), Toast.LENGTH_SHORT).show();
                            try{
                                if(Validar.ValidaTexto(nombredit.getText().toString().trim())) {
                                    o = new Oficinas(nombredit.getText().toString(),codedificioedit);
                                    dao.insertar(o);
                                    lista = dao.verTodos();
                                    adapter.notifyDataSetChanged();
                                }else{
                                    Toast.makeText(getApplication(),"Verifique los datos",Toast.LENGTH_SHORT).show();
                                }
                                dialog.dismiss();
                            }catch (Exception e){
                                Toast.makeText(getApplication(),"Error",Toast.LENGTH_SHORT).show();
                            }
                            dialog.dismiss();
                        }
                    }
                });

                mBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog=mBuilder.create();
                dialog.show();

            }
        });

    }
        private void CargarSpiEdificios () {
            ArrayList<Edificios> edi = daoEdificios.verTodos();

            if (edi.size() > 0) {
                for (int i = 0; i < edi.size(); i++) {
                    //Log.i("MyDB",edi.get(i).getCodigo()+"-"+edi.get(i).getNombreedificio()+"-"+edi.get(i).getEstado());
                    listItemEdificios.add(edi.get(i).getId() + "-" + edi.get(i).getCodigo() + "-" + edi.get(i).getNombreedificio());
                }
            } else {
                Toast.makeText(this, "No existen datos de edificio", Toast.LENGTH_LONG).show();
            }
        }
    }

