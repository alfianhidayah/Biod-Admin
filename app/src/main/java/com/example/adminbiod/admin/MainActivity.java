package com.example.adminbiod.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.adminbiod.R;
import com.example.adminbiod.api.RetrofitClient;
import com.example.adminbiod.model.model_admin.ResponseLogin;
import com.example.adminbiod.storage.SharedPrefManager;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText editTextUsername;
    private TextInputEditText editTextPassword;

    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = (TextInputEditText) findViewById(R.id.editTextUsername);
        editTextPassword = (TextInputEditText) findViewById(R.id.editTextPassword);

        findViewById(R.id.btnMasuk).setOnClickListener(this);


    }

    private void login()
    {
        //deklarasi field API dan ambil data dari input text
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        //validasi
        if (username.isEmpty()){
            editTextUsername.setError("Masukkan Username !");
            editTextUsername.requestFocus();
        }
        if (password.isEmpty()){
            editTextPassword.setError("Masukkan Kata Sandi !");
            editTextPassword.requestFocus();
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Call<ResponseLogin> call = RetrofitClient.getInstance()
                .getApi().adminLogin(
                        username,
                        password
                );

        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                progressDialog.hide();
                ResponseLogin responseLogin = response.body();
                if (response.body().getStatus()){
                    Toast.makeText(MainActivity.this, responseLogin.getMessage(), Toast.LENGTH_SHORT).show();
                    //simpan data
//                    SharedPrefManager.getInstance(MainActivity.this).saveAdmin(responseLogin.getData());
                    Intent intent = new Intent(MainActivity.this, ScanActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, responseLogin.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            //FAILURE BIASANYA LUPA INTERNET PERMISSION
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                progressDialog.hide();
                Toast.makeText(MainActivity.this, "ERROR CUY", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnMasuk:
                login();
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (SharedPrefManager.getInstance(this).isLoggedIn()){
            Intent intent = new Intent(MainActivity.this, ScanActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }
    }
}
