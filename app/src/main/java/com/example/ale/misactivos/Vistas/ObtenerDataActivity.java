package com.example.ale.misactivos.Vistas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.ale.misactivos.R;

import java.util.ArrayList;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class ObtenerDataActivity extends AppCompatActivity {

   MaterialSpinner spinner, spinnerUnidad, spinnerfuncionario, spinnertipoequipo;
   List<String> listItem = new ArrayList<>();
    List<String> listItemUnidad = new ArrayList<>();
    List<String> listItemFuncionario = new ArrayList<>();
    List<String> listItemTipoEquipo = new ArrayList<>();
   ArrayAdapter<String> adapter, adapterUnidad, adapterFuncionario, adapterTipoEquipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obtener_data);
        showToolbar(getResources().getString(R.string.toolbar_tittle_obtenerDatos),true);
        initItems();

        spinner = (MaterialSpinner) findViewById(R.id.spinnerdpto);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,listItem);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != -1){
                    String selected = (spinner.getItemAtPosition(position).toString());
                    /*if(selected % 2==0){
                        spinner.setError("Este es un mensaje de error");
                    }*/
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerUnidad = (MaterialSpinner) findViewById(R.id.spinnerunidad);
        adapterUnidad = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,listItemUnidad);
        adapterUnidad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnidad.setAdapter(adapterUnidad);

        spinnerUnidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != -1){
                    String selected = (spinnerUnidad.getItemAtPosition(position).toString());
                    /*if(selected % 2==0){
                        spinner.setError("Este es un mensaje de error");
                    }*/
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerfuncionario = (MaterialSpinner) findViewById(R.id.spinnerfuncionario);
        adapterFuncionario = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,listItemFuncionario);
        adapterFuncionario.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerfuncionario.setAdapter(adapterFuncionario);

        spinnerfuncionario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != -1){
                    String selected = (spinnerfuncionario.getItemAtPosition(position).toString());
                    /*if(selected % 2==0){
                        spinner.setError("Este es un mensaje de error");
                    }*/
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnertipoequipo = (MaterialSpinner) findViewById(R.id.spinnertipoequipo);
        adapterTipoEquipo = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,listItemTipoEquipo);
        adapterTipoEquipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnertipoequipo.setAdapter(adapterTipoEquipo);

        spinnertipoequipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != -1){
                    String selected = (spinnertipoequipo.getItemAtPosition(position).toString());
                    /*if(selected % 2==0){
                        spinner.setError("Este es un mensaje de error");
                    }*/
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void initItems() {
       /* for(int i=0;i<=100;i++)
        {
            listItem.add(i);
        }*/
        listItem.add("Ejecutivo");
        listItem.add("Departamento Contabilidad");
        listItem.add("Departamento Agua Potable");
        listItem.add("Departamento Alcantarillado");
        listItem.add("Departamento Control Calidad");
        listItem.add("Departamento Comercial");
        listItem.add("Departamento Informática");


        listItemUnidad.add("Gerencia General");
        listItemUnidad.add("Gerencia Administrativa");
        listItemUnidad.add("Gerencia Técnica");
        listItemUnidad.add("Jefatura Contable");
        listItemUnidad.add("Jefatura Agua Potable");
        listItemUnidad.add("Jefatura Comercial");
        listItemUnidad.add("Jefatura Informática");
        listItemUnidad.add("Encargado Informática");
        listItemUnidad.add("Encargado Activos Fijos");
        listItemUnidad.add("Encargado Facturacion");
        listItemUnidad.add("Encargado Micromedicion");
        listItemUnidad.add("Encargado Consumo Medido");
        listItemUnidad.add("Encargado Almacenes");

        listItemFuncionario.add("Limber Rojas");
        listItemFuncionario.add("José Cortéz");
        listItemFuncionario.add("Waldo Serrudo");
        listItemFuncionario.add("Fernando Chacón");
        listItemFuncionario.add("Consuelo Vallejos");
        listItemFuncionario.add("Franz Heredia");
        listItemFuncionario.add("Mauro Claros");
        listItemFuncionario.add("Nelson Salgueiro");
        listItemFuncionario.add("Sergio Aramayo");
        listItemFuncionario.add("Claudia Cuellar");
        listItemFuncionario.add("David Dolz");
        listItemFuncionario.add("Faustino Velásquez");
        listItemFuncionario.add("Rómulo Grimaldo");
        listItemFuncionario.add("Marcelo Rossel");

        listItemTipoEquipo.add("Computadora");
        listItemTipoEquipo.add("Bomba sumergible");
        listItemTipoEquipo.add("Impresora");
        listItemTipoEquipo.add("Mueble");
        listItemTipoEquipo.add("Camioneta");
        listItemTipoEquipo.add("Moto");
        listItemTipoEquipo.add("Maquinaria");

    }
    public void showToolbar(String tittle, boolean upButton){
        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
}
