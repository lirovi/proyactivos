package com.example.ale.misactivos.Vistas;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ale.misactivos.Model.DaoActivos;
import com.example.ale.misactivos.Model.DaoCustodias;
import com.example.ale.misactivos.Model.DaoDetcustodias;
import com.example.ale.misactivos.Model.DaoEdificios;
import com.example.ale.misactivos.Model.DaoFuncionario;
import com.example.ale.misactivos.R;
import com.example.ale.misactivos.entidades.Activos;
import com.example.ale.misactivos.entidades.Custodias;
import com.example.ale.misactivos.entidades.Detcustodias;
import com.example.ale.misactivos.entidades.Edificios;
import com.example.ale.misactivos.entidades.Funcionarios;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fr.ganfra.materialspinner.MaterialSpinner;

public class GestionInventarioActivity extends AppCompatActivity {

    private static String TAG = "MainActivity ";
    private ProgressDialog progress;

    MaterialSpinner spinneredificio, spinnerfuncionario;
    ArrayList<String> listItemEdificio = new ArrayList<>();
    ArrayList<String> listItemFuncionario = new ArrayList<>();
    ArrayAdapter<String> adapterEdificio;
    ArrayAdapter<String> adapterFuncionario;
    Button btProcesar; //BDR= Base de Datos Remota
    DaoEdificios daoEdificios;
    DaoFuncionario daoFuncionario;
    DaoCustodias daoCustodias;
    String coded = "", codfun = "", nomed = "Oficina", nomfun = "Funcionario";
    Edificios e;
    DaoDetcustodias daoDetcustodias;
    Funcionarios f;
    RequestQueue queueFuncionarios = null;
    RequestQueue queueEdificios = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);

        showToolbar(getResources().getString(R.string.toolbar_tittle_inventario), true);

        daoEdificios = new DaoEdificios(getApplicationContext());
        daoFuncionario = new DaoFuncionario(getApplicationContext());
        daoCustodias = new DaoCustodias(getApplicationContext());
        daoDetcustodias = new DaoDetcustodias(getApplicationContext());


        queueFuncionarios = Volley.newRequestQueue(this);
        queueEdificios = Volley.newRequestQueue(this);

        listItemEdificio.add("Seleccione");
        listItemFuncionario.add("Seleccione");

        CargarSpinnerEdificios();

        spinneredificio = ( MaterialSpinner ) findViewById(R.id.spinnerEdificio);
        adapterEdificio = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listItemEdificio);
        adapterEdificio.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneredificio.setAdapter(adapterEdificio);
        spinneredificio.setBackgroundResource(R.drawable.backgroundspinner);
        spinneredificio.setSelection(0);

        spinneredificio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    String selected[] = (spinneredificio.getItemAtPosition(position).toString().split("-"));
                    Toast.makeText(getApplicationContext(), "Datos Selecionado: " + selected[1], Toast.LENGTH_SHORT).show();
                    CargarSpinnerFuncionariosXfiltro(selected[1]);

                    coded = selected[1];
                    nomed = selected[2];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerfuncionario = ( MaterialSpinner ) findViewById(R.id.spinnerFuncionario);
        adapterFuncionario = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listItemFuncionario);
        adapterFuncionario.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerfuncionario.setAdapter(adapterFuncionario);
        spinnerfuncionario.setBackgroundResource(R.drawable.backgroundspinner);
        spinnerfuncionario.setSelection(0);

        spinnerfuncionario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    String selecfun[] = (spinnerfuncionario.getItemAtPosition(position).toString().split("-"));
                    Toast.makeText(getApplicationContext(), "Datos Seleccionado" + selecfun[0] + " - " + selecfun[1], Toast.LENGTH_SHORT).show();
                    codfun = selecfun[0];
                    nomfun = selecfun[1];
                } else {
                    codfun = "Seleccione";
                    nomfun = "Todos del área";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btProcesar = findViewById(R.id.btCargaDatos);
        btProcesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent veractivos = new Intent(GestionInventarioActivity.this, DetalleInventarioActivity.class);
                veractivos.putExtra("vedificio", nomed);
                veractivos.putExtra("vfuncionario", nomfun);
                startActivity(veractivos);

            }
        });
    }

    private void CargarSpinnerEdificios() {
        ArrayList<Edificios> edi = daoEdificios.verTodos();

        if (edi.size() > 0) {
            for (int i = 0; i < edi.size(); i++) {
                //Log.i("MyDB",edi.get(i).getCodigo()+"-"+edi.get(i).getNombreedificio()+"-"+edi.get(i).getEstado());
                listItemEdificio.add(edi.get(i).getId() + "-" + edi.get(i).getCodigo() + "-" + edi.get(i).getNombreedificio());
            }
        } else {
            Toast.makeText(this, "No existen datos de edificio", Toast.LENGTH_LONG).show();
        }
    }


    private void CargarSpinnerFuncionariosXfiltro(String ed) {
        ArrayList<Funcionarios> fun = daoFuncionario.verTodosXfiltro(ed);

        if (fun.size() > 0) {
            for (int i = 0; i < fun.size(); i++) {

                listItemFuncionario.add(fun.get(i).getNrodoc() +
                        "-" + fun.get(i).getNombre() + "-" + fun.get(i).getApellidou());
            }
        } else {
            Toast.makeText(this, "No existen datos de FUNCIONARIOS", Toast.LENGTH_LONG).show();
        }
    }

    public void descargar() {
        progress = new ProgressDialog(this);
        progress.setMessage("Descargando datos....");
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // progress.setIndeterminate(true);
        progress.setProgress(0);
        progress.show();

        final int totalProgressTime = 100;
        final Thread t = new Thread() {
            @Override
            public void run() {
                int jumpTime = 0;

                while (jumpTime < totalProgressTime) {
                    try {
                        jumpTime += 5;
                        progress.setProgress(jumpTime);
                        sleep(200);
                    } catch (InterruptedException e) {
                        Log.e(TAG, e.getMessage());
                    }
                }
                progress.setMessage("Completado..., toque la pantalla");
                progress.dismiss();
            }
        };
        t.start();
    }

    public void showToolbar(String tittle, boolean upButton) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

}