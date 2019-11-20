package com.example.ale.misactivos.Vistas;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ale.misactivos.R;
import com.example.ale.misactivos.entidades.Activos;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class EditaActivosActivity extends AppCompatActivity {
    TextView teCodigo;
    EditText  teCorrelativo, teTipo, teDescripcion, teUunidad, teFeching,teValor,
            teValorResidual,teEstadoDB,teObseravacion,teGrupo,teAuxiliar,teGestionIng, tePartida,
            teGlosa, teColor, teSerie, teMarca,teModelo,tePlaca,teGestionBaja,teBaja,teUbiGeo,teOrigen;
    ImageView teImagen;
    String sCambio,sEstado, archivo;
    Button btGrabarCambios, btnTomarfoto;
    Activos datoActivo;

    public static final int REQUEST_CODE_TAKE_PHOTO = 0 /*1*/;
    private String currentPhotoPath;
    private Uri photoURI;

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

        Bundle datos= getIntent().getExtras();
        if(datos!= null) {


            //datoActivo= new Lis<>();
            datoActivo = ( Activos ) datos.getSerializable("datosActivo");

            /*Log.i("MyDB",datoActivo.getCodigo()+"");
            Log.i("MyDB",datoActivo.getCORRELATIVO()+"");
            Log.i("MyDB",datoActivo.getTIPO());
            Log.i("MyDB",datoActivo.getDESCRIPCION());

           Log.i("MyDB",datoActivo.getUNIDAD());
            Log.i("MyDB",datoActivo.getFECHA_INGRESO());
           Log.i("MyDB",datoActivo.getVALOR());

            Log.i("MyDB",datoActivo.getVALOR_RESIDUAL());
            Log.i("MyDB",datoActivo.getESTADOFISICO());
            Log.i("MyDB",datoActivo.getESTADO_BD());
            Log.i("MyDB",datoActivo.getOBSERVACION());
            Log.i("MyDB",datoActivo.getGRUPO());
            Log.i("MyDB",datoActivo.getAUXILIAR());
            Log.i("MyDB",datoActivo.getGESTION_INGRESO()+"");
            Log.i("MyDB",datoActivo.getPARTIDA());
            Log.i("MyDB",datoActivo.getGLOSA());
            Log.i("MyDB",datoActivo.getCOLOR());
            Log.i("MyDB",datoActivo.getSERIE());
            Log.i("MyDB",datoActivo.getMARCA());
            Log.i("MyDB",datoActivo.getMODELO());
            Log.i("MyDB",datoActivo.getPLACA());
            Log.i("MyDB",datoActivo.getGESTION_BAJA()+"");
            Log.i("MyDB",datoActivo.getBAJA());
            Log.i("MyDB",datoActivo.getUBI_GEOGRAFICA());
            Log.i("MyDB",datoActivo.getORIGEN());*/

            teCodigo.setText(datoActivo.getCodigo()+"");
            teCorrelativo.setText(datoActivo.getCORRELATIVO()+"");
            teTipo.setText(datoActivo.getTIPO());
            teDescripcion.setText(datoActivo.getDESCRIPCION());
            teUunidad.setText(datoActivo.getUNIDAD());
            teFeching.setText(datoActivo.getFECHA_INGRESO());
            teValor.setText(datoActivo.getVALOR()+"");
            teValorResidual.setText(datoActivo.getVALOR_RESIDUAL()+"");
            teEstadoDB.setText(datoActivo.getESTADO_BD());
            teObseravacion.setText(datoActivo.getOBSERVACION());
            teGrupo.setText(datoActivo.getGRUPO());
            teAuxiliar.setText(datoActivo.getAUXILIAR());
            teGestionIng.setText(datoActivo.getGESTION_INGRESO()+"");
            tePartida.setText(datoActivo.getPARTIDA()+"");
            teGlosa.setText(datoActivo.getGLOSA());
            teColor.setText(datoActivo.getCOLOR());
            teSerie.setText(datoActivo.getSERIE());
            teMarca.setText(datoActivo.getMARCA());
            teModelo.setText(datoActivo.getMODELO());
            tePlaca.setText(datoActivo.getPLACA());
            teGestionBaja.setText(datoActivo.getGESTION_BAJA()+"");
            teBaja.setText(datoActivo.getBAJA());
            teUbiGeo.setText(datoActivo.getUBI_GEOGRAFICA());
            teOrigen.setText(datoActivo.getORIGEN());
        }else{
            Toast.makeText(this, "Datos no recibidos",Toast.LENGTH_SHORT).show();
        }

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

        btGrabarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //teCorrelativo.setText(datoActivo.getCORRELATIVO()+"");
                datoActivo.setTIPO(teTipo.getText().toString());
                datoActivo.setDESCRIPCION(teDescripcion.getText().toString());
                datoActivo.setUNIDAD(teUunidad.getText().toString());
                teFeching.setText(datoActivo.getFECHA_INGRESO());
                teValor.setText(datoActivo.getVALOR()+"");
                teValorResidual.setText(datoActivo.getVALOR_RESIDUAL()+"");
                teEstadoDB.setText(datoActivo.getESTADO_BD());
                teObseravacion.setText(datoActivo.getOBSERVACION());
                teGrupo.setText(datoActivo.getGRUPO());
                teAuxiliar.setText(datoActivo.getAUXILIAR());
                teGestionIng.setText(datoActivo.getGESTION_INGRESO()+"");
                tePartida.setText(datoActivo.getPARTIDA()+"");
                teGlosa.setText(datoActivo.getGLOSA());
                teColor.setText(datoActivo.getCOLOR());
                teSerie.setText(datoActivo.getSERIE());
                teMarca.setText(datoActivo.getMARCA());
                teModelo.setText(datoActivo.getMODELO());
                tePlaca.setText(datoActivo.getPLACA());
                teGestionBaja.setText(datoActivo.getGESTION_BAJA()+"");
                teBaja.setText(datoActivo.getBAJA());
                teUbiGeo.setText(datoActivo.getUBI_GEOGRAFICA());
                teOrigen.setText(datoActivo.getORIGEN());
            }
        });
    }

    private void relacionaUI() {
        teCodigo= findViewById(R.id.tvCodigo_edit);
        teCorrelativo= findViewById(R.id.etCorrelativo_edit);
        teTipo= findViewById(R.id.etTipoactivo_edit);
        teDescripcion= findViewById(R.id.etDescribe_activo_edit);
        teUunidad= findViewById(R.id.etDescribe_unidad_edit);
        teFeching= findViewById(R.id.etFeching_edit);
        teValor= findViewById(R.id.etValor_edit);
        teValorResidual= findViewById(R.id.etValor_residual_edit);
        teEstadoDB= findViewById(R.id.etEstado_bd_edit);
        teObseravacion= findViewById(R.id.etObservaciones_edit);
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
        btGrabarCambios= findViewById(R.id.btnGuardaActivoEdit);
        btnTomarfoto= findViewById(R.id.btnTomafoto_edit);
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
