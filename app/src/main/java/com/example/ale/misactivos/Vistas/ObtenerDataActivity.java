package com.example.ale.misactivos.Vistas;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
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
import com.example.ale.misactivos.Model.DaoEdificios;
import com.example.ale.misactivos.Model.DaoFuncionario;
import com.example.ale.misactivos.Operaciones.CargarWSEdificio;
import com.example.ale.misactivos.Operaciones.CargarWSfuncionario;
import com.example.ale.misactivos.R;
import com.example.ale.misactivos.entidades.Edificios;
import com.example.ale.misactivos.entidades.Funcionarios;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fr.ganfra.materialspinner.MaterialSpinner;

public class ObtenerDataActivity extends AppCompatActivity  {

    private static String TAG = "MainActivity ";
    private ProgressDialog progress;
    private Edificios edificios;
    private Funcionarios funcionarios;

    MaterialSpinner spinneredificio,  spinnerfuncionario;
    ArrayList<String> listItemEdificio = new ArrayList<>();
    ArrayList<String> listItemFuncionario = new ArrayList<>();
    ArrayAdapter<String> adapterEdificio;
    ArrayAdapter<String> adapterFuncionario;
    Button btProcesar,btCargarBDR,btCargaEdiBDR,btCargaFunBDR; //BDR= Base de Datos Remota
    JsonObjectRequest jsonObjectRequest;
    DaoEdificios daoEdificios;
    DaoFuncionario daoFuncionario;
    Context context;
    Edificios e;
    Funcionarios f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obtener_data);

        showToolbar(getResources().getString(R.string.toolbar_tittle_obtenerDatos),true);

        daoEdificios= new DaoEdificios(getApplicationContext());
        daoFuncionario = new DaoFuncionario(getApplicationContext());
        btCargarBDR = findViewById(R.id.btCargaDatosBDR);
        btCargaEdiBDR = findViewById(R.id.btCargaEdificio);
        btCargaFunBDR = findViewById(R.id.btCargaFuncionario);

        final RequestQueue queuef = Volley.newRequestQueue(this);
        final RequestQueue queueE = Volley.newRequestQueue(this);


        listItemEdificio.add("Seleccione");
        listItemFuncionario.add("Seleccione");

        CargarSpinnerEdificios();

        spinneredificio = (MaterialSpinner) findViewById(R.id.spinnerEdificio);
        adapterEdificio =  new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,listItemEdificio);
        adapterEdificio.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneredificio.setAdapter(adapterEdificio);
        spinneredificio.setSelection(0);

        spinneredificio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0){
                    String selected[] =  (spinneredificio.getItemAtPosition(position).toString().split("-"));
                    Toast.makeText(getApplicationContext(), "Datos Selecionado: "+selected[1], Toast.LENGTH_SHORT).show();
                    CargarSpinnerFuncionarioXfiltro(selected[1]);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerfuncionario = (MaterialSpinner) findViewById(R.id.spinnerFuncionario);
        adapterFuncionario = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,  listItemFuncionario);
        adapterFuncionario.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerfuncionario.setAdapter(adapterFuncionario);
        spinnerfuncionario.setSelection(0);

        spinnerfuncionario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0){
                    //String selecfun[] =  (spinnerfuncionario.getItemAtPosition(position).toString().split("-"));
                    //Toast.makeText(getApplicationContext(), "Datos Seleccionado"+selecfun[0]+" - "+selecfun [1], Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btCargaEdiBDR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = getString(R.string.ipServer)+"wsJSONConsEdificios.php";

                jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                //Toast.makeText(getApplicationContext(),"Response: " + response.toString(),Toast.LENGTH_LONG).show();
                                daoEdificios = new DaoEdificios( getApplicationContext());
                                Toast.makeText(getApplicationContext(),"Datos:"+response.toString(),Toast.LENGTH_LONG).show();

                                JSONArray json = response.optJSONArray("edificio");
                                JSONObject jsonedi=null;
                                if(daoEdificios.limpiarTabla()) {

                                    for (int i = 0; i < json.length(); i++) {

                                        try {
                                            jsonedi = json.getJSONObject(i);
                                            //listItem.add(jsonedi.getString("CODIGO")+"-"+
                                            //jsonedi.getString("NOMBREEDIFICIO"));
                                            Log.i("MyDB",jsonedi.getString("CODIGO"));
                                            Log.i("MyDB",jsonedi.getString("NOMBRE"));
                                            e = new Edificios(jsonedi.getString("CODIGO"), jsonedi.getString("NOMBRE"));
                                            daoEdificios.insertar(e);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }


                                    }
                                }else{
                                    Toast.makeText(getApplicationContext(),"No se puede Limpiar la Tabla Edificios",Toast.LENGTH_SHORT).show();
                                }
                                CargarSpinnerEdificios();

                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error
                                Toast.makeText(getApplicationContext(),"NO es posible la conexion a la BD: " +error.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        });
                queueE.add(jsonObjectRequest);
            }

        });
        //btCargarBDR.setOnClickListener();

        btCargaFunBDR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = getString(R.string.ipServer)+"wsJSONConsPersonas.php";

                jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                daoFuncionario = new DaoFuncionario( getApplicationContext());
                                Toast.makeText(getApplicationContext(),"Datos:"+response.toString(),Toast.LENGTH_LONG).show();

                                JSONArray json = response.optJSONArray("funcionarios");
                                JSONObject jsonf=null;
                                if(daoFuncionario.limpiarTabla()) {

                                    for (int i = 0; i < json.length(); i++) {

                                        try {
                                            jsonf = json.getJSONObject(i);

                                            Log.i("MyDB",jsonf.getString("DOCUMENTO"));
                                            Log.i("MyDB",jsonf.getString("NOMBRE"));
                                            f = new Funcionarios(jsonf.getString("DOCUMENTO"), jsonf.getString("NOMBRE"),
                                                    jsonf.getString("APELLIDOS"),
                                                    jsonf.getString("NACIONALIDAD"), jsonf.getString("SEXO"));
                                            daoFuncionario.insertar(f);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }


                                    }
                                }else{
                                    Toast.makeText(getApplicationContext(),"No se puede Limpiar la Tabla FUNCIONARIOS",Toast.LENGTH_SHORT).show();
                                }
                                CargarSpinnerFuncionarios();

                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error
                                Toast.makeText(getApplicationContext(),"NO es posible la conexion a la BD: " +error.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        });
                queuef.add(jsonObjectRequest);


            }
        });

        @Override protected Response<JSONObject> parseNetworkResponse(NetworkResponse response)
        {
            try {
                String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
                JSONObject result = null;
                if (jsonString != null && jsonString.length() > 0)
                    result = new JSONObject(jsonString);
                return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));
            } catch (UnsupportedEncodingException e) {
                return Response.error(new ParseError(e));
            } catch (JSONException je) {
                return Response.error(new ParseError(je));
            }
        }


        btProcesar= findViewById(R.id.btCargaDatos);
        btProcesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    private void CargarDatosEdificioWS( JSONObject response) {
       daoEdificios = new DaoEdificios( context);
        Toast.makeText(context,"Datos:"+response,Toast.LENGTH_LONG).show();

        JSONArray json = response.optJSONArray("edificios");
        JSONObject jsonedi=null;
        for(int i=0; i<json.length();i++){

            try {
                jsonedi = json.getJSONObject(i);
                /*listItem.add(jsonedi.getString("CODIGO")+"-"+
                jsonedi.getString("NOMBREEDIFICIO"));*/
                e = new Edificios(jsonedi.getString("CODIGO"),jsonedi.getString("NOMBREEDIFICIO"));
                daoEdificios.insertar(e);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }


    private  void CargarSpinnerEdificios() {
        ArrayList<Edificios> edi = daoEdificios.verTodos();

        if(edi.size()>0) {
            for (int i = 0; i < edi.size(); i++) {
                //Log.i("MyDB",edi.get(i).getCodigo()+"-"+edi.get(i).getNombreedificio()+"-"+edi.get(i).getEstado());
                listItemEdificio.add(edi.get(i).getId() + "-" + edi.get(i).getCodigo() + "-" + edi.get(i).getNombreedificio());
            }
        }else{
            Toast.makeText(this,"No existen datos de edificio",Toast.LENGTH_LONG).show();
        }

    }
    private  void CargarSpinnerFuncionarios() {
        ArrayList<Funcionarios> fun = daoFuncionario.verTodos();

        if(fun.size()>0) {
            for (int i = 0; i < fun.size(); i++) {
                //Log.i("MyDB",edi.get(i).getCodigo()+"-"+edi.get(i).getNombreedificio()+"-"+edi.get(i).getEstado());
                listItemFuncionario.add(fun.get(i).getId() + "-" + fun.get(i).getNrodoc() +
                        "-" + fun.get(i).getNombre()+ "-" + fun.get(i).getApellidou()+
                        "-" + fun.get(i).getNacionalidad()+ "-" + fun.get(i).getSexo());
            }
        }else{
            Toast.makeText(this,"No existen datos de FUNCIONARIOS",Toast.LENGTH_LONG).show();
        }

    }
    private void CargarSpinnerFuncionarioXfiltro(String codedificio){
        ArrayList<Funcionarios> fun = daoFuncionario.verFuncionariosXfiltro(codedificio);


        if(fun.size()>0) {
            for (int i = 0; i < fun.size(); i++) {
                //Log.i("MyDB",edi.get(i).getCodigo()+"-"+edi.get(i).getNombreedificio()+"-"+edi.get(i).getEstado());
                listItemFuncionario.add(fun.get(i).getId() + "-" + fun.get(i).getNrodoc() + "-" + fun.get(i).getNombre()+" "+ fun.get(i).getApellidou());
            }
        }else{
            Toast.makeText(this,"No existen datos de funcionario",Toast.LENGTH_LONG).show();
        }
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


    public void showToolbar(String tittle, boolean upButton){
        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }


}
