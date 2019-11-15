package com.example.ale.misactivos.Vistas;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EditaActivosActivity extends AppCompatActivity {
    TextView teCodigo;
    EditText  teCorrelativo, teTipo, teDescripcion, teUunidad, teFeching,teValor,
            teValorResidual,teEstadoDB,teObseravacion,teGrupo,teAuxiliar,teGestionIng, tePartida,
            teGlosa, teColor, teSerie, teMarca,teModelo,tePlaca,teGestionBaja,teBaja,teUbiGeo,teOrigen;
    ImageView teImagen;
    String sCambio,sEstado;
    Button btGrabarCambios, btnTomarfoto;
    Activos datoActivo;

    public static final int REQUEST_CODE_TAKE_PHOTO = 1 /*1*/;
    private String currentPhotoPath;
    private Uri photoURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita_activos);

        relacionaUI();

        Bundle datos= getIntent().getExtras();
        if(datos!= null) {
            //datoActivo= new Lis<>();
            datoActivo = ( Activos ) datos.getSerializable("datosActivo");
            teCodigo.setText(datoActivo.getCodigo()+"");
            teCorrelativo.setText(datoActivo.getCORRELATIVO()+"");
            teTipo.setText(datoActivo.getTIPO());
            teDescripcion.setText(datoActivo.getDESCRIPCION());
        }else{
            Toast.makeText(this, "Datos no recibidos",Toast.LENGTH_SHORT).show();
        }

        btnTomarfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tomarFotoIntent();
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_TAKE_PHOTO && resultCode == RESULT_OK) {

            Bitmap bitmap;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), photoURI);
                teImagen.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            /*if (requestCode == REQUEST_CODE_TAKE_PHOTO && resultCode == RESULT_OK) {
                Bundle extras = data.getExtras(); // Aquí es null
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                mPhotoImageView.setImageBitmap(imageBitmap);
            }*/

        }
    }
}
