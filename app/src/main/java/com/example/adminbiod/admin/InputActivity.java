package com.example.adminbiod.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adminbiod.R;
import com.example.adminbiod.adapter.BarangListAdapter;
import com.example.adminbiod.api.RetrofitClient;
import com.example.adminbiod.model.model_admin.ResponseTransaksi;
import com.example.adminbiod.model.model_kreditor.DataBarang;
import com.example.adminbiod.model.model_kreditor.DataKreditor;
import com.example.adminbiod.model.model_kreditor.DataTransaksi;
import com.example.adminbiod.model.model_kreditor.ResponseDataBarang;
import com.example.adminbiod.model.model_kreditor.ResponseScan;
import com.example.adminbiod.storage.SharedPrefManager;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InputActivity extends AppCompatActivity {

    private static final String TAG = "InputActivity" ;
    TextView titleInput, namaKreditor, idKreditor;
    TextInputEditText inputNominal;
    private ProgressDialog progressDialog;
    public String id_kreditor, id_barang, id_transaksi,tanggal_transaksi, nominal_transaksi, nama_kreditor, resultIdBarang, resultNamabarang;


    Button btnBarangKredit;
    TextView viewBarangKredit;

    private Context context;
    private List<DataBarang> barangList;


//    Button btnPilihTanggal;
//    TextView viewPilihTanggal;

    Button btnSubmit, btnRescann;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        //============seleksi============
        titleInput = (TextView) findViewById(R.id.titleInput);

        btnBarangKredit = (Button) findViewById(R.id.btnBarangKredit);
        viewBarangKredit = (TextView) findViewById(R.id.viewBarangKredit);
        namaKreditor = (TextView) findViewById(R.id.namaKreditor);
        idKreditor = (TextView) findViewById(R.id.IDKreditor);
        inputNominal = (TextInputEditText) findViewById(R.id.masukkanNominal);


//        btnPilihTanggal = (Button) findViewById(R.id.btnPilihTanggal);
//        viewPilihTanggal = (TextView) findViewById(R.id.viewPilihTanggal);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnRescann = (Button) findViewById(R.id.btnRescann);
        //===========Akhir Seleksi===========


        //============Customization Font========
        //Deklarasi font
        Typeface semibold = Typeface.createFromAsset(getAssets(), "Montserrat-SemiBold.ttf");
        //Customize font
        titleInput.setTypeface(semibold);
        //============End of Customization font hehe :D===========

        //===========SET TEXT NAMA & ID KREDITOR=============
        namaKreditor.setText(SharedPrefManager.getInstance(this).getKreditor().getNama_kreditor());
        idKreditor.setText("ID Kreditor = "+SharedPrefManager.getInstance(this).getKreditor().getId_kreditor());
        //==========AKHIR SET TEXT NAMA & ID KREDITOR===========

//        //==========event klik button alertdialog pilih barang=============

        btnBarangKredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataKreditor data = SharedPrefManager.getInstance(context).getKreditor();
                String id = data.getId_kreditor();

                Call<ResponseDataBarang> call = RetrofitClient.getInstance().getApi().ambildatabarang(id);

                call.enqueue(new Callback<ResponseDataBarang>() {
                    @Override
                    public void onResponse(Call<ResponseDataBarang> call, Response<ResponseDataBarang> response) {
                        if (response.body().getStatus() == true){
                            barangList = response.body().getBarang();
                            final Dialog dialog = new Dialog(InputActivity.this);
                            dialog.setContentView(R.layout.barang_dialog_list_view);
                            if (dialog.getWindow() !=  null) {
                                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            }

                            ListView listView = dialog.findViewById(R.id.lv_assignment_users);
                            TextView tv = dialog.findViewById(R.id.tv_popup_title);
                            ArrayAdapter arrayAdapter = new BarangListAdapter(InputActivity.this, R.layout.barang_list_layout,barangList);
                            listView.setAdapter(arrayAdapter);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView adapterView, View view, int which, long l) {
                                    Log.d(TAG, "showAssignmentsList: " + barangList.get(which).getId_barang());
                                    viewBarangKredit.setText(barangList.get(which).getNama_barang());
                                    resultIdBarang = barangList.get(which).getId_barang();
                                    dialog.dismiss();
                                }
                            });
                            dialog.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseDataBarang> call, Throwable t) {

                    }
                });
            }
        });


//        //===============Akhir Event Button Alert Dialog ==============





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
                id_barang = resultIdBarang;
                id_transaksi = dataTransaksi.getId_transaksi();
                tanggal_transaksi = formatDate;
                nominal_transaksi = inputNominal.getText().toString().trim();
                nama_kreditor = dataKreditor.getNama_kreditor();
                resultNamabarang = viewBarangKredit.getText().toString().trim();


                if (nominal_transaksi.isEmpty()){
                    inputNominal.setError("Masukkan Nominal Transaksi !");
                    inputNominal.requestFocus();
                } else {
                    //formatternumber
                    DecimalFormat formatter = new DecimalFormat("#,###,###");
                    String get_value = formatter.format(Long.valueOf(String.valueOf(inputNominal.getText())));

                    //akhir formater

                    //alertdialog
                    final MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(InputActivity.this);
                    builder.setTitle("Lanjutkan Transaksi ?");
                    builder.setMessage("Nominal yang ingin dimasukkan : " + "\n"
                            + "Rp. " + get_value + "\n\n"
                            + "Untuk Kreditor a.n : " + nama_kreditor + "\n\n"
                            + "Untuk Barang : " + resultNamabarang);
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
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
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

            }
        });

        btnRescann.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InputActivity.this, ScannerActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


        //=========Akhir Event PILIH TANGGAL YEAY :D=================

    }


}

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
// ============AKHIR DATEPICKER==============

//===========alert builder
//final ArrayAdapter arrayAdapter = new BarangListAdapter(context, R.layout.barang_list_layout, barangList);
//    final MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(InputActivity.this);
//                builder.setTitle("Pilih Barang Kredit Yang Ingin di Angsur");
//                        builder.setBackground(getResources().getDrawable(R.drawable.dialog_bg, null));
//                        builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
//@Override
//public void onClick(DialogInterface dialogInterface, int i) {
//final String idBarang = (String) arrayAdapter.getItem(i);
//        AlertDialog.Builder builderInner = new AlertDialog.Builder(InputActivity.this);
//        builderInner.setPositiveButton("OK" ,new DialogInterface.OnClickListener() {
//@Override
//public void onClick(DialogInterface dialogInterface, int i) {
//        viewBarangKredit.setText(barangList.get(i).getId_barang());
//        }
//        });
//        builderInner.show();
//        }
//        });
//

//=============Referensi stackoverflow alertdialog (GOOGLE)
//    AlertDialog.Builder builderSingle = new AlertDialog.Builder(InputActivity.this);
//                builderSingle.setIcon(R.drawable.logo);
//                        builderSingle.setTitle("Pilih Barang");
//
//final ArrayAdapter arrayAdapter = new ArrayAdapter(InputActivity.this, R.layout.barang_list_layout);
//
//        builderSingle.setNegativeButton("Kembali", new DialogInterface.OnClickListener() {
//@Override
//public void onClick(DialogInterface dialogInterface, int i) {
//        dialogInterface.dismiss();
//        }
//        });
//
//        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
//@Override
//public void onClick(DialogInterface dialogInterface, int i) {
//        String idBarang = (String) arrayAdapter.getItem(i);
//        AlertDialog.Builder builderInner = new AlertDialog.Builder(InputActivity.this);
//        builderInner.setMessage(idBarang);
//        builderInner.setTitle("Your Selected Item is");
//        builderInner.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//@Override
//public void onClick(DialogInterface dialogInterface, int i) {
//        dialogInterface.dismiss();
//        }
//        });
//        builderInner.show();
//        }
//        });
//
//        builderSingle.show();