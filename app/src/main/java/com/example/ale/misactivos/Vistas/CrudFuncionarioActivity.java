package com.example.ale.misactivos.Vistas;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ale.misactivos.Adapter.AdaptadorCargo;
import com.example.ale.misactivos.Adapter.AdaptadorEdificio;
import com.example.ale.misactivos.Adapter.AdaptadorFuncionario;
import com.example.ale.misactivos.Adapter.AdaptadorOficina;
import com.example.ale.misactivos.Adapter.AdaptadorProf;
import com.example.ale.misactivos.Adapter.AdaptadorTipoDocumento;
import com.example.ale.misactivos.Model.DaoCargos;
import com.example.ale.misactivos.Model.DaoEdificios;
import com.example.ale.misactivos.Model.DaoFuncionario;
import com.example.ale.misactivos.Model.DaoOficina;
import com.example.ale.misactivos.Model.DaoProfesiones;
import com.example.ale.misactivos.Model.DaoTipoDocumento;
import com.example.ale.misactivos.Model.Validar;
import com.example.ale.misactivos.Operaciones.ConexionSqliteHelper;
import com.example.ale.misactivos.R;
import com.example.ale.misactivos.entidades.Cargos;
import com.example.ale.misactivos.entidades.Edificios;
import com.example.ale.misactivos.entidades.Funcionarios;
import com.example.ale.misactivos.entidades.Oficinas;
import com.example.ale.misactivos.entidades.Profesiones;
import com.example.ale.misactivos.entidades.Tipodocumentos;

import java.util.ArrayList;

import static com.example.ale.misactivos.Operaciones.CreaTablas.NOMBREDB;

public class CrudFuncionarioActivity extends AppCompatActivity {

    DaoFuncionario daof;
    DaoCargos daoCargos;
    DaoProfesiones daoProfesiones;
    DaoTipoDocumento daotd;

    //para los spinner
    ArrayList<String> listItemProfesiones = new ArrayList<>(), listItemCargos = new ArrayList<>(), listItemTipoDoc = new ArrayList<>();
    ArrayAdapter<String> adapterCargo,adapterProf,adapterTipoDoc;

    AdaptadorFuncionario adapterf;
    ArrayList<Funcionarios> listaf = new ArrayList<>();

    ListView list;
    Tipodocumentos td;
    Funcionarios fu;

    Button agregar, guardar, cancelar;
    EditText nombredit,apeuedit,apededit,diredit,teledit,profedit,nrodocedit,nacionaedit;
    Spinner spTipodocedit, spCargoedit, spProfesionedit;
    TextView tituloform, nombre;
    String sexo;
    RadioButton rbM,rbF, rbO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_funcionario);

            daof = new DaoFuncionario(this);
            daotd = new DaoTipoDocumento(this);
            listaf = daof.verTodos();
            adapterf = new AdaptadorFuncionario(listaf, daof, this);
            list = findViewById(R.id.listFuncionarios);
            agregar = findViewById(R.id.btnAdd);

            listItemProfesiones.add("Seleccione");
            listItemCargos.add("Seleccione");
            listItemTipoDoc.add("Seleccione");
            //CargarSpiProfesiones();
            //CargarSpiTipoDoc();
            //CargarSpiCargo();

            list.setAdapter(adapterf);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // para hacer llamada desde la lista

                }
            });


            agregar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context;
                    AlertDialog.Builder mBuilder= new AlertDialog.Builder(CrudFuncionarioActivity.this);
                    View mView=getLayoutInflater().inflate(R.layout.dialogo_funcionario,null);
                    //mBuilder.setTitle("Nuevo registro");

                    nombredit=mView.findViewById(R.id.etNombref_d);
                    apeuedit  = mView.findViewById(R.id.etApellidou_d);
                    apededit = mView.findViewById(R.id.etApellidod_d);
                    diredit = mView.findViewById(R.id.etDireccionf_d);

                    nrodocedit = mView.findViewById(R.id.etNroDoc_d);
                    nacionaedit = mView.findViewById(R.id.etNacionalidad_d);
                    teledit= mView.findViewById(R.id.etTelefonof_d);

                    rbM = mView.findViewById(R.id.rbMasc);
                    rbF=mView.findViewById(R.id.rbFem);
                    rbO=mView.findViewById(R.id.rbOtro);
                    sexo=seleccionSexo(rbM,rbF,rbO);

                    //Spinners
                    spCargoedit = mView.findViewById(R.id.spCargof_d);
                    spTipodocedit = mView.findViewById(R.id.spTipoDocf_d);
                    spProfesionedit = mView.findViewById(R.id.spProfesionf_d);

                    tituloform=mView.findViewById(R.id.tvTitulo_d);

                    adapterProf= new ArrayAdapter<String>(CrudFuncionarioActivity.this,android.R.layout.simple_list_item_1, listItemProfesiones);
                    adapterProf.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spProfesionedit.setAdapter(adapterProf);

                    adapterCargo= new ArrayAdapter<String>(CrudFuncionarioActivity.this,android.R.layout.simple_list_item_1, listItemCargos);
                    adapterCargo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spCargoedit.setAdapter(adapterCargo);

                    adapterTipoDoc= new ArrayAdapter<String>(CrudFuncionarioActivity.this,android.R.layout.simple_list_item_1, listItemTipoDoc);
                    adapterTipoDoc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spTipodocedit.setAdapter(adapterTipoDoc);

                    mBuilder.setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int codProfEdit=0, codCargoedit=0,codTipoDocedit=0;

                            if(!spCargoedit.getSelectedItem().toString().equalsIgnoreCase("Seleccione")){
                                String selected[] =  (spCargoedit.getSelectedItem().toString().split("-"));
                                codCargoedit = Integer.parseInt(selected[0]);
                                Toast.makeText(getApplicationContext(), "Cargo Selecionado: "+selected[0], Toast.LENGTH_SHORT).show();
                            }
                            if(!spProfesionedit.getSelectedItem().toString().equalsIgnoreCase("Seleccione")){
                                String selected[] =  (spProfesionedit.getSelectedItem().toString().split("-"));
                                codProfEdit = Integer.parseInt(selected[0]);
                                Toast.makeText(getApplicationContext(), "Profesion Selecionada: "+selected[0], Toast.LENGTH_SHORT).show();
                            }
                            if(!spTipodocedit.getSelectedItem().toString().equalsIgnoreCase("Seleccione")){
                                String selected[] =  (spTipodocedit.getSelectedItem().toString().split("-"));
                                codTipoDocedit = Integer.parseInt(selected[1]);
                                Toast.makeText(getApplicationContext(), "TipoDoc Selecionado: "+selected[0], Toast.LENGTH_SHORT).show();
                            }
                            try{
                                    if(Validar.ValidaTexto(nombredit.getText().toString().trim())) {
                                        fu = new Funcionarios(
                                                nombredit.getText().toString(),
                                                apeuedit.getText().toString(),
                                                apededit.getText().toString(),
                                                diredit.getText().toString(),
                                                teledit.getText().toString(),
                                                ""+codCargoedit,
                                                ""+codProfEdit,
                                                ""+codTipoDocedit,
                                                nrodocedit.getText().toString(),
                                                nacionaedit.getText().toString(),
                                                sexo);


                                        daof.insertar(fu);
                                        listaf = daof.verTodos();
                                        adapterf.notifyDataSetChanged();
                                    }else{
                                        Toast.makeText(getApplication(),"Verifique los datos",Toast.LENGTH_SHORT).show();
                                    }
                                    dialog.dismiss();
                                }catch (Exception e){
                                    Toast.makeText(getApplication(),"Error",Toast.LENGTH_SHORT).show();
                                }
                                dialog.dismiss();

                        }
                    });

                    mBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    });
                    mBuilder.setView(mView);
                    AlertDialog dialog=mBuilder.create();
                    dialog.show();

                }
            });

        }



    private void CargarSpiCargo() {
        ArrayList<Cargos> c = daoCargos.verTodos();

        if (c.size() > 0) {
            for (int i = 0; i < c.size(); i++) {
                //Log.i("MyDB",c.get(i).getCodigo()+"-"+c.get(i).getNombreedificio()+"-"+c.get(i).getEstado());
                listItemCargos.add(c.get(i).getId()  + "-" + c.get(i).getNombrecargo());
            }
        } else {
            Toast.makeText(this, "No existen datos de Cargos", Toast.LENGTH_LONG).show();
        }
    }



    private void CargarSpiTipoDoc() {
        ArrayList<Tipodocumentos> td = daotd.verTodos();

        if (td.size() > 0) {
            for (int i = 0; i < td.size(); i++) {
                //Log.i("MyDB",edi.get(i).getCodigo()+"-"+edi.get(i).getNombreedificio()+"-"+edi.get(i).getEstado());
                listItemTipoDoc.add(td.get(i).getId()  + "-" + td.get(i).getNombredoc());
            }
        } else {
            Toast.makeText(this, "No existen datos de Tipo Doc.", Toast.LENGTH_LONG).show();
        }
    }

    private void CargarSpiProfesiones() {
        ArrayList<Profesiones> prof = daoProfesiones.verTodos();

        if (prof.size() > 0) {
            for (int i = 0; i < prof.size(); i++) {
                //Log.i("MyDB",edi.get(i).getCodigo()+"-"+edi.get(i).getNombreedificio()+"-"+edi.get(i).getEstado());
                listItemProfesiones.add(prof.get(i).getId()  + "-" + prof.get(i).getNombreprof());
            }
        } else {
            Toast.makeText(this, "No existen datos de Profesiones", Toast.LENGTH_LONG).show();
        }

    }
    private String seleccionSexo(RadioButton rbM, RadioButton rbF, RadioButton rbO) {

        String sex="";
        if (rbM.isChecked()){
            sex="M";
        }
        if (rbF.isChecked()){
            sex="F";
        }
        if (rbO.isChecked()) {
            sex="O";
        }

        return sex;
    }
        /*super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_funcionario);

        daof = new DaoFuncionario(this);

        listaf = daof.verTodos();
        adapterf = new AdaptadorFuncionario(listaf, daof, this);


        list = findViewById(R.id.listFuncionarios);
        //Log.i("Datos:", String.valueOf(listaf.size()));
        agregar = findViewById(R.id.btnAdd);

        list.setAdapter(adapterf);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // para hacer llamada desde la lista

            }
        });
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
        return lista;*/
    }


