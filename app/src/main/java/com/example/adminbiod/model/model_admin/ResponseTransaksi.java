package com.example.adminbiod.model.model_admin;

public class ResponseTransaksi {
    public Boolean status;
    public String message;
    public DataResultTransaksi transaksi = null;

    public ResponseTransaksi(Boolean status, String message, DataResultTransaksi transaksi) {
        this.status = status;
        this.message = message;
        this.transaksi = transaksi;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public DataResultTransaksi getTransaksi() {
        return transaksi;
    }
}
