package com.example.adminbiod.model.model_kreditor;

import java.util.List;

public class ResponseScan {
    public Boolean status;
    public String message;
    public DataKreditor kreditor = null;
    public List<DataBarang> barang = null;
    public DataTransaksi transaksi = null;

    public ResponseScan(Boolean status, String message, DataKreditor kreditor, List<DataBarang> barang, DataTransaksi transaksi) {
        this.status = status;
        this.message = message;
        this.kreditor = kreditor;
        this.barang = barang;
        this.transaksi = transaksi;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public DataKreditor getKreditor() {
        return kreditor;
    }

    public List<DataBarang> getBarang() {
        return barang;
    }

    public DataTransaksi getTransaksi() {
        return transaksi;
    }
}
