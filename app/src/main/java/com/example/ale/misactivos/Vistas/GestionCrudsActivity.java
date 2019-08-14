package com.example.ale.misactivos.Vistas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.ale.misactivos.R;

public class GestionCrudsActivity extends AppCompatActivity {
//Button opEdificio,opDpto, opOficina,opCargo, opProfesion, opEstado, OpObs, opMotivo, opTipoDoc, opActivos, opFuncionario, opTipoProfesion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_cruds);

        showToolbar("MENU CRUDS", true);
    }
    public void goShowGesEdificio(View view){
        Intent intent= new Intent(this,EdificiosActivity.class);
        startActivity(intent);

    }
    public void goShowGesDpto(View view){
        Intent intent= new Intent(this,EdificiosActivity.class);
        startActivity(intent);

    }
    public void goShowGesOficina(View view){
        Intent intent= new Intent(this,EdificiosActivity.class);
        startActivity(intent);

    }
    public void goShowGesCargo(View view){
        Intent intent= new Intent(this,EdificiosActivity.class);
        startActivity(intent);

    }
    public void goShowGesTipoProfesion(View view){
        Intent intent= new Intent(this,EdificiosActivity.class);
        startActivity(intent);

    }
    public void goShowGesEstado(View view){
        Intent intent= new Intent(this,EdificiosActivity.class);
        startActivity(intent);

    }
    public void goShowGesMotivo(View view){
        Intent intent= new Intent(this,EdificiosActivity.class);
        startActivity(intent);

    }
    public void goShowGesObs(View view){
        Intent intent= new Intent(this,EdificiosActivity.class);
        startActivity(intent);

    }
    public void goShowGesTipoDocumento(View view){
        Intent intent= new Intent(this,EdificiosActivity.class);
        startActivity(intent);

    }
    public void goShowGesFuncionario(View view){
        Intent intent= new Intent(this,EdificiosActivity.class);
        startActivity(intent);
    }
    public void goShowGesProfesion(View view){
        Intent intent= new Intent(this,EdificiosActivity.class);
        startActivity(intent);
    }
    public void goShowGesActivos(View view){
        Intent intent= new Intent(this,GestionActivosActivity.class);
        startActivity(intent);
    }


    public void showToolbar(String tittle, boolean upButton){
        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
}
