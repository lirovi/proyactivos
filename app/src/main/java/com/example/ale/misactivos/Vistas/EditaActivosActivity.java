package com.example.ale.misactivos.Vistas;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ale.misactivos.Model.DaoActivos;
import com.example.ale.misactivos.R;
import com.example.ale.misactivos.entidades.Activos;

import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.text.SimpleDateFormat;
import java.util.Date;


public class EditaActivosActivity extends AppCompatActivity {
    TextView teCodigo,tvEdif,tvFun;
    EditText  teCorrelativo, teTipo, teDescripcion, teUunidad, teFeching,teValor,
            teValorResidual,teEstadoDB,teEfisico, teObservacion,teGrupo,teAuxiliar,teGestionIng, tePartida,
            teGlosa, teColor, teSerie, teMarca,teModelo,tePlaca,teGestionBaja,teBaja,teUbiGeo,teOrigen;
    ImageView teImagen;
    String sCambio="N",sEstado, archivo,vDes=null, vEfisico=null,vObs=null,vGlosa=null,vSerie=null,vMarca=null,vModelo=null,vPlaca=null,edif,func;
    Button  btnTomarfoto;
    Activos datoActivo;
    DaoActivos dao;

    public static final int REQUEST_CODE_TAKE_PHOTO = 0 /*1*/;
    private String currentPhotoPath;
    private Uri photoURI;
    ImageButton btnGuarda, btnEdita,  btnCancela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita_activos);


        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 226);

        }
        relacionaUI();

        tvEdif= findViewById(R.id.tvEd);
        tvFun =findViewById(R.id.tvFn);
        dao= new DaoActivos(this);

        Bundle datos= getIntent().getExtras();
        edif= datos.getString("vedificio");
        func= datos.getString("vfuncionario");
        tvFun.setText(func);
        tvEdif.setText(edif);

        if(datos!= null) {

            datoActivo = ( Activos ) datos.getSerializable("datosActivo");


            try {
                teCodigo.setText(datoActivo.getCodigo() + "");
                teCorrelativo.setText(datoActivo.getCORRELATIVO() + "");
                teTipo.setText(datoActivo.getTIPO());
                teEfisico.setText(datoActivo.getESTADOFISICO());
                teDescripcion.setText(datoActivo.getDESCRIPCION());
                teUunidad.setText(datoActivo.getUNIDAD());
                teFeching.setText(datoActivo.getFECHA_INGRESO());
                teValor.setText(datoActivo.getVALOR() + "");
                teValorResidual.setText(datoActivo.getVALOR_RESIDUAL() + "");
                teEstadoDB.setText(datoActivo.getESTADO_BD());
                teObservacion.setText(datoActivo.getOBSERVACION());
                teGrupo.setText(datoActivo.getGRUPO());
                teAuxiliar.setText(datoActivo.getAUXILIAR());
                teGestionIng.setText(datoActivo.getGESTION_INGRESO() + "");
                tePartida.setText(datoActivo.getPARTIDA() + "");
                teGlosa.setText(datoActivo.getGLOSA());
                teColor.setText(datoActivo.getCOLOR());
                teSerie.setText(datoActivo.getSERIE());
                teMarca.setText(datoActivo.getMARCA());
                teModelo.setText(datoActivo.getMODELO());
                tePlaca.setText(datoActivo.getPLACA());
                teGestionBaja.setText(datoActivo.getGESTION_BAJA() + "");
                teBaja.setText(datoActivo.getBAJA());
                teUbiGeo.setText(datoActivo.getUBI_GEOGRAFICA());
                teOrigen.setText(datoActivo.getORIGEN());
                DesactivarEditext();
            }catch (Exception e){
                Toast.makeText(this, "Error: "+e.getMessage(),Toast.LENGTH_SHORT).show();
                Log.i("MyDB","Erro: "+e.getMessage());
            }
        }else{
            Toast.makeText(this, "Datos no recibidos",Toast.LENGTH_SHORT).show();
        }

        btnEdita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teDescripcion.setEnabled(true);
                teDescripcion.setBackgroundColor(Color.parseColor("#c7f0db"));
                teEfisico.setEnabled(true);
                teEfisico.setBackgroundColor(Color.parseColor("#c7f0db"));
                teObservacion.setEnabled(true);
                teObservacion.setBackgroundColor(Color.parseColor("#c7f0db"));
                teGlosa.setEnabled(true);
                teGlosa.setBackgroundColor(Color.parseColor("#c7f0db"));
                teSerie.setEnabled(true);
                teSerie.setBackgroundColor(Color.parseColor("#c7f0db"));
                teMarca.setEnabled(true);
                teMarca.setBackgroundColor(Color.parseColor("#c7f0db"));
                teModelo.setEnabled(true);
                teModelo.setBackgroundColor(Color.parseColor("#c7f0db"));
                tePlaca.setEnabled(true);
                tePlaca.setBackgroundColor(Color.parseColor("#c7f0db"));
                vDes=teDescripcion.getText().toString();
                vEfisico=teEfisico.getText().toString();
                vObs= teObservacion.getText().toString();
                vGlosa=teGlosa.getText().toString();
                vSerie=teSerie.getText().toString();
                vMarca=teMarca.getText().toString();
                vModelo=teModelo.getText().toString();
                vPlaca=tePlaca.getText().toString();

            }
        });



        btnCancela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnTomarfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try
                {
                    //Creamos el Intent para llamar a la Camara
                    Time now = new Time();
                    now.setToNow();
                    Intent icamara = new Intent(
                            android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    //Creamos una carpeta en la memeria del terminal
                    /*archivo = Environment.getExternalStorageDirectory() + "/external_sd/Fotos/"+ "ID" + now.format2445() +".jpg";
                    file = new File(archivo);

                    //Finalmente ejecuto la actividad cámara pasándole dicho archivo como parámetro

                    Uri outputFileUri = Uri.fromFile(file);
                    icamara.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);*/
                    archivo = Environment.getExternalStorageDirectory() +  "/FotoActivos/"+ "ID" + now.format2445() +".jpg";
                    //intent.putExtra(MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(new File(archivo));
                    //imageUri = Uri.fromFile(photo);
                    Log.i("MyDB",archivo);
                    //Validación de acuerdo al OS.
                    if (Build.VERSION.SDK_INT >=  Build.VERSION_CODES.N) {
                        photoURI = Uri.parse(archivo);
                    } else{
                        photoURI = Uri.fromFile(new File(archivo));
                    }
                    startActivityForResult(icamara,1);
                }catch(Exception e)
                {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean res=false;
                Verificacambios();
                if (sCambio.equals("S")) {
                    datoActivo.setCodigo(teCodigo.getText().toString());
                   /* datoActivo.setCORRELATIVO(Integer.parseInt(teCorrelativo.getText().toString()));
                    datoActivo.setTIPO(teTipo.getText().toString());*/
                    datoActivo.setDESCRIPCION(teDescripcion.getText().toString());
                    /*datoActivo.setUNIDAD(teUunidad.getText().toString());
                    datoActivo.setFECHA_INGRESO(teFeching.getText().toString());
                    datoActivo.setVALOR(teValor.getText().toString());
                    datoActivo.setVALOR_RESIDUAL(teValorResidual.getText().toString());
                    datoActivo.setESTADO_BD(teEstadoDB.getText().toString());*/
                    datoActivo.setESTADOFISICO(teEfisico.getText().toString().toUpperCase());
                    datoActivo.setOBSERVACION(teObservacion.getText().toString().toUpperCase());
                    /*datoActivo.setGRUPO(teGrupo.getText().toString());
                    datoActivo.setAUXILIAR(teAuxiliar.getText().toString());
                    datoActivo.setGESTION_INGRESO(Integer.parseInt(teGestionIng.getText().toString()));
                    datoActivo.setPARTIDA(tePartida.getText().toString());*/
                    datoActivo.setGLOSA(teGlosa.getText().toString().toUpperCase());
                    datoActivo.setCOLOR(teColor.getText().toString().toUpperCase());
                    datoActivo.setSERIE(teSerie.getText().toString().toUpperCase());
                    datoActivo.setMARCA(teMarca.getText().toString().toUpperCase());
                    datoActivo.setMODELO(teModelo.getText().toString().toUpperCase());
                    datoActivo.setPLACA(tePlaca.getText().toString().toUpperCase());
                    /*datoActivo.setGESTION_BAJA(Integer.parseInt(teGestionBaja.getText().toString()));
                    datoActivo.setBAJA(teBaja.getText().toString());
                    datoActivo.setUBI_GEOGRAFICA(teUbiGeo.getText().toString());
                    datoActivo.setORIGEN(teOrigen.getText().toString());

                    datoActivo.setRUTA_IMAGEN("ruta");
                    datoActivo.setESTADO("A");*/
                    datoActivo.setCAMBIO("S");

                    res = dao.editar(datoActivo);
                }
                if (res) Toast.makeText(getApplicationContext(),"Se registró las modificaciones",Toast.LENGTH_SHORT).show();
                else Toast.makeText(getApplicationContext(),"No se registraron modificaciones",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void Verificacambios() {
        if(vEfisico.equals(teEfisico.getText().toString())&&
                vDes.equals(teDescripcion.getText().toString())&&
                vObs.equals(teObservacion.getText().toString())&&
                vGlosa.equals(teGlosa.getText().toString())&&
                vSerie.equals(teSerie.getText().toString())&&
                vMarca.equals(teMarca.getText().toString())&&
                vModelo.equals(teModelo.getText().toString())&&
                vPlaca.equals(tePlaca.getText().toString())){
                sCambio="N";
        }else sCambio="S";

    }

    private void DesactivarEditext() {
        teCodigo.setEnabled(false);
        teCorrelativo.setEnabled(false);
        teTipo.setEnabled(false);
        teDescripcion.setEnabled(false);
        teEfisico.setEnabled(false);
        teUunidad.setEnabled(false);
        teFeching.setEnabled(false);
        teValor.setEnabled(false);
        teValorResidual.setEnabled(false);
        teEstadoDB.setEnabled(false);
        teObservacion.setEnabled(false);
        teGrupo.setEnabled(false);
        teAuxiliar.setEnabled(false);
        teGestionIng.setEnabled(false);
        tePartida.setEnabled(false);
        teGlosa.setEnabled(false);
        teColor.setEnabled(false);
        teSerie.setEnabled(false);
        teMarca.setEnabled(false);
        teModelo.setEnabled(false);
        tePlaca.setEnabled(false);
        teGestionBaja.setEnabled(false);
        teBaja.setEnabled(false);
        teUbiGeo.setEnabled(false);
        teOrigen.setEnabled(false);
    }

    private void relacionaUI() {
        teCodigo= findViewById(R.id.tvCodigo_edit);
        teCorrelativo= findViewById(R.id.etCorrelativo_edit);
        teTipo= findViewById(R.id.etTipoactivo_edit);
        teDescripcion= findViewById(R.id.etDescribe_activo_edit);
        teEfisico=findViewById(R.id.etEstado_fisico_edit);
        teUunidad= findViewById(R.id.etDescribe_unidad_edit);
        teFeching= findViewById(R.id.etFeching_edit);
        teValor= findViewById(R.id.etValor_edit);
        teValorResidual= findViewById(R.id.etValor_residual_edit);
        teEstadoDB= findViewById(R.id.etEstado_bd_edit);
        teObservacion= findViewById(R.id.etObservaciones_edit);
        teGrupo= findViewById(R.id.etGrupo_activo_edit);
        teAuxiliar= findViewById(R.id.etAuxiliar_grupo_edit);
        teGestionIng= findViewById(R.id.etGestion_edit);
        tePartida= findViewById(R.id.etPartida_edit);
        teGlosa= findViewById(R.id.etGlosa_edit);
        teColor= findViewById(R.id.etColor_activo_edit);
        teSerie= findViewById(R.id.etSerie_edit);
        teMarca= findViewById(R.id.etMarca_edit);
        teModelo= findViewById(R.id.etModelo_edit);
        tePlaca= findViewById(R.id.etPlaca_edit);
        teGestionBaja= findViewById(R.id.etGesbaja_edit);
        teBaja= findViewById(R.id.etBaja_edit);
        teUbiGeo= findViewById(R.id.etUbigeografica_edit);
        teOrigen= findViewById(R.id.etOrigen_edit);
        teImagen= findViewById(R.id.imagenId_edit);
        sCambio="N"; sEstado="A";
        btnTomarfoto= findViewById(R.id.btnTomafoto_edit);
        btnGuarda= findViewById(R.id.btGuarda);
        btnEdita= findViewById(R.id.btEdita);
        btnCancela= findViewById(R.id.btCancela);
    }
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "AF_" + timeStamp + "";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile( imageFileName, ".png",  storageDir );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void tomarFotoIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {

                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, "FotoActivo");
                values.put(MediaStore.Images.Media.DESCRIPTION, "Foto Tomada en " + System.currentTimeMillis());

                photoURI = getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

                //Uri photoURI = FileProvider.getUriForFile(AddActivity.this, "com.example.android.fileprovider", photoFile);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                Log.i("MyDB", photoURI.toString());
                startActivityForResult(takePictureIntent, REQUEST_CODE_TAKE_PHOTO);
            }
        }
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Comprobamos que la foto se ha realizado
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            //Creamos un bitmap con la imagen recientemente
            //almacenada en la memoria
            Bitmap bMap = ( Bitmap ) data.getExtras().get("data");
           /* Bitmap bMap = BitmapFactory.decodeFile(
                    Environment.getExternalStorageDirectory() +
                            "/FotoActivos/" + "foto.jpg");*/
            //Añadimos el bitmap al imageView para
            //mostrarlo por pantalla
            teImagen.setImageBitmap(bMap);
        }
    }
}
