package com.example.ale.misactivos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.ale.misactivos.Vistas.ContainerActivity;
import com.example.ale.misactivos.Vistas.CreateAccountActivity;
import com.example.ale.misactivos.Vistas.MenuInicialActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void goCreateAccount(View view){
        Intent intent= new Intent(this,CreateAccountActivity.class);
        startActivity(intent);
    }



    public void goShowMenuInicial(View view){
        Intent intent= new Intent(this,MenuInicialActivity.class);
        startActivity(intent);
    }
}
