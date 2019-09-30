package com.example.ale.misactivos.Vistas;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ale.misactivos.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class ObtenerDataActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    private static String TAG = "MainActivity ";
    private ProgressDialog progress;

   MaterialSpinner spinner,  spinnerfuncionario, spinnertipoequipo;
   List<String> listItemEdificio = new ArrayList<>();
   List<String> listItemFuncionario = new ArrayList<>();
   List<String> listItemTipoEquipo = new ArrayList<>();
   ArrayAdapter<String> adapterEdificio, adapterFuncionario, adapterTipoEquipo;
   Button btProcesar;
    JsonObjectRequest jsonObjectRequest;
    RequestQueue request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obtener_data);
        showToolbar(getResources().getString(R.string.toolbar_tittle_obtenerDatos),true);
        //initItems();

        Context context;
        request= Volley.newRequestQueue(this);

        cargarWsEdificios();

        spinner = (MaterialSpinner) findViewById(R.id.spinnerEdificio);
        adapterEdificio = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,listItemEdificio);
        adapterEdificio.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterEdificio);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != -1){
                    String selected = (spinner.getItemAtPosition(position).toString());
                    Toast.makeText(getApplicationContext(), "Datos Selecionado"+selected, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*adapterUnidad = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,listItemUnidad);
        adapterUnidad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnidad.setAdapter(adapterUnidad);

        spinnerUnidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != -1){
                    String selected = (spinnerUnidad.getItemAtPosition(position).toString());

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerfuncionario = (MaterialSpinner) findViewById(R.id.spinnerfuncionario);
        adapterFuncionario = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,listItemFuncionario);
        adapterFuncionario.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerfuncionario.setAdapter(adapterFuncionario);

        spinnerfuncionario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != -1){
                    String selected = (spinnerfuncionario.getItemAtPosition(position).toString());
                    //if(selected % 2==0){
                      //  spinner.setError("Este es un mensaje de error");
                    //}
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnertipoequipo = (MaterialSpinner) findViewById(R.id.spinnertipoequipo);
        adapterTipoEquipo = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,listItemTipoEquipo);
        adapterTipoEquipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnertipoequipo.setAdapter(adapterTipoEquipo);

        spinnertipoequipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != -1){
                    String selected = (spinnertipoequipo.getItemAtPosition(position).toString());

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
        btProcesar= findViewById(R.id.cargaDatos);
        btProcesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(ObtenerDataActivity.this, "Datos descargados con exito", Toast.LENGTH_SHORT).show();
                descargar(v);
            }
        });
    }

    public void descargar(View view){
        progress=new ProgressDialog(this);
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

                while(jumpTime < totalProgressTime) {
                    try {
                        jumpTime += 5;
                        progress.setProgress(jumpTime);
                        sleep(200);
                    }
                    catch (InterruptedException e) {
                        Log.e(TAG, e.getMessage());
                    }
                }
                progress.setMessage("Completado..., toque la pantalla");
            }
        };
        t.start();
    }

    private void cargarWsEdificios() {
        String url="http://192.168.3.223/conexoracle/wsJSONConsEdificios.php";

        //Toast.makeText(this,"URL:"+url,Toast.LENGTH_LONG).show();
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                this, this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this,"No se pudo consultar Edificios",Toast.LENGTH_LONG).show();
        Log.i("Error:",error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(this,"Datos:"+response,Toast.LENGTH_LONG).show();
        JSONArray json = response.optJSONArray("edificio");
        JSONObject jsonEdificio=null;
        for(int i=0; i<json.length();i++){

            try {
                jsonEdificio = json.getJSONObject(i);
                listItemEdificio.add(jsonEdificio.getString("CODIGO")+" "+jsonEdificio.getString("NOMBRE"));
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }

    private void initItems() {
       /* for(int i=0;i<=100;i++)
        {
            listItem.add(i);
        }*/
        /*listItem.add("Ejecutivo");
        listItem.add("Departamentos Contabilidad");
        listItem.add("Departamentos Agua Potable");
        listItem.add("Departamentos Alcantarillado");
        listItem.add("Departamentos Control Calidad");
        listItem.add("Departamentos Comercial");
        listItem.add("Departamentos Informática");*/



       /* listItemUnidad.add("Gerencia General");
        listItemUnidad.add("Gerencia Administrativa");
        listItemUnidad.add("Gerencia Técnica");
        listItemUnidad.add("Jefatura Contable");
        listItemUnidad.add("Jefatura Agua Potable");
        listItemUnidad.add("Jefatura Comercial");
        listItemUnidad.add("Jefatura Informática");
        listItemUnidad.add("Encargado Informática");
        listItemUnidad.add("Encargado Activos Fijos");
        listItemUnidad.add("Encargado Facturacion");
        listItemUnidad.add("Encargado Micromedicion");
        listItemUnidad.add("Encargado Consumo Medido");
        listItemUnidad.add("Encargado Almacenes");

        listItemFuncionario.add("Limber Rojas");
        listItemFuncionario.add("José Cortéz");
        listItemFuncionario.add("Waldo Serrudo");
        listItemFuncionario.add("Fernando Chacón");
        listItemFuncionario.add("Consuelo Vallejos");
        listItemFuncionario.add("Franz Heredia");
        listItemFuncionario.add("Mauro Claros");
        listItemFuncionario.add("Nelson Salgueiro");
        listItemFuncionario.add("Sergio Aramayo");
        listItemFuncionario.add("Claudia Cuellar");
        listItemFuncionario.add("David Dolz");
        listItemFuncionario.add("Faustino Velásquez");
        listItemFuncionario.add("Rómulo Grimaldo");
        listItemFuncionario.add("Marcelo Rossel");

        listItemTipoEquipo.add("Computadora");
        listItemTipoEquipo.add("Bomba sumergible");
        listItemTipoEquipo.add("Impresora");
        listItemTipoEquipo.add("Mueble");
        listItemTipoEquipo.add("Camioneta");
        listItemTipoEquipo.add("Moto");
        listItemTipoEquipo.add("Maquinaria");*/

    }


    public void showToolbar(String tittle, boolean upButton){
        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }


}
