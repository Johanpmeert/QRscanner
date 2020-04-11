package com.example.qrscanner_v2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE=1;
    TextView barCodeInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        barCodeInfo = findViewById(R.id.qrCode);
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 100);
    }

    public void OnScan(View V) {
        Intent intent = new Intent(this,ScanQRcodeActivity.class);
        startActivityForResult(intent,REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode==REQUEST_CODE)&&(resultCode==Activity.RESULT_OK)) {
            String result = data.getStringExtra("QR");
            Log.i("QR-code: ",result);
            barCodeInfo.setText(result);
            // if (result!=null) qrCode.setText(result);
        }
    }

    public void OnExit(View V) {
        finish();
        System.exit(0);
    }
}
