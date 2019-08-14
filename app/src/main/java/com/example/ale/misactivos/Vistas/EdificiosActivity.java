package com.example.ale.misactivos.Vistas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import com.example.ale.misactivos.Operaciones.ControladorEdificios;
import com.example.ale.misactivos.R;

public class EdificiosActivity extends AppCompatActivity {

    Button botoncrearedificio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edificios);

        botoncrearedificio = findViewById(R.id.buttonCrearEdificio);
        botoncrearedificio.setOnClickListener( new onClickListenerCreaEdificio());

        showToolbar("CRUD DE EDIFICIOS",true);

        contadorregistros();
    }
    public void showToolbar(String tittle, boolean upButton){
        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    public void contadorregistros(){

        int contador=new ControladorEdificios(this).contador();
        TextView contadoredificio = findViewById(R.id.contador);
        contadoredificio.setText(contador + " Resultados encontrados");
    }
}
