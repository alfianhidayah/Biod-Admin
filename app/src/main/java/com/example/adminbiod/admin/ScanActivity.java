package com.example.adminbiod.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.adminbiod.R;
import com.example.adminbiod.storage.SharedPrefManager;

public class ScanActivity extends AppCompatActivity implements View.OnClickListener {

    TextView titleScan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        titleScan = (TextView) findViewById(R.id.titleScan);

        //Deklarasi font
        Typeface semibold = Typeface.createFromAsset(getAssets(), "Montserrat-SemiBold.ttf");

        //Customize font
        titleScan.setTypeface(semibold);

        //deklarasi button
        findViewById(R.id.btnScan).setOnClickListener(this);
//        findViewById(R.id.btnLogout).setOnClickListener(this);

    }

    public void scan(){
        //METHOD SCAN
        Intent intent = new Intent(this, ScannerActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

//    public void logout(){
//        SharedPrefManager.getInstance(this).clear();
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(intent);
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnScan :
                scan();
                break;
//            case R.id.btnLogout:
//                logout();
//                break;
        }
    }
}
