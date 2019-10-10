package com.example.ale.misactivos.Vistas;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ale.misactivos.Operaciones.ConexionSqliteHelper;
import com.example.ale.misactivos.R;

import static com.example.ale.misactivos.Operaciones.CreaTablas.NOMBREDB;

public class CreateAccountActivity extends AppCompatActivity {
    private TextInputEditText nom,ape,usu,pas,pas2;
    private Button guardarUsuario;
    private String snom,sape,susu,spas,spas2;
    private Long resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        showToolbar(getResources().getString(R.string.toolbar_tittle_createAccount),true);

      guardarUsuario =  findViewById(R.id.unete);

        nom = findViewById(R.id.nombre);
        ape = findViewById(R.id.apellido);
        usu = findViewById(R.id.user);
        pas = findViewById(R.id.password_create_account);
        pas2 = findViewById(R.id.confirm_password);

        guardarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuardarUsuario(v);
            }
        });
    }

    private void GuardarUsuario(View v) {
        snom= nom.getText().toString();
        sape= ape.getText().toString();
        susu= usu.getText().toString();
        spas= pas.getText().toString();
        spas2= pas2.getText().toString();
        if(!snom.isEmpty()&& !sape.isEmpty() && !susu.isEmpty() && !spas.isEmpty() && !spas2.isEmpty()) {
            if (spas.equals(spas2)) {
                resultado = insertarReg(snom, sape, susu, spas);
                Toast.makeText(this,"Resultado:"+ resultado, Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,"Las contraseñas:"+spas +" y :"+spas2+" son diferentes, CORRIJA", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "Complete los datos faltantes", Toast.LENGTH_LONG).show();
        }
        limpiarFormUsuarios();
    }

    private void limpiarFormUsuarios() {

        nom.setText("");
        ape.setText("");
        usu.setText("");
        pas.setText("");
        pas2.setText("");
        nom.setFocusable(true);
    }

    private Long insertarReg(String nom, String ape, String usu, String pas){
        ConexionSqliteHelper admin= new ConexionSqliteHelper(this,NOMBREDB,null,1);
        SQLiteDatabase db=admin.getWritableDatabase();
        ContentValues reg= new ContentValues();
        reg.put("Nombre",nom);
        reg.put("Apellido",ape);
        reg.put("Usuario",usu);
        reg.put("Password",pas);
        Long res= db.insert("usuarios",null,reg);
        db.close();
        return res;
    }

    public void showToolbar(String tittle, boolean upButton){
        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
}
