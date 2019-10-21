package com.example.ale.misactivos.Vistas;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ale.misactivos.Adapter.ReciclerViewAdaptador;
import com.example.ale.misactivos.Model.DaoActivos;
import com.example.ale.misactivos.Operaciones.ConexionSqliteHelper;
import com.example.ale.misactivos.Operaciones.SimpleScannerActivity;
import com.example.ale.misactivos.R;
import com.example.ale.misactivos.entidades.Activos;
import com.example.ale.misactivos.entidades.Edificios;

import java.util.ArrayList;
import java.util.List;

public class CrudActivosActivity extends AppCompatActivity {
    private RecyclerView recyclerViewActivos;
    private ReciclerViewAdaptador adaptadorActivos;
    DaoActivos daoActivos;
    private EditText etBuscar;
    Button btLeerCodBarra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(
                R.layout.layout_activos);

        daoActivos= new DaoActivos(this);
        etBuscar =  findViewById(R.id.etBuscar);
        recyclerViewActivos = findViewById(R.id.reciclerActivos);
        btLeerCodBarra = findViewById(R.id.btnCodBarra);
        recyclerViewActivos.setLayoutManager(new LinearLayoutManager(this));

        adaptadorActivos=new ReciclerViewAdaptador(obtenerListaActivos(""));
        recyclerViewActivos.setAdapter(adaptadorActivos);

        etBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adaptadorActivos=new ReciclerViewAdaptador(obtenerListaActivos(s.toString()));
                recyclerViewActivos.setAdapter(adaptadorActivos);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btLeerCodBarra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lBarra = new Intent(getApplicationContext(), SimpleScannerActivity.class);
                startActivity(lBarra);
            }
        });

    }


    private  List<Activos> obtenerListaActivos(String cadBuscar) {
        ArrayList<Activos> a = daoActivos.verTodos();
        List<Activos> listActivos = new ArrayList<>();

        if (a.size() > 0) {
            for (int i = 0; i < a.size(); i++) {
                if (cadBuscar!="") {  // si la cadena a buscar es distinto de  "" entonces aplica filtro
                    if (a.get(i).getCodigo().toLowerCase().contains(cadBuscar.toLowerCase())) {
                        listActivos.add(new Activos(a.get(i).getCodigo(), a.get(i).getDESCRIPCION(), a.get(i).getFECHA_INGRESO(), R.drawable.cara_duda));
                    }
                }else{ // si la cadena a buscar es igual a "" entonces muestra todos los registros
                        Log.i("MyDB", a.get(i).getCodigo());
                        Log.i("MyDB", a.get(i).getDESCRIPCION());
                        listActivos.add(new Activos(a.get(i).getCodigo(), a.get(i).getDESCRIPCION(), a.get(i).getFECHA_INGRESO(), R.drawable.cara_duda));
                    }
            }
        } else {
            Toast.makeText(this, "No existen datos de Activos", Toast.LENGTH_LONG).show();
        }
        return listActivos;
    }
}
