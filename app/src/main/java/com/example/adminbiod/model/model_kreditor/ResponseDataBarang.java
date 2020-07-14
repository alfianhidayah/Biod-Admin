package com.example.adminbiod.model.model_kreditor;

import java.util.List;

public class ResponseDataBarang {

    public boolean status;
    public List<DataBarang> barang;

    public ResponseDataBarang(boolean status, List<DataBarang> barang) {
        this.status = status;
        this.barang = barang;
    }

    public boolean getStatus() {
        return status;
    }

    public List<DataBarang> getBarang() {
        return barang;
    }
}
