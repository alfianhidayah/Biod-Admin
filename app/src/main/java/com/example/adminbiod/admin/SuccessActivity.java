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

public class SuccessActivity extends AppCompatActivity {

    TextView titleSuccess, titleNama, namaSuccess, titleIdKreditor, idKreditorSuccess, titleIdBarang, idBarangSuccess, titleIdTransaksi, idTransaksiSuccess, titleTanggal, tanggalSuccess, titleNominal, nominalSuccess;
    Button btnToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        //=============Seleksi CUy========

        titleSuccess = (TextView) findViewById(R.id.titleSuccess);
        titleNama = (TextView) findViewById(R.id.titleNama);
        namaSuccess = (TextView) findViewById(R.id.namaSuccess);
        titleIdKreditor = (TextView) findViewById(R.id.titleIdKreditor);
        idKreditorSuccess = (TextView) findViewById(R.id.idKreditorSuccess);
        titleIdBarang = (TextView) findViewById(R.id.titleIdBarang);
        idBarangSuccess = (TextView) findViewById(R.id.idBarangSuccess);
        titleIdTransaksi = (TextView) findViewById(R.id.titleIdTransaksi);
        idTransaksiSuccess = (TextView) findViewById(R.id.idTransaksiSuccess);
        titleTanggal = (TextView) findViewById(R.id.titleTanggal);
        tanggalSuccess = (TextView) findViewById(R.id.tanggalSuccess);
        titleNominal = (TextView) findViewById(R.id.titleNominal);
        nominalSuccess = (TextView) findViewById(R.id.nominalSuccess);

        btnToHome = (Button) findViewById(R.id.btnToHome);
//        btnEdit = (Button) findViewById(R.id.btnEdit);

        //=============Akhir Sleksi Uhuy========

        //============Customization Font========
        //Deklarasi font
        Typeface semibold = Typeface.createFromAsset(getAssets(), "Montserrat-SemiBold.ttf");
        Typeface bold = Typeface.createFromAsset(getAssets(), "Montserrat-Bold.ttf");

        //Customize font
        titleSuccess.setTypeface(bold);
        titleNama.setTypeface(semibold);
        namaSuccess.setTypeface(semibold);
        titleIdKreditor.setTypeface(semibold);
        idKreditorSuccess.setTypeface(semibold);
        titleIdBarang.setTypeface(semibold);
        idBarangSuccess.setTypeface(semibold);
        titleIdTransaksi.setTypeface(semibold);
        idTransaksiSuccess.setTypeface(semibold);
        titleTanggal.setTypeface(semibold);
        tanggalSuccess.setTypeface(semibold);
        titleNominal.setTypeface(semibold);
        nominalSuccess.setTypeface(semibold);

        //============End of Customization font hehe :D===========

        //============Custom Text============
        namaSuccess.setText(SharedPrefManager.getInstance(SuccessActivity.this).getKreditor().getNama_kreditor());
        idKreditorSuccess.setText(SharedPrefManager.getInstance(SuccessActivity.this).getKreditor().getId_kreditor());
        idBarangSuccess.setText(SharedPrefManager.getInstance(SuccessActivity.this).getResultTransaksi().getId_barang());
        idTransaksiSuccess.setText(SharedPrefManager.getInstance(SuccessActivity.this).getTransaksi().getId_transaksi());
        tanggalSuccess.setText(SharedPrefManager.getInstance(SuccessActivity.this).getResultTransaksi().getTanggal_transaksi());
        nominalSuccess.setText(SharedPrefManager.getInstance(SuccessActivity.this).getResultTransaksi().getNominal_transaksi());
        //============Akhir Custiom Text=======

        //=======Event pindah ke home======
        btnToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPrefManager.getInstance(SuccessActivity.this).clear();
                Intent home = new Intent(SuccessActivity.this, ScanActivity.class);
                home.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(home);
            }
        });

//        btnEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent home = new Intent(SuccessActivity.this, EditActivity.class);
//                home.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(home);
//            }
//        });



    }
}
