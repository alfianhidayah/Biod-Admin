package com.example.adminbiod.model.model_kreditor;

public class ResponseScan {
    public Boolean status;
    public String message;
    public DataKreditor kreditor = null;
    public DataTransaksi transaksi = null;


    public ResponseScan(Boolean status, String message, DataKreditor kreditor, DataTransaksi transaksi) {
        this.status = status;
        this.message = message;
        this.kreditor = kreditor;
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

    public DataTransaksi getTransaksi(){
        return transaksi;
    }
}
