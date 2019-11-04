package com.example.ale.misactivos.Vistas;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

public class CrudActivosActivity extends AppCompatActivity {
    private RecyclerView recyclerViewActivos;
    private ReciclerViewAdaptador adaptadorActivos;
    DaoActivos daoActivos;
    private EditText etBuscar;
    Button btLeerCodBarra;
    String cad="";
    CardView cardView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(
                R.layout.layout_activos);

        daoActivos= new DaoActivos(this);
        etBuscar =  findViewById(R.id.etBuscar);
        cardView = findViewById(R.id.idCardviewActivos);
        recyclerViewActivos = findViewById(R.id.reciclerActivos);
        btLeerCodBarra = findViewById(R.id.btnCodBarra);
        recyclerViewActivos.setLayoutManager(new LinearLayoutManager(this));

        adaptadorActivos=new ReciclerViewAdaptador(obtenerListaActivos(cad));

        recyclerViewActivos.setAdapter(adaptadorActivos);


       /* btLeerCodBarra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escaner();
            }
        });

        adaptadorActivos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MyDB", "llegue aqui");
                Toast.makeText(getApplicationContext(),"Seleccion "+obtenerListaActivos(cad).get(
                        recyclerViewActivos.getChildAdapterPosition(v)).getCodigo(),Toast.LENGTH_SHORT).show();
            }
        });
        etBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                cad=s.toString();
                adaptadorActivos=new ReciclerViewAdaptador(obtenerListaActivos(cad));
                recyclerViewActivos.setAdapter(adaptadorActivos);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/
        etBuscar.setOnClickListener(myOnClickListener);
        btLeerCodBarra.setOnClickListener(myOnClickListener);
        recyclerViewActivos.setOnClickListener(myOnClickListener);
        //cardView.setOnClickListener(myOnClickListener);
    }

    private View.OnClickListener myOnClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch (v.getId()){

                case R.id.btnCodBarra:
                    escaner();
                    break;
                case R.id.reciclerActivos:
                    Log.i("MyDB", "llegue aqui"+obtenerListaActivos(cad).get(
                            recyclerViewActivos.getChildAdapterPosition(v)).getCodigo());
                    Toast.makeText(getApplicationContext(),"Seleccion "+obtenerListaActivos(cad).get(
                            recyclerViewActivos.getChildAdapterPosition(v)).getCodigo(),Toast.LENGTH_LONG).show();
                    break;
                case R.id.etBuscar:
                    etBuscar.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            cad=s.toString();
                            adaptadorActivos=new ReciclerViewAdaptador(obtenerListaActivos(cad));
                            Log.i("MyDB", "llegue aqui 000" +obtenerListaActivos(cad));
                            recyclerViewActivos.setAdapter(adaptadorActivos);
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    break;
            }
        }
    };
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
