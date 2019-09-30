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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ale.misactivos.Adapter.AdaptadorOficina;
import com.example.ale.misactivos.Adapter.AdaptadorEdificio;
import com.example.ale.misactivos.Model.DaoEdificios;
import com.example.ale.misactivos.Model.DaoOficina;
import com.example.ale.misactivos.Model.Validar;
import com.example.ale.misactivos.Operaciones.ConexionSqliteHelper;
import com.example.ale.misactivos.R;
import com.example.ale.misactivos.entidades.Edificios;
import com.example.ale.misactivos.entidades.Oficinas;


import java.util.ArrayList;

public class CrudOficinaActivity extends AppCompatActivity {
    String nombreDB = "DBActivos";
    DaoOficina dao;
    DaoEdificios daoed;
    AdaptadorOficina adapter;
    AdaptadorEdificio adaptered;
    ArrayList<Oficinas> lista = new ArrayList<>();
    ArrayList<String> listaed = new ArrayList<>();
    ListView list;
    Edificios e;
    Oficinas of;
    Button agregar, guardar, cancelar;
    EditText nombredit;
    Spinner spIdEdificio;
    TextView tituloform, nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_oficina);

        dao = new DaoOficina(this);
        //daoed =new DaoEdificios(this);

        lista = dao.verTodos();
        adapter = new AdaptadorOficina(lista, dao, this);
        //adaptered=new AdaptadorEdificio(listaed,daoed,this);
        list = findViewById(R.id.listOf);
        Log.i("Datos:", String.valueOf(lista.size()));
        agregar = findViewById(R.id.btnAdd);

        list.setAdapter(adapter);
       /* list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // para hacer llamada desde la lista

            }
        });*/
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dialogo de agregar
                Context context;
                listaed = cargardatosedificio();
                final AlertDialog.Builder dialog= new AlertDialog.Builder(CrudOficinaActivity.this);
                final View sView = getLayoutInflater().inflate(R.layout.dialogo_oficina,null);
                dialog.setTitle("Nuevo reegistro Oficina");

                //enlazamos variable nombredit con edit text del dialogo
                nombredit = sView.findViewById(R.id.etNombre_d);

                //enlazamos variable spIdEdificio con spinner del dialogo
                spIdEdificio = sView.findViewById(R.id.spIdedif_d);

                ArrayAdapter<String> adapteredif = new ArrayAdapter<String>(CrudOficinaActivity.this,
                                                        android.R.layout.simple_spinner_item, listaed);
                adapteredif.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spIdEdificio.setAdapter(adapteredif);


                tituloform = sView.findViewById(R.id.tvTitulo_d);
                guardar = sView.findViewById(R.id.d_agregar);
                cancelar = sView.findViewById(R.id.d_cancelar);
                tituloform.setText("NUEVO REGISTRO DE DPTO");
                guardar.setText("Agregar");


                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            if (Validar.ValidaTexto(nombredit.getText().toString().trim())) {
                                of= new Oficinas(nombredit.getText().toString(), ( int ) spIdEdificio.getSelectedItemId());
                                dao.insertar(of);
                                lista = dao.verTodos();
                                adapter.notifyDataSetChanged();
                            } else {
                                Toast.makeText(getApplication(), "Verifique los datos", Toast.LENGTH_SHORT).show();
                            }

                            //mdialog.dismiss();
                        } catch (Exception e) {
                            Toast.makeText(getApplication(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //dialog.dismiss;
                        //mdialog.dismiss();



                    }
                });
                //Toast.makeText(CrudOficinaActivity.this, "Añadiendo registro: " + nombredit.getText(), Toast.LENGTH_SHORT).show();
                dialog.setView(sView);
                AlertDialog dialog1 = dialog.create();
                dialog1.show();
            }
        });

    }

    private ArrayList<String> cargardatosedificio() {
        ArrayList<String> lista = new ArrayList<String>();
        //e = new Edificios();
        ConexionSqliteHelper conex = new ConexionSqliteHelper(getApplicationContext(), nombreDB, null, 1);
        SQLiteDatabase db = conex.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("Select nombreedificio from edificios", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();

                lista.add("Seleccione Edificio...");
                do {

                   lista.add(cursor.getString(0));
                    //Log.i("MYDB",""+e.getId());
                    Log.i("MYDB",""+cursor.getString(0));

                } while (cursor.moveToNext());
            }else{
                Toast.makeText(this, "Realizar registro edificios ",Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
