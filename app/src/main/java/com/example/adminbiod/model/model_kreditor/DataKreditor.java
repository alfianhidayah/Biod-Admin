package com.example.adminbiod.model.model_kreditor;

public class DataKreditor {
    private String id_kreditor, nama_kreditor;

    public DataKreditor(String id_kreditor, String nama_kreditor) {
        this.id_kreditor = id_kreditor;
        this.nama_kreditor = nama_kreditor;
    }

    public String getId_kreditor() {
        return id_kreditor;
    }

    public String getNama_kreditor() {
        return nama_kreditor;
    }
}
