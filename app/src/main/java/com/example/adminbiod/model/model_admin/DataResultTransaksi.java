package com.example.adminbiod.model.model_admin;

public class DataResultTransaksi {
    private String id_kreditor, id_barang, id_transaksi, tanggal_transaksi, nominal_transaksi;

    public DataResultTransaksi(String id_kreditor, String id_barang, String id_transaksi, String tanggal_transaksi, String nominal_transaksi) {
        this.id_kreditor = id_kreditor;
        this.id_barang = id_barang;
        this.id_transaksi = id_transaksi;
        this.tanggal_transaksi = tanggal_transaksi;
        this.nominal_transaksi = nominal_transaksi;
    }

    public String getId_kreditor() {
        return id_kreditor;
    }

    public String getId_barang() {
        return id_barang;
    }

    public String getId_transaksi() {
        return id_transaksi;
    }

    public String getTanggal_transaksi() {
        return tanggal_transaksi;
    }

    public String getNominal_transaksi() {
        return nominal_transaksi;
    }
}
