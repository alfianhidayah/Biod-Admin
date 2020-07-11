package com.example.adminbiod.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adminbiod.R;
import com.example.adminbiod.api.RetrofitClient;
import com.example.adminbiod.model.model_admin.ResponseTransaksi;
import com.example.adminbiod.model.model_kreditor.DataKreditor;
import com.example.adminbiod.model.model_kreditor.DataTransaksi;
import com.example.adminbiod.storage.SharedPrefManager;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InputActivity extends AppCompatActivity {

    TextView titleInput, namaKreditor, idKreditor;
    TextInputEditText inputIdBarang, inputNominal;
    private ProgressDialog progressDialog;
    public String id_kreditor, id_barang, id_transaksi,tanggal_transaksi, nominal_transaksi, nama_kreditor;


    //    Button btnBarangKredit;
    TextView viewBarangKredit;
    CharSequence[] barangList;

//    Button btnPilihTanggal;
//    TextView viewPilihTanggal;

    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        //============seleksi============
        titleInput = (TextView) findViewById(R.id.titleInput);

//        btnBarangKredit = (Button) findViewById(R.id.btnBarangKredit);
//        viewBarangKredit = (TextView) findViewById(R.id.viewBarangKredit);
        namaKreditor = (TextView) findViewById(R.id.namaKreditor);
        idKreditor = (TextView) findViewById(R.id.IDKreditor);
        inputIdBarang = (TextInputEditText) findViewById(R.id.masukkanIdBarang);
        inputNominal = (TextInputEditText) findViewById(R.id.masukkanNominal);



//        barangList = new CharSequence[]{
//                "HP Xiami Readme 3",
//                "Kulkas Polytron",
//                "Laptop ROG Zephyrus"
//        };

//        btnPilihTanggal = (Button) findViewById(R.id.btnPilihTanggal);
//        viewPilihTanggal = (TextView) findViewById(R.id.viewPilihTanggal);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        //===========Akhir Seleksi===========


        //============Customization Font========
        //Deklarasi font
        Typeface semibold = Typeface.createFromAsset(getAssets(), "Montserrat-SemiBold.ttf");
        //Customize font
        titleInput.setTypeface(semibold);
        //============End of Customization font hehe :D===========

        //===========SET TEXT NAMA & ID KREDITOR=============
        namaKreditor.setText(SharedPrefManager.getInstance(this).getKreditor().getNama_kreditor());
        idKreditor.setText(SharedPrefManager.getInstance(this).getKreditor().getId_kreditor());
        //==========AKHIR SET TEXT NAMA & ID KREDITOR===========

//        //==========event klik button alertdialog pilih barang=============
//        btnBarangKredit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(InputActivity.this);
//                builder.setTitle("Pilih Barang Kredit Yang Ingin di Angsur");
//                builder.setBackground(getResources().getDrawable(R.drawable.dialog_bg, null));
//                builder.setSingleChoiceItems(barangList, 0, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        //event setelah pilih
//                        viewBarangKredit.setText(" " + barangList[i]);
//                    }
//                });
//                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                //event setelah klik ok
//
//
//
//                            }
//                        });
//                        builder.show();
//
//            }
//        });
//        //===============Akhir Event Button Alert Dialog ==============




        //==============Event PILIH TANGGAL WOI =====================
            //biar dapet waktu sekarang cuy
//        final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
//        calendar.clear();
//
//            //deklarasi waktu sekarang
//        Long today = MaterialDatePicker.todayInUtcMilliseconds();
//
//            //set time sesuai waktu sekarang
//
//        calendar.setTimeInMillis(today);
//
//            //batas bulan
//        calendar.set(Calendar.MONTH, Calendar.JANUARY);
//        long january = calendar.getTimeInMillis();
//
//        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
//        long desember = calendar.getTimeInMillis();
//
//
//        //Constraint Date
//        CalendarConstraints.Builder constraintbuilder = new CalendarConstraints.Builder();
//        constraintbuilder.setStart(january);
//        constraintbuilder.setEnd(desember);
//
//
//        //Material Picker
//        MaterialDatePicker.Builder tanggal = MaterialDatePicker.Builder.datePicker();
//        tanggal.setTitleText("Pilih Tanggal Pembayaran");
//            //seleksi tanggal sekarang otomatis cuy :D
//        tanggal.setSelection(today);
//            //set constraint / batas
//        tanggal.setCalendarConstraints(constraintbuilder.build());
//        final MaterialDatePicker materialDatePicker = tanggal.build();
//
//            //event ketika tombol di klik coeg
//        btnPilihTanggal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                materialDatePicker.show(getSupportFragmentManager(),"DATE_PICKER");
//            }
//        });
//
//        //===event merubah text view hehe XD
//        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
//            @Override
//            public void onPositiveButtonClick(Object selection) {
//
//
//            }
//        });
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);


        calendar.set(year,month,day);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final String formatDate = sdf.format(calendar.getTime());
//        viewPilihTanggal.setText(formatDate);



        //event pindah ke menu Home
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DataKreditor dataKreditor = SharedPrefManager.getInstance(InputActivity.this).getKreditor();
                DataTransaksi dataTransaksi = SharedPrefManager.getInstance(InputActivity.this).getTransaksi();

                id_kreditor = dataKreditor.getId_kreditor();
                id_barang = inputIdBarang.getText().toString().trim();
                id_transaksi = dataTransaksi.getId_transaksi();
                tanggal_transaksi = formatDate;
                nominal_transaksi = inputNominal.getText().toString().trim();
                nama_kreditor = dataKreditor.getNama_kreditor();

                //validasi
                if (id_barang.isEmpty()){
                    inputIdBarang.setError("Masukkan ID Barang !");
                    inputIdBarang.requestFocus();
                }
                if (nominal_transaksi.isEmpty()){
                    inputNominal.setError("Masukkan Nominal Transaksi !");
                    inputNominal.requestFocus();
                }

                //formatternumber
                DecimalFormat formatter = new DecimalFormat("#,###,###");
                String get_value = formatter.format(Long.valueOf(String.valueOf(inputNominal.getText())));

                //akhir formater

                //alertdialog
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(InputActivity.this);
                builder.setTitle("Lanjutkan Transaksi ?");
                builder.setMessage("Nominal yang ingin dimasukkan : " + "\n"
                        + "Rp. " + get_value + "\n\n"
                        + "Untuk Kreditor a.n : " + nama_kreditor + "\n\n"
                        + "Untuk Barang dengan ID : " + id_barang);
                builder.setBackground(getResources().getDrawable(R.drawable.dialog_bg, null));

                builder.setPositiveButton("Lanjutkan", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //event setelah klik ok
                                progressDialog = new ProgressDialog(InputActivity.this);
                                progressDialog.setMessage("Memasukkan Data Transaksi");
                                progressDialog.setCancelable(false);
                                progressDialog.show();

                                Call<ResponseTransaksi> call = RetrofitClient.getInstance().getApi()
                                        .inputTransaksi(id_kreditor,id_barang,id_transaksi,tanggal_transaksi,nominal_transaksi);

                                call.enqueue(new Callback<ResponseTransaksi>() {
                                    @Override
                                    public void onResponse(Call<ResponseTransaksi> call, Response<ResponseTransaksi> response) {
                                        ResponseTransaksi responseTransaksi = response.body();
                                        progressDialog.hide();
                                        if (response.body().getStatus()){
                                            Toast.makeText(InputActivity.this, responseTransaksi.getMessage(), Toast.LENGTH_LONG). show();

                                            //SAVE DATA TRANSAKSI
                                            SharedPrefManager.getInstance(InputActivity.this).saveResultTransaksi(responseTransaksi.getTransaksi());

                                            Intent intent = new Intent(InputActivity.this, SuccessActivity.class);
                                            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(InputActivity.this, responseTransaksi.getMessage(), Toast.LENGTH_LONG). show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseTransaksi> call, Throwable t) {
                                        progressDialog.hide();
                                        Toast.makeText(InputActivity.this, "ERROR TRANSACTION", Toast.LENGTH_LONG). show();
                                    }
                                });


                            }
                        });

                builder.setNeutralButton("Kembali", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //event setelah klik ok



                            }
                        });
                //akhir alertdialog
                builder.show();
            }
        });


        //=========Akhir Event PILIH TANGGAL YEAY :D=================

    }
}
