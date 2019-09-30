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
import com.example.ale.misactivos.Adapter.AdaptadorTipoDocumento;
import com.example.ale.misactivos.Model.DaoProfesiones;
import com.example.ale.misactivos.Model.DaoTipoDocumento;
import com.example.ale.misactivos.Model.Validar;
import com.example.ale.misactivos.R;
import com.example.ale.misactivos.entidades.Profesiones;
import com.example.ale.misactivos.entidades.Tipodocumentos;

import java.util.ArrayList;

public class CrudTipoDocumentoActivity extends AppCompatActivity {
    DaoTipoDocumento dao;
    AdaptadorTipoDocumento adapter;
    ArrayList<Tipodocumentos> lista=new ArrayList<>();
    ListView list;
    Tipodocumentos td;
    Button agregar,guardar,cancelar;
    EditText nombredit, siglaedit;
    TextView tituloform, nombre, sigla;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tipo_documento);

        dao = new DaoTipoDocumento( this);
        //lista=new ArrayList();
        lista=dao.verTodos();
        adapter=new AdaptadorTipoDocumento(lista,dao,this);
        list= findViewById(R.id.listTipoDocumentos);
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
                final Dialog dialog= new Dialog(CrudTipoDocumentoActivity.this);
                //dialog.setTitle("Nuevo registro");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialogo_tipo_documento);
                dialog.show();
                nombredit= dialog.findViewById(R.id.etNombre_d);
                siglaedit= dialog.findViewById(R.id.etSigla_d);
               // tituloform=dialog.findViewById(R.id.tvTitulo_d);
                guardar =  dialog.findViewById(R.id.d_agregar);
                cancelar = dialog.findViewById(R.id.d_cancelar);
                //tituloform.setText("NUEVO REGISTRO DE PROFESION");
                guardar.setText("Agregar");
                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            if((Validar.ValidaTexto(nombredit.getText().toString().trim()) && !siglaedit.getText().toString().isEmpty())) {
                                td = new Tipodocumentos(nombredit.getText().toString(),siglaedit.getText().toString());
                                dao.insertar(td);
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
                //Toast.makeText(CrudTipoDocumentoActivity.this,"Añadiendo registro: "+nombredit.getText(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}
