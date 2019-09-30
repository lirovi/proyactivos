package com.example.ale.misactivos.Vistas;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ale.misactivos.Adapter.AdaptadorProf;
import com.example.ale.misactivos.Model.DaoProfesiones;
import com.example.ale.misactivos.Model.Validar;
import com.example.ale.misactivos.R;
import com.example.ale.misactivos.entidades.Profesiones;

import java.util.ArrayList;

public class CrudProfesionesActivity extends AppCompatActivity {
    DaoProfesiones dao;
    AdaptadorProf adapter;
    ArrayList<Profesiones> lista=new ArrayList<>();
    ListView list;
    Profesiones p;
    Button agregar,guardar,cancelar;
    EditText nombredit;
    TextView tituloform, nombre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_profesiones);

        dao = new DaoProfesiones( this);
        //lista=new ArrayList();
        lista=dao.verTodos();
        adapter=new AdaptadorProf(lista,dao,this);
        list= findViewById(R.id.listProfesiones);
        agregar=findViewById(R.id.btnAdd);

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
                //dialogo de agregar
                Context context;
                final Dialog dialog= new Dialog(CrudProfesionesActivity.this);
                dialog.setTitle("Nuevo registro");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialogo);
                dialog.show();
                nombredit= dialog.findViewById(R.id.etNombre_d);
                tituloform=dialog.findViewById(R.id.tvTitulo_d);
                guardar =  dialog.findViewById(R.id.d_agregar);
                cancelar = dialog.findViewById(R.id.d_cancelar);
                tituloform.setText("NUEVO REGISTRO DE PROFESION");
                guardar.setText("Agregar");
                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            if(Validar.ValidaTexto(nombredit.getText().toString().trim())) {
                                p = new Profesiones(nombredit.getText().toString());
                                dao.insertar(p);
                                lista = dao.verTodos();
                                adapter.notifyDataSetChanged();
                            }else{
                                Toast.makeText(getApplication(),"Verifique los datos",Toast.LENGTH_SHORT).show();
                            }
                            dialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(getApplication(),"Error",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                Toast.makeText(CrudProfesionesActivity.this,"Añadiendo registro: "+nombredit.getText(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}
