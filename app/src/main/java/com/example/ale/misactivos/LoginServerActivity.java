package com.example.ale.misactivos;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ale.misactivos.Model.DaoCustodias;
import com.example.ale.misactivos.Operaciones.ConexionSqliteHelper;
import com.example.ale.misactivos.Vistas.CreateAccountActivity;
import com.example.ale.misactivos.Vistas.GestionServidorActivity;
import com.example.ale.misactivos.Vistas.MenuInicialActivity;
import com.example.ale.misactivos.entidades.Custodias;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.ale.misactivos.Operaciones.CreaTablas.NOMBREDB;

public class LoginServerActivity extends AppCompatActivity  {

    TextInputEditText etUsu, etPas;
    Button btVerifica;
    JsonObjectRequest jsonObjectRequest;
    RequestQueue queue=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_server);

        etUsu= findViewById(R.id.username);
        etPas= findViewById(R.id.password);
        btVerifica= findViewById(R.id.btVerificalogin);

        final Context context;
        queue= Volley.newRequestQueue(this);

        btVerifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validalogin(etUsu.getText().toString().trim()) && validalogin(etPas.getText().toString().trim())) {
                    String url = getString(R.string.ipServer) + "wsLoginServer.php?nuser=" +
                            etUsu.getText().toString().trim() + "&npass=" + etPas.getText().toString().trim();

                    jsonObjectRequest = new JsonObjectRequest
                            (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {

                                    Toast.makeText(getApplicationContext(), "Dato: "+response.optInt("verifica"), Toast.LENGTH_LONG).show();
                                    Log.i("MyDB","Dato: "+response.optInt("verifica"));

                                        if (response.optInt("verifica")==1) {
                                            Intent intent = new Intent(LoginServerActivity.this, GestionServidorActivity.class);
                                            startActivity(intent);
                                        } else
                                            Toast.makeText(getApplicationContext(), "Usuario y Contraseña Incorrectos...", Toast.LENGTH_LONG).show();
                                }
                            }, new Response.ErrorListener() {

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // TODO: Handle error
                                    Toast.makeText(getApplicationContext(), "NO es posible la conexion a la BD: " + error.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                    queue.add(jsonObjectRequest);
                } else {
                    Toast.makeText(getApplicationContext(), "Usuario o Contraseña debe tener mas de 4 digitos", Toast.LENGTH_SHORT).show();
                }
                etPas.setText("");
                etUsu.setText("");
        }
        });
    }
    public boolean validalogin(String dato){
        return dato.length()>=4;
    }
}
