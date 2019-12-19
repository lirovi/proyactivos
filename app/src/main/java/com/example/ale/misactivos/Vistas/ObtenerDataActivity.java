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
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import com.example.ale.misactivos.entidades.Tipodocumentos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fr.ganfra.materialspinner.MaterialSpinner;

public class ObtenerDataActivity extends AppCompatActivity  {

    private static String TAG = "MainActivity ";
    private ProgressDialog progreso, progress;
    private Edificios edificios;
    private Funcionarios funcionarios;


    MaterialSpinner spinneredificio,  spinnerfuncionario;
    ArrayList<String> listItemEdificio = new ArrayList<>();
    ArrayList<String> listItemFuncionario = new ArrayList<>();
    ArrayAdapter<String> adapterEdificio;
    ArrayAdapter<String> adapterFuncionario;
    Button btProcesar,btCargaEdiBDR,btCargaFunBDR; //BDR= Base de Datos Remota
    ImageButton btVerActivos, btBajarCustodias,btBajaDetcustodia,btDeActivos;
    JsonObjectRequest jsonObjectRequest,jsonObjectRequest1;
    DaoEdificios daoEdificios;
    DaoFuncionario daoFuncionario;
    DaoCustodias daoCustodias;
    Custodias cus;
    DaoActivos daoActivos;
    Activos ac;
    Context context;
    String coded="",codfun="",nomed="Oficina",nomfun="Funcionario";
    Edificios e;
    Detcustodias dcus;
    DaoDetcustodias daoDetcustodias;
    Funcionarios f;
    RequestQueue queueFuncionarios=null;
    RequestQueue queueEdificios = null;
    RequestQueue queueCustodia = null;
    RequestQueue queueDetcustodia = null;
    RequestQueue queueActivos = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obtener_data);

        showToolbar(getResources().getString(R.string.toolbar_tittle_obtenerDatos),true);

        daoEdificios= new DaoEdificios(getApplicationContext());
        daoFuncionario = new DaoFuncionario(getApplicationContext());
        daoActivos = new DaoActivos(getApplicationContext());
        daoCustodias = new DaoCustodias(getApplicationContext());
        daoDetcustodias = new DaoDetcustodias(getApplicationContext());
        btVerActivos = findViewById(R.id.btnVerActivos);
        btBajarCustodias = findViewById(R.id.btnCustodia);
        btBajaDetcustodia = findViewById(R.id.btnDetcustodias);
        btDeActivos = findViewById(R.id.btnDescActivos);
        btCargaEdiBDR = findViewById(R.id.btCargaEdificio);
        btCargaFunBDR = findViewById(R.id.btCargaFuncionario);

        queueFuncionarios = Volley.newRequestQueue(this);
         queueEdificios = Volley.newRequestQueue(this);
         queueCustodia = Volley.newRequestQueue(this);
        queueDetcustodia = Volley.newRequestQueue(this);
        queueActivos = Volley.newRequestQueue(this);


        listItemEdificio.add("Seleccione");
        listItemFuncionario.add("Seleccione");

        CargarSpinnerEdificios();
        //CargarSpinnerFuncionarios();

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
                    CargarSpinnerFuncionariosXfiltro(selected[1]);

                    coded=selected[1];
                    nomed=selected[2];

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
                    String selecfun[] =  (spinnerfuncionario.getItemAtPosition(position).toString().split("-"));
                    Toast.makeText(getApplicationContext(), "Datos Seleccionado"+selecfun[0]+" - "+selecfun [1], Toast.LENGTH_SHORT).show();
                    codfun=selecfun[0];
                    nomfun=selecfun[1];
                }else{
                    codfun="Seleccione";
                    nomfun="Todos del área";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btBajarCustodias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(showdialogo("¿Esta seguro de Cargar Nuevos Datos?, Los datos de CUSTODIAS Anteriores se reemplazarán")) {
                    String url = getString(R.string.ipServer) + "wsJSONCargarCustodias.php";

                    jsonObjectRequest1 = new JsonObjectRequest
                            (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    //Toast.makeText(getApplicationContext(),"Response: " + response.toString(),Toast.LENGTH_LONG).show();
                                    daoCustodias = new DaoCustodias(getApplicationContext());
                                    Toast.makeText(getApplicationContext(), "Datos:" + response.toString(), Toast.LENGTH_LONG).show();

                                    JSONArray jsonc = response.optJSONArray("custodias");
                                    JSONObject json_c = null;
                                    if (daoCustodias.limpiarTabla()) {

                                        for (int i = 0; i < jsonc.length(); i++) {

                                            try {
                                                json_c = jsonc.getJSONObject(i);

                                                cus = new Custodias(json_c.getString("CPBTE"), json_c.getInt("GESTION"), json_c.getString("FECHA"), json_c.getString("FUNCIONARIO"),
                                                        json_c.getString("EDIFICIO"), json_c.getString("GLOSA"));
                                                daoCustodias.insertar(cus);
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    } else {
                                        Toast.makeText(getApplicationContext(), "No se puede Limpiar la Tabla Custodias", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }, new Response.ErrorListener() {

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // TODO: Handle error
                                    Toast.makeText(getApplicationContext(), "NO es posible la conexion a la BD: " + error.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                    queueCustodia.add(jsonObjectRequest1);
                }
            }
        });
        btBajaDetcustodia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(showdialogo("¿Esta seguro de Cargar Nuevos Datos?, Los datos de DETALLE CUSTODIA Anteriores se reemplazarán")) {
                    String url = getString(R.string.ipServer) + "wsJSONCargarDetcustodias.php";

                    jsonObjectRequest = new JsonObjectRequest
                            (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    //Toast.makeText(getApplicationContext(),"Response: " + response.toString(),Toast.LENGTH_LONG).show();
                                    daoDetcustodias = new DaoDetcustodias(getApplicationContext());
                                    Toast.makeText(getApplicationContext(), "Datos:" + response.toString(), Toast.LENGTH_LONG).show();

                                    JSONArray jsondc = response.optJSONArray("detcustodias");
                                    JSONObject json_dc = null;
                                    if (daoDetcustodias.limpiarTabla()) {

                                        for (int i = 0; i < jsondc.length(); i++) {

                                            try {
                                                json_dc = jsondc.getJSONObject(i);

                                                dcus = new Detcustodias(json_dc.getString("CPBTE"), json_dc.getInt("GESTION"),
                                                        json_dc.getString("CODIGO_ACTIVO"), json_dc.getString("ESTADO_ACTUAL"),
                                                        json_dc.getString("ESTADO"));
                                                daoDetcustodias.insertar(dcus);
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    } else {
                                        Toast.makeText(getApplicationContext(), "No se puede Limpiar la Tabla Custodias", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }, new Response.ErrorListener() {

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // TODO: Handle error
                                    Toast.makeText(getApplicationContext(), "NO es posible la conexion a la BD: " + error.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                    queueDetcustodia.add(jsonObjectRequest);
                }
            }
        });
        btCargaEdiBDR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(showdialogo("¿Esta seguro de Cargar Nuevos Datos?, Los datos de EDIFICIO Anteriores se reemplazarán")) {
                    String url = getString(R.string.ipServer) + "wsJSONCargarEdificios.php";

                    jsonObjectRequest = new JsonObjectRequest
                            (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    //Toast.makeText(getApplicationContext(),"Response: " + response.toString(),Toast.LENGTH_LONG).show();
                                    daoEdificios = new DaoEdificios(getApplicationContext());
                                    Toast.makeText(getApplicationContext(), "Datos:" + response.toString(), Toast.LENGTH_LONG).show();

                                    JSONArray json = response.optJSONArray("edificio");
                                    JSONObject jsonedi = null;
                                    if (daoEdificios.limpiarTabla()) {

                                        for (int i = 0; i < json.length(); i++) {

                                            try {
                                                jsonedi = json.getJSONObject(i);

                                                e = new Edificios(jsonedi.getString("CODIGO"), jsonedi.getString("NOMBRE"));
                                                daoEdificios.insertar(e);
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    } else {
                                        Toast.makeText(getApplicationContext(), "No se puede Limpiar la Tabla Edificios", Toast.LENGTH_SHORT).show();
                                    }

                                    CargarSpinnerEdificios();

                                }
                            }, new Response.ErrorListener() {

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // TODO: Handle error
                                    Toast.makeText(getApplicationContext(), "NO es posible la conexion a la BD: " + error.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                    queueEdificios.add(jsonObjectRequest);
                }
            }

        });

        /********************************************************************************/

        btVerActivos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*new AlertDialog.Builder(getApplicationContext())
                        .setTitle( "Alerta")
                        .setMessage("¿Desea ingresar a la edición de Activos?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {*/
                                Intent veractivos = new Intent(ObtenerDataActivity.this, CrudActivosActivity.class);
                                veractivos.putExtra("vedificio", nomed);
                                veractivos.putExtra("vfuncionario", nomfun);
                                startActivity(veractivos);
                          /*  }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d( "MyDB", "Preciono Cancelar");
                            }
                        }).show();*/
            }
        });

        /*******************************************************************************/
        btCargaFunBDR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(showdialogo("¿Esta seguro de Cargar Nuevos Datos?, Los datos de ACTIVOS Anteriores se reemplazarán")) {
                  String url = getString(R.string.ipServer) + "wsJSONCargarFuncionarios.php";

                  jsonObjectRequest = new JsonObjectRequest
                          (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                              @Override
                              public void onResponse(JSONObject response) {
                                  daoFuncionario = new DaoFuncionario(getApplicationContext());
                                  Toast.makeText(getApplicationContext(), "Datos:" + response.toString(), Toast.LENGTH_LONG).show();

                                  JSONArray jsonf = response.optJSONArray("funcionarios");
                                  JSONObject json_f = null;
                                  if (daoFuncionario.limpiarTabla()) {

                                      for (int i = 0; i < jsonf.length(); i++) {

                                          try {
                                              json_f = jsonf.getJSONObject(i);

                                              f = new Funcionarios(json_f.getString("NOMBRE"), json_f.getString("APELLIDOS"),
                                                      json_f.getString("DOCUMENTO"), json_f.getString("NACIONALIDAD"), json_f.getString("SEXO"));
                                              daoFuncionario.insertar(f);
                                          } catch (JSONException e) {
                                              e.printStackTrace();
                                          }
                                      }
                                  } else {
                                      Toast.makeText(getApplicationContext(), "No se puede Limpiar la Tabla FUNCIONARIOS", Toast.LENGTH_SHORT).show();
                                  }
                                  CargarSpinnerFuncionarios();

                              }
                          }, new Response.ErrorListener() {

                              @Override
                              public void onErrorResponse(VolleyError error) {
                                  // TODO: Handle error
                                  Toast.makeText(getApplicationContext(), "NO es posible la conexion a la BD: " + error.getMessage(), Toast.LENGTH_LONG).show();
                              }
                          });
                  queueFuncionarios.add(jsonObjectRequest);
              }
            }
        });
        btDeActivos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(showdialogo("¿Esta seguro de Cargar Nuevos Datos?, Los datos de Activos Anteriores se reemplazarán")) {
                    progreso = new ProgressDialog(context);
                    progreso.setMessage("Cargando...");
                    progreso.show();
                    final String[] eFStr = new String[1];
                    //Log.i("MyDB",":::"+coded+"-"+codfun);
                    String url = getString(R.string.ipServer) + "wsJSONCargaActivos.php";//?coded=" + coded + "&codfun=" + codfun;

                    jsonObjectRequest = new JsonObjectRequest
                            (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    progreso.hide();
                                    daoActivos = new DaoActivos(getApplicationContext());
                                    //Toast.makeText(getApplicationContext(), "Datos:" + response.toString(), Toast.LENGTH_LONG).show();

                                    JSONArray jsona = response.optJSONArray("activos");
                                    JSONObject jsonActivos = null;
                                    if (daoActivos.limpiarTabla()) {
                                        descargar();
                                        Log.i("MyDB", "Registro: " + jsona.length());
                                        for (int i = 0; i < jsona.length(); i++) {
                                            Log.i("MyDB", "Contador i: " + i);
                                            if (i > 37) {
                                                String hola = "hola";
                                            }
                                            try {
                                                jsonActivos = jsona.getJSONObject(i);
                                                /*String valres = jsonActivos.optString("VALOR_RESIDUAL");
                                                if(jsonActivos.optString("VALOR_RESIDUAL") == "null")
                                                        valres="1";*/

                                                ac = new Activos(jsonActivos.getString("CODIGO"),
                                                        jsonActivos.getInt("CORRELATIVO"),
                                                        jsonActivos.getString("TIPO"),
                                                        ((jsonActivos.getString("DESCRIPCION") == "null") ? "NINGUNA" : jsonActivos.getString("DESCRIPCION")),
                                                        jsonActivos.getString("UNIDAD"),
                                                        jsonActivos.getString("FECHA_INGRESO"),
                                                        ((jsonActivos.optString("VALOR") == "null") ? "1" : jsonActivos.getString("VALOR")),
                                                        ((jsonActivos.optString("VALOR_RESIDUAL") == "null") ? "1" : jsonActivos.optString("VALOR_RESIDUAL")),
                                                        ((jsonActivos.optString("ESTADO_FISICO") == "null") ? "1" : jsonActivos.optString("ESTADO_FISICO")),
                                                        ((jsonActivos.optString("ESTADO_BD") == "null") ? "NOTA_INGRESO" : jsonActivos.optString("ESTADO_BD")),
                                                        ((jsonActivos.optString("OBSERVACIONES") == "null") ? "NINGUNA" : jsonActivos.optString("OBSERVACIONES")),
                                                        ((jsonActivos.getString("GRUPO") == "null") ? "NINGUNA" : jsonActivos.getString("GRUPO")),
                                                        ((jsonActivos.getString("AUXILIAR") == "null") ? "NINGUNA" : jsonActivos.getString("AUXILIAR")),
                                                        jsonActivos.optInt("GESTION_INGRESO"),
                                                        ((jsonActivos.getString("PARTIDA") == "null") ? "NINGUNA" : jsonActivos.getString("PARTIDA")),
                                                        ((jsonActivos.getString("GLOSA") == "null") ? "NINGUNA" : jsonActivos.getString("GLOSA")),
                                                        jsonActivos.getString("COLOR"),
                                                        ((jsonActivos.getString("SERIE") == "null") ? "NINGUNA" : jsonActivos.getString("SERIE")),
                                                        jsonActivos.getString("MARCA"),
                                                        jsonActivos.getString("MODELO"),
                                                        jsonActivos.getString("PLACA"),
                                                        jsonActivos.getString("BAJA"),
                                                        jsonActivos.optInt("GESTION_BAJA"),
                                                        jsonActivos.getString("UBI_GEOGRAFICA"),
                                                        jsonActivos.getString("ORIGEN"));
                                                daoActivos.insertarActivo(ac);

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                                Log.i("MyDB", "Error : " + e.getMessage());
                                            }
                                        }
                                    } else {
                                        Toast.makeText(getApplicationContext(), "No existen registros en Activos", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }, new Response.ErrorListener() {

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    progreso.hide();
                                    // TODO: Handle error
                                    Toast.makeText(getApplicationContext(), "NO es posible la conexion a la BD: " + error.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                    queueActivos.add(jsonObjectRequest);
                }
            }
        });
        btProcesar= findViewById(R.id.btCargaDatos);
        btProcesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /*new AlertDialog.Builder(getApplicationContext())
                        .setTitle( "Alerta")
                        .setMessage("¿Desea Cargar datos nuevos de activos de acuerdo a filtro?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {*/
                                Intent veracti = new Intent(ObtenerDataActivity.this, CrudActivosActivity.class);
                                veracti.putExtra("vedificio", nomed);
                                veracti.putExtra("vfuncionario", nomfun);
                                startActivity(veracti);
                          /*  }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d( "MyDB", "Preciono Cancelar");
                            }
                        }).show();*/
            }
        });
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
                listItemFuncionario.add( fun.get(i).getNrodoc() +
                        "-" + fun.get(i).getNombre()+ "-" + fun.get(i).getApellidou());
            }
        }else{
            Toast.makeText(this,"No existen datos de FUNCIONARIOS",Toast.LENGTH_LONG).show();
        }
    }

    private  void CargarSpinnerFuncionariosXfiltro(String ed) {
        ArrayList<Funcionarios> fun = daoFuncionario.verTodosXfiltro(ed);

        if(fun.size()>0) {
            for (int i = 0; i < fun.size(); i++) {

                listItemFuncionario.add( fun.get(i).getNrodoc() +
                        "-" + fun.get(i).getNombre()+ "-" + fun.get(i).getApellidou());
            }
        }else{
            Toast.makeText(this,"No existen datos de FUNCIONARIOS",Toast.LENGTH_LONG).show();
        }

    }


    public void descargar(){
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
                progress.dismiss();
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


    private boolean showdialogo(String mensaje) {
        final boolean[] resul = {false};

        new AlertDialog.Builder(this)
            .setTitle( "Alerta")
            .setMessage(mensaje)
            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    resul[0] = true;
                }
            })
            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.d( "MyDB", "Preciono Cancelar");
                    resul[0]=false;
                }
            }).show();
        return resul[0];
    }

}
