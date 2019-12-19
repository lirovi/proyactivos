package com.example.ale.misactivos.Vistas;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ale.misactivos.Adapter.ReciclerViewAdaptador;
import com.example.ale.misactivos.Model.DaoActivos;
import com.example.ale.misactivos.R;
import com.example.ale.misactivos.entidades.Activos;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

public class CrudActivosActivity extends AppCompatActivity {
    private RecyclerView recyclerViewActivos;
    private ReciclerViewAdaptador adaptadorActivos;
    DaoActivos daoActivos;
    private EditText etBuscar;
    TextView tvEdif, tvFunc;
    Button btLeerCodBarra;
    String cad="", nomedif, nomfun;
    CardView cardView;
    List<Activos> listActivos;
    ArrayList<Activos> a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activos);

        daoActivos= new DaoActivos(this);
        etBuscar =  findViewById(R.id.etBuscar);
        tvEdif= findViewById(R.id.tvEdificio);
        tvFunc= findViewById(R.id.tvFuncionario);
        cardView = findViewById(R.id.idCardviewActivos);
        recyclerViewActivos = findViewById(R.id.reciclerActivos);
        btLeerCodBarra = findViewById(R.id.btnCodBarra);
        recyclerViewActivos.setLayoutManager(new LinearLayoutManager(this));

        Bundle vdatos= getIntent().getExtras();
        nomedif = vdatos.getString("vedificio");
        nomfun = vdatos.getString("vfuncionario");

        tvEdif.setText(nomedif);
        tvFunc.setText(nomfun);
        adaptadorActivos=new ReciclerViewAdaptador(this,obtenerListaActivos(cad),nomedif,nomfun);

        recyclerViewActivos.setAdapter(adaptadorActivos);


       btLeerCodBarra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escaner();
            }
        });

        etBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                cad=s.toString();
                adaptadorActivos=new ReciclerViewAdaptador(getApplicationContext(),obtenerListaActivos(cad),nomedif,nomfun);
                recyclerViewActivos.setAdapter(adaptadorActivos);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    public  void escaner(){
        IntentIntegrator intent = new IntentIntegrator(this);
        intent.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);

        intent.setPrompt("Escanear Código");
        intent.setCameraId(0);
        intent.setBeepEnabled(false);
        intent.setBarcodeImageEnabled(false);
        intent.setOrientationLocked(false);
        intent.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result= IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(result != null){
            if(result.getContents()== null){
                Toast.makeText(this,"Cancelaste el escaneo",Toast.LENGTH_SHORT).show();

            }else{
                etBuscar.setText(result.getContents().toString());
            }
        }else {
            super.onActivityResult(requestCode,resultCode,data);
        }
    }

    private  List<Activos> obtenerListaActivos(String cadBuscar) {
        a = daoActivos.verTodos();
        listActivos = new ArrayList<>();

        if (a.size() > 0) {
            for (int i = 0; i < a.size(); i++) {
                if (cadBuscar!="") {  // si la cadena a buscar es distinto de  "" entonces aplica filtro
                    if (a.get(i).getCodigo().toLowerCase().contains(cadBuscar.toLowerCase())) {

                        Cargarlista(i);
                    }
                }else{ // si la cadena a buscar es igual a "" entonces muestra todos los registros

                        Cargarlista(i);
                    }
                Log.i("MyDB", a.get(i).getCodigo());
                Log.i("MyDB", a.get(i).getESTADOFISICO());
            }
        } else {
            Toast.makeText(this, "No existen datos de Activos", Toast.LENGTH_LONG).show();
        }
        return listActivos;
    }

    private void Cargarlista(int i) {
        listActivos.add(new Activos(
                a.get(i).getCodigo(),
                a.get(i).getCORRELATIVO(),
                a.get(i).getTIPO(),
                a.get(i).getDESCRIPCION(),
                a.get(i).getUNIDAD(),
                a.get(i).getFECHA_INGRESO(),
                a.get(i).getVALOR(),
                a.get(i).getVALOR_RESIDUAL(),
                a.get(i).getESTADOFISICO(),
                a.get(i).getESTADO_BD(),
                a.get(i).getOBSERVACION(),
                a.get(i).getGRUPO(),
                a.get(i).getAUXILIAR(),
                a.get(i).getGESTION_INGRESO(),
                a.get(i).getPARTIDA(),
                a.get(i).getGLOSA(),
                a.get(i).getCOLOR(),
                a.get(i).getSERIE(),
                a.get(i).getMARCA(),
                a.get(i).getMODELO(),
                a.get(i).getPLACA(),
                a.get(i).getBAJA(),
                a.get(i).getGESTION_BAJA(),
                a.get(i).getUBI_GEOGRAFICA(),
                a.get(i).getORIGEN(),
                "drawable-mdpi/cara_duda.png",
                a.get(i).getCAMBIO(),
                a.get(i).getESTADO()));
    }
}
