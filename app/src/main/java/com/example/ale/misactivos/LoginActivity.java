package com.example.ale.misactivos;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.ale.misactivos.Operaciones.ConexionSqliteHelper;
import com.example.ale.misactivos.Vistas.CreateAccountActivity;
import com.example.ale.misactivos.Vistas.MenuInicialActivity;

import static com.example.ale.misactivos.Operaciones.CreaTablas.NOMBREDB;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText etUsu, etPas;
    ConexionSqliteHelper basedatos= new ConexionSqliteHelper(this,NOMBREDB,null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void goCreateAccount(View view){
        etUsu= (TextInputEditText)findViewById(R.id.username);
        etPas= (TextInputEditText)findViewById(R.id.password);
        if(etUsu.getText().toString().trim().equals("admin")&& etPas.getText().toString().trim().equals("admine")){
        Intent intent= new Intent(this,CreateAccountActivity.class);
        startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"No esta autorizado para crear cuentas",Toast.LENGTH_SHORT).show();
        }
        etUsu.setText("");
        etPas.setText("");
    }


    public void goShowMenuInicial(View view){
        etUsu= (TextInputEditText)findViewById(R.id.username);
        etPas= (TextInputEditText)findViewById(R.id.password);
        if(validalogin(etUsu.getText().toString().trim())&& validalogin(etPas.getText().toString().trim())) {
            boolean verifica = verificaUssPas(etUsu.getText().toString().trim(), etPas.getText().toString().trim());
            if (verifica) {
                Intent intent = new Intent(this, MenuInicialActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Usuario o Contraseña Incorrecto", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "Usuario o Contraseña debe tener mas de 4 digitos", Toast.LENGTH_SHORT).show();
        }
        etPas.setText("");
        etUsu.setText("");

       // etUsu.setFocusable(true);
    }

    public boolean verificaUssPas(String usu, String pas) throws SQLException {
       // Toast.makeText(getApplicationContext(),"Usuario:"+usu+"; Passwped:"+pas,Toast.LENGTH_LONG).show();
        SQLiteDatabase db=basedatos.getReadableDatabase();
         Cursor mcursor=null;
       mcursor=db.query("usuarios", new String[]{"id",
                "Nombre","Apellido","usuario","Password"},"Usuario like '"+usu+"'  " +
                "and Password like '"+pas+"'",null,null,null,null);

        if (mcursor.getCount()>0){
            db.close();
            return true;
        }else{
            db.close();
            return false;
        }
    }
    public boolean validalogin(String dato){
        return dato.length()>=4;
    }
}
