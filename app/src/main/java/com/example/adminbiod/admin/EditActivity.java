package com.example.adminbiod.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adminbiod.R;
import com.example.adminbiod.api.RetrofitClient;
import com.example.adminbiod.model.model_admin.ResponseTransaksi;
import com.example.adminbiod.storage.SharedPrefManager;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditActivity extends AppCompatActivity {

    public TextView namaKreditor, idKreditor, idBarang, idTransaksi, nominalTransaksi;
    public TextInputEditText ubahNominal;
    public Button btnUbahTransaksi;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //seleksi
        namaKreditor = (TextView) findViewById(R.id.namaKreditor);
        idKreditor = (TextView) findViewById(R.id.idKreditor);
        idBarang = (TextView) findViewById(R.id.idBarang);
        idTransaksi= (TextView) findViewById(R.id.idTransaksi);
        nominalTransaksi = (TextView) findViewById(R.id.nominalTransaksi);

        ubahNominal = (TextInputEditText) findViewById(R.id.ubahNominal);

        btnUbahTransaksi = (Button) findViewById(R.id.btnUbahTransaksi);

        namaKreditor.setText(SharedPrefManager.getInstance(EditActivity.this).getKreditor().getNama_kreditor());
        idKreditor.setText(SharedPrefManager.getInstance(EditActivity.this).getResultTransaksi().getId_kreditor());
        idBarang.setText(SharedPrefManager.getInstance(EditActivity.this).getResultTransaksi().getId_barang());
        idTransaksi.setText(SharedPrefManager.getInstance(EditActivity.this).getResultTransaksi().getId_transaksi());
        nominalTransaksi.setText(SharedPrefManager.getInstance(EditActivity.this).getResultTransaksi().getNominal_transaksi());

        btnUbahTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id_kreditor = SharedPrefManager.getInstance(EditActivity.this).getKreditor().getId_kreditor();
                String id_barang = SharedPrefManager.getInstance(EditActivity.this).getResultTransaksi().getId_barang();
                String id_transaksi = SharedPrefManager.getInstance(EditActivity.this).getResultTransaksi().getId_transaksi();
                String tanggal_transaksi = SharedPrefManager.getInstance(EditActivity.this).getResultTransaksi().getTanggal_transaksi();
                String nominal_transaksi = ubahNominal.getText().toString().trim();

                if (nominal_transaksi.isEmpty()){
                    ubahNominal.setError("Masukkan Nominal !");
                    ubahNominal.requestFocus();
                }

                progressDialog = new ProgressDialog(EditActivity.this);
                progressDialog.setMessage("Memperbarui Data..");
                progressDialog.setCancelable(false);
                progressDialog.show();

                Call<ResponseTransaksi> call = RetrofitClient.getInstance().getApi()
                        .editTransaksi(id_kreditor,id_barang,id_transaksi,tanggal_transaksi,nominal_transaksi);

                call.enqueue(new Callback<ResponseTransaksi>() {
                    @Override
                    public void onResponse(Call<ResponseTransaksi> call, Response<ResponseTransaksi> response) {
                        ResponseTransaksi responseTransaksi = response.body();
                        progressDialog.hide();
                        if (response.body().getStatus()){
                            Toast.makeText(EditActivity.this, responseTransaksi.getMessage(), Toast.LENGTH_LONG). show();

                            //SAVE DATA TRANSAKSI
                            SharedPrefManager.getInstance(EditActivity.this).saveResultTransaksi(responseTransaksi.getTransaksi());

                            Intent intent = new Intent(EditActivity.this, SuccessActivity.class);
                            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else {
                            Toast.makeText(EditActivity.this, responseTransaksi.getMessage(), Toast.LENGTH_LONG). show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseTransaksi> call, Throwable t) {
                        progressDialog.hide();
                        Toast.makeText(EditActivity.this, "ERROR EDIT TRANSACTION", Toast.LENGTH_LONG). show();
                    }
                });


            }
        });

    }
}
