package com.example.adminbiod.api;

import com.example.adminbiod.model.model_admin.ResponseLogin;
import com.example.adminbiod.model.model_admin.ResponseTransaksi;
import com.example.adminbiod.model.model_kreditor.ResponseDataBarang;
import com.example.adminbiod.model.model_kreditor.ResponseScan;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface Api {
    @FormUrlEncoded
    @POST("authadmin")
    Call<ResponseLogin> adminLogin(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("scanner")
    Call<ResponseScan>scanning(
            @Field("id_kreditor") String id
    );

    @FormUrlEncoded
    @POST("DataBarang")
    Call<ResponseDataBarang>ambildatabarang(
            @Field("id_kreditor") String id
    );

    @FormUrlEncoded
    @POST("transaksiadmin")
    Call<ResponseTransaksi>inputTransaksi(
            @Field("id_kreditor") String id_kreditor,
            @Field("id_barang") String id_barang,
            @Field("id_transaksi") String id_transaksi,
            @Field("tanggal_transaksi") String tanggal_transaksi,
            @Field("nominal_transaksi") String nominal_transaksi
    );

    @FormUrlEncoded
    @PUT("transaksiadmin")
    Call<ResponseTransaksi>editTransaksi(
            @Field("id_kreditor") String id_kreditor,
            @Field("id_barang") String id_barang,
            @Field("id_transaksi") String id_transaksi,
            @Field("tanggal_transaksi") String tanggal_transaksi,
            @Field("nominal_transaksi") String nominal_transaksi
    );
}
