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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CargarWSfuncionario  implements Response.Listener<JSONObject>, Response.ErrorListener {
   JsonObjectRequest jsonObjectRequest;
   List<String> listItem=new ArrayList<>();
   RequestQueue request;
   Context context;
    public CargarWSfuncionario() {
        String url="http://192.168.3.223/conexoracle/wsJSONConsFuncionario.php";
        request= Volley.newRequestQueue(context);
        Toast.makeText(context,"URL:"+url,Toast.LENGTH_LONG).show();
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                this,this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(context,"No se pudo consultar Datos",Toast.LENGTH_LONG).show();
        Log.i("Error:",error.toString());

    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(context,"Datos:"+response,Toast.LENGTH_LONG).show();

        JSONArray json = response.optJSONArray("funcionarios");
        JSONObject jsonfunc=null;
        for(int i=0; i<json.length();i++){

            try {
                jsonfunc = json.getJSONObject(i);
                listItem.add(jsonfunc.getString("DOCUMENTO")+","+
                        jsonfunc.getString("NOMBRE")+","+
                        jsonfunc.getString("APELLIDOS"));
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }
}
