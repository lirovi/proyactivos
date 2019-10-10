package com.example.ale.misactivos.Operaciones;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ale.misactivos.Model.DaoEdificios;
import com.example.ale.misactivos.R;
import com.example.ale.misactivos.Vistas.ObtenerDataActivity;
import com.example.ale.misactivos.entidades.Edificios;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CargarWSEdificio implements Response.Listener<JSONObject>, Response.ErrorListener {
    JsonObjectRequest jsonObjectRequest;
    List<String> listItem=new ArrayList<>();
    RequestQueue request;
    DaoEdificios dao;
    Edificios e;
    private Context context;

    public CargarWSEdificio(Context cotxt) {
        context=cotxt;
        String url= context.getString(R.string.ipServer)+"wsJSONConsEdificio.php";
        request= Volley.newRequestQueue(context);
        Toast.makeText(context,"URL:"+url,Toast.LENGTH_LONG).show();
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                CargarWSEdificio.this,CargarWSEdificio.this);
        request.add(jsonObjectRequest);

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(context,"No se pudo consultar Edificios",Toast.LENGTH_LONG).show();
        Log.i("Error:",error.toString());

    }

    @Override
    public void onResponse(JSONObject response) {
        dao = new DaoEdificios( context);
        Toast.makeText(context,"Datos:"+response,Toast.LENGTH_LONG).show();

        JSONArray json = response.optJSONArray("edificios");
        JSONObject jsonedi=null;
        for(int i=0; i<json.length();i++){

            try {
                jsonedi = json.getJSONObject(i);
                /*listItem.add(jsonedi.getString("CODIGO")+"-"+
                        jsonedi.getString("NOMBREEDIFICIO"));*/
                e = new Edificios(jsonedi.getString("CODIGO"),jsonedi.getString("NOMBREEDIFICIO"));
                dao.insertar(e);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }
}
