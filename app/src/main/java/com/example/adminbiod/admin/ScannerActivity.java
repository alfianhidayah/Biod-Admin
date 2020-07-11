package com.example.adminbiod.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.adminbiod.R;
import com.example.adminbiod.api.RetrofitClient;
import com.example.adminbiod.model.model_kreditor.DataKreditor;
import com.example.adminbiod.model.model_kreditor.ResponseScan;
import com.example.adminbiod.storage.SharedPrefManager;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScannerActivity extends AppCompatActivity {

    CodeScanner codeScanner;
    CodeScannerView scannerView;
    Button btnRescann, resultData;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        scannerView =  findViewById(R.id.scannerView);
        resultData = findViewById(R.id.resultData);
        btnRescann = findViewById(R.id.btnRescann);

        codeScanner = new CodeScanner(this, scannerView);

        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        resultData.setText(result.getText());

                        btnRescann.setVisibility(View.VISIBLE);
                        resultData.setVisibility(View.VISIBLE);
                        resultData.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //simpan result ke SharedPrefManager
                                String id = result.getText();

                                progressDialog = new ProgressDialog(ScannerActivity.this);
                                progressDialog.setMessage("Loading..");
                                progressDialog.setCancelable(false);
                                progressDialog.show();

                                Call<ResponseScan> call = RetrofitClient
                                        .getInstance().getApi().scanning(id);

                                call.enqueue(new Callback<ResponseScan>() {
                                    @Override
                                    public void onResponse(Call<ResponseScan> call, Response<ResponseScan> response) {
                                        ResponseScan responseScan = response.body();
                                        progressDialog.hide();
                                        if (response.body().getStatus()){
                                            SharedPrefManager.getInstance(ScannerActivity.this)
                                                    .saveKreditor(response.body().getKreditor());
                                            SharedPrefManager.getInstance(ScannerActivity.this)
                                                    .saveTransaksi(response.body().getTransaksi());

                                            Intent intent = new Intent(ScannerActivity.this, InputActivity.class);
                                            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(ScannerActivity.this, responseScan.getMessage(), Toast.LENGTH_LONG). show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseScan> call, Throwable t) {
                                        progressDialog.hide();
                                        Toast.makeText(ScannerActivity.this, "ERROR ACCESS", Toast.LENGTH_LONG). show();
                                    }

                                });


                            }
                        });
                    }
                });
            }
        });

        btnRescann.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codeScanner.startPreview();
                resultData.setText(null);
                btnRescann.setVisibility(View.GONE);
                resultData.setVisibility(View.GONE);
            }
        });
    }


    //REQUEST PERMISSION
    @Override
    protected void onResume() {
        super.onResume();
        requestForCamera();
    }

    private void requestForCamera() {
        Dexter.withContext(this).withPermission(Manifest.permission.CAMERA).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                codeScanner.startPreview();
                btnRescann.setVisibility(View.GONE);
                resultData.setVisibility(View.GONE);
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Toast.makeText(ScannerActivity.this, "Camera Permission is Required", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }
}
