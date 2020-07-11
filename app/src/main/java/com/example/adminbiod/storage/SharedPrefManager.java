package com.example.adminbiod.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.adminbiod.model.model_admin.DataAdmin;
import com.example.adminbiod.model.model_admin.DataResultTransaksi;
import com.example.adminbiod.model.model_kreditor.DataKreditor;
import com.example.adminbiod.model.model_kreditor.DataTransaksi;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "admin_biod_pref";
    private static SharedPrefManager mInstance;
    private Context mCtx;

    public SharedPrefManager(Context mCtx) {
        this.mCtx = mCtx;
    }

    public static synchronized SharedPrefManager getInstance(Context mCtx){
        if (mInstance == null){
            mInstance = new SharedPrefManager(mCtx);
        }
        return mInstance;
    }

    // IT WORKS!
    public void saveAdmin(DataAdmin admin) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("username", admin.getUsername());

        editor.apply();
    }

    public void saveKreditor(DataKreditor kreditor) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("id_kreditor", kreditor.getId_kreditor());
        editor.putString("nama_kreditor", kreditor.getNama_kreditor());

        editor.apply();
    }

    public void saveTransaksi(DataTransaksi transaksi) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("id_transaksi", transaksi.getId_transaksi());

        editor.apply();
    }

    public void saveResultTransaksi(DataResultTransaksi resultTransaksi) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("id_kreditor", resultTransaksi.getId_kreditor());
        editor.putString("id_barang", resultTransaksi.getId_barang());
        editor.putString("id_transaksi", resultTransaksi.getId_transaksi());
        editor.putString("tanggal_transaksi", resultTransaksi.getTanggal_transaksi());
        editor.putString("nominal_transaksi", resultTransaksi.getNominal_transaksi());

        editor.apply();
    }

    public DataTransaksi getTransaksi(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new DataTransaksi(
                sharedPreferences.getString("id_transaksi", null)
        );
    }

    public DataKreditor getKreditor(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new DataKreditor(
                sharedPreferences.getString("id_kreditor", "-1"),
                sharedPreferences.getString("nama_kreditor", null)
        );
    }

    public DataResultTransaksi getResultTransaksi(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new DataResultTransaksi(
                sharedPreferences.getString("id_kreditor", null),
                sharedPreferences.getString("id_barang", null),
                sharedPreferences.getString("id_transaksi", null),
                sharedPreferences.getString("tanggal_transaksi", null),
                sharedPreferences.getString("nominal_transaksi", null)
        );
    }

    //IT WORKS!
    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("username", null) != "asu";
    }

    public void clear(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
