package com.example.adminbiod.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.adminbiod.R;
import com.example.adminbiod.model.model_kreditor.DataBarang;

import org.w3c.dom.Text;

import java.util.List;

public class BarangListAdapter extends ArrayAdapter<DataBarang> {

    private Context context;
    private List<DataBarang> barangList;


    public BarangListAdapter(@NonNull Context context, int resource, @NonNull List<DataBarang> array) {
        super(context, resource, array);
        barangList = array;
        this.context = context;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.barang_list_layout, parent, false);
        TextView id_barang = rowView.findViewById(R.id.itemIdBarang);
        TextView nama_barang = rowView.findViewById(R.id.itemNamaBarang);
        DataBarang dataBarang = barangList.get(position);

        id_barang.setText(dataBarang.getId_barang());
        nama_barang.setText(dataBarang.getNama_barang());

        return rowView;
    }
}
