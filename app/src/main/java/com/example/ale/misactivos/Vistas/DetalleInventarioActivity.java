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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

public class DetalleInventarioActivity extends AppCompatActivity {

    private RecyclerView recyclerViewActivos;
    private ReciclerViewAdaptador adaptadorActivos;
    DaoActivos daoActivos;
    TextView tvEdif, tvFunc;
    EditText etCodigo;
    Button btLeerCodBarra, btAdiciona;
    String cad="", nomedif, nomfun;
    List<Activos> listActivos;
    ArrayAdapter adaptador;
    ArrayList<Activos> arrayList= new ArrayList<>();
    Activos a;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_inventario);

        daoActivos= new DaoActivos(this);
        etCodigo =  findViewById(R.id.etCodigoInv);
        tvEdif= findViewById(R.id.tvEdificio);
        tvFunc= findViewById(R.id.tvFuncionario);

        btLeerCodBarra = findViewById(R.id.btnCodBarra);
        btAdiciona= findViewById(R.id.btnAdicionaInventario);
        lista= findViewById(R.id.lvInventario);

       /* adaptador= new ArrayAdapter<Activos>(this,android.R.layout.simple_list_item_1, listActivos);
        lista.setAdapter(adaptador);*/

        Bundle vdatos= getIntent().getExtras();
        nomedif = vdatos.getString("vedificio");
        nomfun = vdatos.getString("vfuncionario");

        tvEdif.setText(nomedif);
        tvFunc.setText(nomfun);

        btLeerCodBarra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escaner();
            }
        });

        btAdiciona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etCodigo.getText().toString().isEmpty()){
                   /* a= daoActivos.verUnoXCodigo(etCodigo.getText().toString());
                    listActivos.add(a);
                    lista.setAdapter(adaptador);*/

                }else{
                    Toast.makeText(getApplicationContext(),"Debe ingresar un codigo de Activos",Toast.LENGTH_SHORT).show();
                }


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
                etCodigo.setText(result.getContents().toString());
            }
        }else {
            super.onActivityResult(requestCode,resultCode,data);
        }
    }

    private List<Activos> obtenerListaActivos2(String edi, String fun) {
        arrayList = daoActivos.verActivosXfiltro2(edi,fun);
        listActivos = new ArrayList<>();
        if (edi.isEmpty() && fun.isEmpty()) {
            if (arrayList.size() > 0) {
                for (int i = 0; i < arrayList.size(); i++)
                    Cargarlista(i);

                //Log.i("MyDB", a.get(i).getCodigo());
                //Log.i("MyDB", a.get(i).getESTADOFISICO());
            } else {
                Toast.makeText(this, "No existen datos de Activos", Toast.LENGTH_LONG).show();
            }
        }else
            Toast.makeText(this, "Debe Seleccionar Edificio y Funcionario", Toast.LENGTH_LONG).show();

        return listActivos;
    }

    private void Cargarlista(int i) {
        listActivos.add(new Activos(
                arrayList.get(i).getCodigo(),
                arrayList.get(i).getCORRELATIVO(),
                arrayList.get(i).getTIPO(),
                arrayList.get(i).getDESCRIPCION(),
                arrayList.get(i).getUNIDAD(),
                arrayList.get(i).getFECHA_INGRESO(),
                arrayList.get(i).getVALOR(),
                arrayList.get(i).getVALOR_RESIDUAL(),
                arrayList.get(i).getESTADOFISICO(),
                arrayList.get(i).getESTADO_BD(),
                arrayList.get(i).getOBSERVACION(),
                arrayList.get(i).getGRUPO(),
                arrayList.get(i).getAUXILIAR(),
                arrayList.get(i).getGESTION_INGRESO(),
                arrayList.get(i).getPARTIDA(),
                arrayList.get(i).getGLOSA(),
                arrayList.get(i).getCOLOR(),
                arrayList.get(i).getSERIE(),
                arrayList.get(i).getMARCA(),
                arrayList.get(i).getMODELO(),
                arrayList.get(i).getPLACA(),
                arrayList.get(i).getBAJA(),
                arrayList.get(i).getGESTION_BAJA(),
                arrayList.get(i).getUBI_GEOGRAFICA(),
                arrayList.get(i).getORIGEN(),
                "drawable-mdpi/cara_duda.png",
                arrayList.get(i).getCAMBIO(),
                arrayList.get(i).getESTADO()));
    }
}
