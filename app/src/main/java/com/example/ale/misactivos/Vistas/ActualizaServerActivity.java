package com.example.ale.misactivos.Vistas;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ale.misactivos.Model.DaoActivos;
import com.example.ale.misactivos.R;
import com.example.ale.misactivos.entidades.Activos;

import org.json.JSONObject;

import java.util.ArrayList;

public class ActualizaServerActivity extends AppCompatActivity implements  Response.Listener<JSONObject>, Response.ErrorListener  {

    ArrayAdapter adaptadorlista;
    ArrayList listadatos= new ArrayList<String>();
    ListView lista;
    Activos a;
    DaoActivos dao;
    ArrayList<Activos>  cambioActivos= new ArrayList<>();

    private static String TAG = "MainActivity ";
    Button btnDescarga;
    private ProgressDialog progress;
    RequestQueue request;
    StringRequest srequest;
    JsonObjectRequest jsonObjectRequest;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualiza_server);

        StrictMode.ThreadPolicy policy = new
        StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        btnDescarga =  findViewById(R.id.btnDescarga);
        lista = findViewById(R.id.lvActivosModif);

        dao= new DaoActivos(this);
        CargarListaActivos();

        adaptadorlista= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listadatos);
        lista.setAdapter(adaptadorlista);

        request= Volley.newRequestQueue(this);
        //srequest= Volley.newRequestQueue(getApplicationContext());

        btnDescarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i=0 ;i<cambioActivos.size();i++)
                    ActualizaServer(cambioActivos.get(i));
                //barraProgreso(view);
            }
        });
    }
    private void ActualizaServer(Activos a) {

       /* String codigo=a.getCodigo();
        Integer correlativo= a.getCORRELATIVO();
        String tipo= a.getTIPO();
        String descripcion= a.getDESCRIPCION();
        String unidad= a.getUNIDAD();
        String fecha_ing= a.getFECHA_INGRESO();
        Double valor= Double.parseDouble(a.getVALOR());
        Double valor_residual= Double.parseDouble(a.getVALOR_RESIDUAL());
        String estadofisico= a.getESTADOFISICO();
        String estadobd= a.getESTADO_BD();
        String observacion= a.getOBSERVACION();
        String grupo=a.getGRUPO();
        String auxiliar= a.getAUXILIAR();
        Integer gestion_ing= a.getGESTION_INGRESO();
        String partida= a.getPARTIDA();
        String glosa= a.getGLOSA();
        String color= a.getCOLOR();
        String serie= a.getSERIE();
        String marca= a.getMARCA();
        String modelo=a.getMODELO();
        String nroplaca= a.getPLACA();
        Integer gestion_baja= a.getGESTION_BAJA();
        String baja= a.getBAJA();
        String ubi_geog= a.getUBI_GEOGRAFICA();
        String origen= a.getORIGEN();*/

         //codigo="+a.getCodigo()+"&descripcion="+ a.getDESCRIPCION()+"&estadofisico="+ a.getESTADOFISICO()+"&observacion="+ a.getOBSERVACION() +"&glosa="+ a.getGLOSA()+"&color="+ a.getCOLOR() +"&serie="+ a.getSERIE() +"&marca="+ a.getMARCA()+"&modelo="+a.getMODELO() +"&nroplaca="+ a.getPLACA();


        String url= getString(R.string.ipServer)+"wsActualizaServer.php?codigo="+a.getCodigo()+"&descripcion="+
                a.getDESCRIPCION()+"&estadofisico="+ a.getESTADOFISICO()+"&observacion="+ a.getOBSERVACION() +"&glosa="+
                a.getGLOSA()+"&color="+ a.getCOLOR() +"&serie="+ ((a.getSERIE() != null || !a.getSERIE().isEmpty())?"NADA": a.getSERIE()) +"&marca="+ a.getMARCA()+"&modelo="+((a.getMODELO() != null || !a.getMODELO().isEmpty())?"NADA": a.getMODELO()) +
                "&nroplaca="+ ((a.getPLACA() != null || !a.getPLACA().isEmpty())?"NADA": a.getPLACA());

        url=url.replace(" ","%20");
        Log.i("MyDB", "Cadena: "+url);
        jsonObjectRequest= new JsonObjectRequest(Request.Method.POST, url,null,this,this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onResponse(JSONObject response) {
        //progreso.hide();
        Toast.makeText(getApplicationContext(),"Registro exitoso", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        //progreso.hide();
        Toast.makeText(getApplicationContext(),"NO se pudo registrar"+ error.toString(), Toast.LENGTH_LONG).show();
        Log.i("MyDB","Error :"+error.getMessage());
        finish();
    }

    public void barraProgreso(View view){
        progress=new ProgressDialog(this);
        progress.setMessage("Cargando datos....");
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
    public void CargarListaActivos() {
        cambioActivos = dao.verActivosCambios();

        if (cambioActivos.size() > 0) {
            for (int i = 0; i < cambioActivos.size(); i++) {
                listadatos.add(cambioActivos.get(i).getCodigo()+ "-" + cambioActivos.get(i).getDESCRIPCION());
            }
        } else {
            Toast.makeText(this, "No existen Activos con Modificaciones", Toast.LENGTH_LONG).show();
        }
    }


}
