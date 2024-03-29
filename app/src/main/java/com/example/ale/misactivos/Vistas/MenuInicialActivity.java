package com.example.ale.misactivos.Vistas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.ale.misactivos.LoginServerActivity;
import com.example.ale.misactivos.R;

public class MenuInicialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.activity_menu_inicial));
        showToolbar(getResources().getString(R.string.toolbar_tittle_menuinicial),true);


    }

    public void goShowGesCruds(View view){
        Intent intent= new Intent(this,MenuCrudsActivity.class);
        startActivity(intent);

    }
    public void goShowGesServer(View view){
        Intent intent= new Intent(this, LoginServerActivity.class);
        startActivity(intent);

    }
    public void goShowGesActivos(View view){
        Intent intent= new Intent(this,GestionActivosActivity.class);
        startActivity(intent);

    }
    public void goShowContainer3(View view){
        Intent intent= new Intent(this, GestionInventarioActivity.class);
        startActivity(intent);

    }
    public void showToolbar(String tittle, boolean upButton){
        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
}
