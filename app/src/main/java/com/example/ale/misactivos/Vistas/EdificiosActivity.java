package com.example.ale.misactivos.Vistas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ale.misactivos.Operaciones.ControladorEdificios;
import com.example.ale.misactivos.R;
import com.example.ale.misactivos.entidades.Edificios;

import java.util.List;

public class EdificiosActivity extends AppCompatActivity {

    Button botoncrearedificio;
    LinearLayout lytextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edificios);

        botoncrearedificio = findViewById(R.id.buttonCrearEdificio);
        botoncrearedificio.setOnClickListener( new onClickListenerCreaEdificio());

        lytextView = findViewById(R.id.linearlayout);
        lytextView.setOnClickListener(new onClicEditaEdificio());

        showToolbar("CRUD DE EDIFICIOS",true);

        contadorEdificios();
        leerEdificio();


    }
    public void showToolbar(String tittle, boolean upButton){
        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    public void contadorEdificios(){

        int contador=new ControladorEdificios(this).contador();
        TextView contadoredificio = (TextView) findViewById(R.id.contadored);
        contadoredificio.setText(contador + " Resultados encontrados");
    }

    public void leerEdificio(){
        LinearLayout linearLayout = findViewById(R.id.linearlayout);
        linearLayout.removeAllViews();

        List<Edificios> edificio = new ControladorEdificios(this).leer();

        if(edificio.size()>0){
            for(Edificios obj:edificio){
                int id= obj.getId();
                String nombre= obj.getNombreedificio();

                TextView textView= new TextView(this);
                textView.setPadding(0,7,0,7);
                textView.setText(nombre);
                textView.setTag(Integer.toString(id));

                linearLayout.addView(textView);


            }
        }else{
                TextView notexto = new TextView(this);
                notexto.setText("No hay registros de Edificios ");
                linearLayout.addView(notexto);
        }

    }


}
