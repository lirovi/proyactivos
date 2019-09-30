package com.example.ale.misactivos.Vistas;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ale.misactivos.Adapter.AdaptadorEdificio;
import com.example.ale.misactivos.Adapter.AdaptadorFuncionario;
import com.example.ale.misactivos.Adapter.AdaptadorOficina;
import com.example.ale.misactivos.Adapter.AdaptadorTipoDocumento;
import com.example.ale.misactivos.Model.DaoEdificios;
import com.example.ale.misactivos.Model.DaoFuncionario;
import com.example.ale.misactivos.Model.DaoOficina;
import com.example.ale.misactivos.Model.DaoTipoDocumento;
import com.example.ale.misactivos.Model.Validar;
import com.example.ale.misactivos.Operaciones.ConexionSqliteHelper;
import com.example.ale.misactivos.R;
import com.example.ale.misactivos.entidades.Edificios;
import com.example.ale.misactivos.entidades.Funcionarios;
import com.example.ale.misactivos.entidades.Oficinas;
import com.example.ale.misactivos.entidades.Tipodocumentos;

import java.util.ArrayList;

public class CrudFuncionarioActivity extends AppCompatActivity {
    String nombreDB = "DBActivoss";
    DaoFuncionario daof;
    DaoTipoDocumento daoTd;
    AdaptadorFuncionario adapterf;
    AdaptadorTipoDocumento adapterTd;
    ArrayList<Funcionarios> listaf = new ArrayList<>();
    ArrayList<String> listaTd = new ArrayList<>();
    ListView list;
    Tipodocumentos td;
    Funcionarios fu;
    Button agregar, guardar, cancelar;
    EditText nombredit,apeuedit,apededit,diredit,teledit,cargoedit,profedit,nrodocedit,nacionaedit;
    Spinner spIdTipodoc,sexoedit;
    TextView tituloform, nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_funcionario);

        daof = new DaoFuncionario(this);

        listaf = daof.verTodos();
        adapterf = new AdaptadorFuncionario(listaf, daof, this);


        list = findViewById(R.id.listFuncionarios);
        //Log.i("Datos:", String.valueOf(listaf.size()));
        agregar = findViewById(R.id.btnAdd);

        list.setAdapter(adapterf);
       /* list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // para hacer llamada desde la lista

            }
        });*/
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dialogo de agregar

                Context context;
                listaTd = cargardatosTipoDocumentos();
                final android.app.AlertDialog.Builder dialog= new android.app.AlertDialog.Builder(CrudFuncionarioActivity.this);
                final View sView = getLayoutInflater().inflate(R.layout.dialogo_funcionario,null);


                //enlazamos variable nombredit con edit text del dialogo
                nombredit = sView.findViewById(R.id.etNombre_d);
                apeuedit  = sView.findViewById(R.id.etApellidou_d);
                apededit = sView.findViewById(R.id.etApellidod_d);
                diredit = sView.findViewById(R.id.etDireccion_d);
                cargoedit = sView.findViewById(R.id.etCargo_d);
                profedit = sView.findViewById(R.id.etProfesion_d);
                spIdTipodoc = sView.findViewById(R.id.spinnerTipoDoc);
                nrodocedit = sView.findViewById(R.id.etNroDoc_d);
                nacionaedit = sView.findViewById(R.id.etNacionalidad_d);
                sexoedit = sView.findViewById(R.id.spinnerSexo);
                teledit= sView.findViewById(R.id.etTelefono_d);

                //enlazamos variable spIdEdificio con spinner del dialogo
                //spIdTipodoc = sView.findViewById(R.id.spIdedif_d);

                ArrayAdapter<String> adapteredif = new ArrayAdapter<String>(CrudFuncionarioActivity.this,
                        android.R.layout.simple_spinner_item, listaTd);
                adapteredif.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spIdTipodoc.setAdapter(adapteredif);


                tituloform = sView.findViewById(R.id.tvTitulo_d);
                guardar = sView.findViewById(R.id.d_agregar);
                cancelar = sView.findViewById(R.id.d_cancelar);
                tituloform.setText("REGISTRO FUNCIONARIO");
                guardar.setText("Agregar");


                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            boolean b_nombre = Validar.ValidaTexto(nombredit.getText().toString().trim());
                            boolean b_apeu= Validar.ValidaTexto(apeuedit.getText().toString().trim());
                            boolean b_aped = Validar.ValidaTexto(apededit.getText().toString().trim());
                            boolean b_direc = Validar.ValidaTexto(diredit.getText().toString().trim());
                            boolean b_telef = Validar.ValidaTexto(teledit.getText().toString().trim());
                            boolean b_cargo = Validar.ValidaTexto(cargoedit.getText().toString().trim());
                            boolean b_prof = Validar.ValidaTexto(profedit.getText().toString().trim());
                            boolean b_nrodoc = Validar.ValidaTexto(nrodocedit.getText().toString().trim());
                            boolean b_nacion = Validar.ValidaTexto(nacionaedit.getText().toString().trim());
                            boolean b_tipodoc = true; //Validar.ValidaTexto(spIdTipodoc.getSelectedItem().toString().trim());
                            boolean b_sexo = true; //Validar.ValidaTexto(sexoedit.getSelectedItem().toString().trim());

                            String s_nombre = nombredit.getText().toString().trim();
                            String s_apeu= apeuedit.getText().toString().trim();
                            String s_aped = apededit.getText().toString().trim();
                            String s_direc = diredit.getText().toString().trim();
                            String s_telef = teledit.getText().toString().trim();
                            String s_cargo = cargoedit.getText().toString().trim();
                            String s_prof = profedit.getText().toString().trim();
                            String s_nrodoc = nrodocedit.getText().toString().trim();
                            String s_nacion = nacionaedit.getText().toString().trim();
                            String s_tipodoc = "1"; //spIdTipodoc.getSelectedItem().toString().trim();
                            String s_sexo = "1"; //sexoedit.getSelectedItem().toString().trim();

                            if (b_nombre && b_apeu &&b_aped && b_direc && b_cargo &&
                                    b_nacion && b_nrodoc && b_prof && b_sexo && b_telef && b_tipodoc) {

                                fu= new Funcionarios(s_nombre,s_apeu,s_aped,s_direc,s_telef,s_cargo,
                                        s_prof,s_nrodoc,s_nacion,s_tipodoc,s_sexo);

                                daof.insertar(fu);
                                listaf = daof.verTodos();
                                adapterf.notifyDataSetChanged();
                            } else {
                                Toast.makeText(getApplication(), "Verifique los datos", Toast.LENGTH_SHORT).show();
                            }

                            //mdialog.dismiss();
                        } catch (Exception e) {
                            Toast.makeText(getApplication(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //dialog.dismiss;
                        //mdialog.dismiss();

                    }
                });
                //Toast.makeText(CrudOficinaActivity.this, "Añadiendo registro: " + nombredit.getText(), Toast.LENGTH_SHORT).show();
                dialog.setView(sView);
                AlertDialog dialog1 = dialog.create();
                dialog1.show();
            }
        });

    }

    private ArrayList<String> cargardatosTipoDocumentos() {
        ArrayList<String> lista = new ArrayList<>();
        //td = new Tipodocumentos();

        try {
            ConexionSqliteHelper conex = new ConexionSqliteHelper(getApplicationContext(), nombreDB, null, 1);
            SQLiteDatabase db = conex.getReadableDatabase();
            Cursor cursor = db.rawQuery("Select id,nombredoc,sigla from tiposdocumentos", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();

                lista.add("Seleccione TipoDocumento...");
                do {

                    lista.add(cursor.getInt(0)+" - "+cursor.getString(1)+" - "+cursor.getString(2));
                    //Log.i("MYDB",""+e.getId());
                    Log.i("MYDB",""+cursor.getString(0));

                } while (cursor.moveToNext());
            }else{
                Toast.makeText(this, "Realizar registro Tipos de documentos ",Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Log.i("MYDB","Falla en el cursor");
        }
        return lista;
    }

}
