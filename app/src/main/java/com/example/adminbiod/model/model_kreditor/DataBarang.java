package com.example.adminbiod.model.model_kreditor;

public class DataBarang {
    private String id_barang, nama_barang;

    public DataBarang(String id_barang, String nama_barang) {
        this.id_barang = id_barang;
        this.nama_barang = nama_barang;
    }

    public String getId_barang() {
        return id_barang;
    }

    public String getNama_barang() {
        return nama_barang;
    }
}
