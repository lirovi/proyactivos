package com.example.ale.misactivos.Vistas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.ale.misactivos.R;

public class GestionServidorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_servidor);
        showToolbar(getResources().getString(R.string.toolbar_tittle_gestionservidor),true);
    }
    public void goShowGetDatServer(View view){
        Intent intent= new Intent(this,ObtenerDataActivity.class);
        startActivity(intent);
    }


    public void goShowUpdateServer(View view){
        Intent intent= new Intent(this,CreateAccountActivity.class);
        startActivity(intent);
    }
    public void showToolbar(String tittle, boolean upButton){
        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
}
