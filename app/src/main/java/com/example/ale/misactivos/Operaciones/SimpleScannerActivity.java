package com.example.ale.misactivos.Operaciones;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;


import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

    public class SimpleScannerActivity extends Activity implements ZBarScannerView.ResultHandler {
        private ZBarScannerView mScannerView;

        @Override
        public void onCreate(Bundle state) {
            super.onCreate(state);
            mScannerView = new ZBarScannerView(this);
            setContentView(mScannerView);
        }

        @Override
        public void onResume() {
            super.onResume();
            mScannerView.setResultHandler(this);
            mScannerView.startCamera();
        }

        @Override
        public void onPause() {
            super.onPause();
            mScannerView.stopCamera();
        }

        @Override
        public void handleResult(Result rawResult) {
           Log.v("MyDB",rawResult.getContents());
           Log.v("MyDB",rawResult.getBarcodeFormat().getName());
        }
    }