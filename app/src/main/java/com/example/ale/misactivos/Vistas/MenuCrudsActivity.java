package com.example.ale.misactivos.Vistas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.example.ale.misactivos.R;

import java.util.ArrayList;
import java.util.List;

public class MenuCrudsActivity extends AppCompatActivity {
    private List<Integer> name;
    private GridView gridView;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cruds);
        showToolbar("Gestión CRUDs", true);

    gridView=findViewById(R.id.gvNombres);
    final List<Integer> nombres= new ArrayList<>();
        nombres.add(R.drawable.ico_edificio);
        nombres.add(R.drawable.ico_dpto);
        nombres.add(R.drawable.ico_oficina);
        nombres.add(R.drawable.ico_cargo);
        nombres.add(R.drawable.ico_tipodoc);
        nombres.add(R.drawable.ico_funcionario);
        nombres.add(R.drawable.ico_profesion);
        nombres.add(R.drawable.ico_obs);



        ArrayAdapter<Integer> adaptador = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, nombres);
        gridView.setAdapter(adaptador);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
            //Toast.makeText(MenuCrudsActivity.this, id + "- Hiciste click en "+ nombres.get(pos),Toast.LENGTH_SHORT).show();
            switch(( int ) id){
                case 0:
                    intent = new Intent(MenuCrudsActivity.this, EdificiosActivity.class );
                    startActivity(intent);
                    break;
                case 1:
                    intent = new Intent(MenuCrudsActivity.this, EdificiosActivity.class );
                    startActivity(intent);
                    break;
                case 2:
                    intent = new Intent(MenuCrudsActivity.this, EdificiosActivity.class );
                    startActivity(intent);
                    break;
                case 3:
                    intent = new Intent(MenuCrudsActivity.this, EdificiosActivity.class );
                    startActivity(intent);
                    break;
                case 4:
                     intent = new Intent(MenuCrudsActivity.this, EdificiosActivity.class );
                    startActivity(intent);
                    break;
                case 5:
                    intent = new Intent(MenuCrudsActivity.this, EdificiosActivity.class );
                    startActivity(intent);
                    break;
                case 6:
                     intent = new Intent(MenuCrudsActivity.this, EdificiosActivity.class );
                    startActivity(intent);
                case 7:
                    intent = new Intent(MenuCrudsActivity.this, EdificiosActivity.class );
                    startActivity(intent);
                    break;
                    default:
                        Toast.makeText(MenuCrudsActivity.this, "Actividad no encontrada",Toast.LENGTH_SHORT).show();
                        break;
            }
        }
    });

    MiMenuAdaptador miadaptador = new MiMenuAdaptador(this, R.layout.cardview_button, nombres);
        gridView.setAdapter(miadaptador);
    }
    public void showToolbar(String tittle, boolean upButton){
        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
}
