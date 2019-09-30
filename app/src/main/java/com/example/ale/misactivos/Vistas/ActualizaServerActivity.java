package com.example.ale.misactivos.Vistas;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.ale.misactivos.R;

public class ActualizaServerActivity extends AppCompatActivity {

    private static String TAG = "MainActivity ";
    Button btnDescarga;
    private ProgressDialog progress;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualiza_server);
        btnDescarga = (Button) findViewById(R.id.btnDescarga);
    }

    public void descargar(View view){
        progress=new ProgressDialog(this);
        progress.setMessage("Cargando datos....");
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // progress.setIndeterminate(true);
        progress.setProgress(0);
        progress.show();

        final int totalProgressTime = 100;
        final Thread t = new Thread() {
            @Override
            public void run() {
                int jumpTime = 0;

                while(jumpTime < totalProgressTime) {
                    try {
                        jumpTime += 5;
                        progress.setProgress(jumpTime);
                        sleep(200);
                    }
                    catch (InterruptedException e) {
                        Log.e(TAG, e.getMessage());
                    }
                }
                progress.setMessage("Completado..., toque la pantalla");
            }
        };
        t.start();
    }
}
