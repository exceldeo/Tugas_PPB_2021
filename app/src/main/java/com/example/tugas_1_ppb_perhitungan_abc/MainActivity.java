package com.example.tugas_1_ppb_perhitungan_abc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugas_1_ppb_perhitungan_abc.models.PenjualanModels;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;


public class MainActivity extends AppCompatActivity {
    EditText etNamaPelanggan, etNamaBarang, etJmlBarang, etHarga, etJmlUang;
    TextView tvNamaPembeli, tvNamaBarang, tvJmlBarang, tvHarga, tvUangBayar,
            tvTotal, tvKembalian, tvBonus, tvKeterangan,
            tvJmlItem,tvTotalHargaItem;
    Button btnProses, btnHapus, btnKeluar, btnTambahOrder;

    List<PenjualanModels> order = new ArrayList<>();

    String namaPelanggan, namaBarang, jumlahBarang, hargaBarang, uangBayar;
    Integer jmlBarang, hrgBarang, uangByr, total = 0, kembalian, item = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Aplikasi Penjualan");

        setupItemView();
        setupView();
    }

    private void setupItemView(){
        //EditText
        etNamaPelanggan = findViewById(R.id.etNamaPelanggan);
        etNamaBarang = findViewById(R.id.etNamaBarang);
        etJmlBarang = findViewById(R.id.etJmlBarang);
        etHarga = findViewById(R.id.etHarga);
        etJmlUang = findViewById(R.id.etJmlUang);

        //TextView
        tvNamaPembeli = findViewById(R.id.tvNamaPembeli);
        tvJmlBarang = findViewById(R.id.tvJmlBarang);
        tvUangBayar = findViewById(R.id.tvUangBayar);
        tvTotal = findViewById(R.id.tvTotal);
        tvKembalian = findViewById(R.id.tvKembalian);
        tvBonus = findViewById(R.id.tvBonus);
        tvKeterangan = findViewById(R.id.tvKeterangan);
        tvJmlItem = findViewById(R.id.tvJmlItem);
        tvTotalHargaItem = findViewById(R.id.tvTotalHargaItem);

        //Button
        btnProses = findViewById(R.id.btnProses);
        btnHapus = findViewById(R.id.btnHapus);
        btnKeluar = findViewById(R.id.btnKeluar);
        btnTambahOrder = findViewById(R.id.btnTambahOrder);

        btnTambahOrder.setOnClickListener(tambahData);
        btnProses.setOnClickListener(proses);
        btnHapus.setOnClickListener(hapusData);
        btnKeluar.setOnClickListener(keluar);

    }

    private void setupView(){
        tvJmlItem.setText("0");
        tvTotalHargaItem.setText("0");
    }

    private final View.OnClickListener tambahData = v -> {

        namaBarang = etNamaBarang.getText().toString().trim();
        jumlahBarang = etJmlBarang.getText().toString().trim();
        hargaBarang = etHarga.getText().toString().trim();

        jmlBarang = Integer.parseInt(jumlahBarang);
        hrgBarang = Integer.parseInt(hargaBarang);

        order.add(new PenjualanModels(namaBarang, jmlBarang, hrgBarang));

        total = total + (jmlBarang * hrgBarang);

        etNamaBarang.setText("");
        etJmlBarang.setText("");
        etHarga.setText("");

        Integer sizeOrder = order.size();
        item = item + jmlBarang;

        tvJmlItem.setText(item.toString());
        tvTotalHargaItem.setText(total.toString());

        Toast.makeText(getApplicationContext(), "Barang berhasil ditambahkan", Toast.LENGTH_SHORT).show();
    };

    private final View.OnClickListener proses = v -> {

        namaPelanggan = etNamaPelanggan.getText().toString().trim();
        uangBayar = etJmlUang.getText().toString().trim();
        uangByr = Integer.parseInt(uangBayar);

        if (uangByr < total) {
            Toast.makeText(getApplicationContext(), "Uang yang anda bayarkan kurang", Toast.LENGTH_SHORT).show();
        } else {
            kembalian = (uangByr - total);

            tvNamaPembeli.setText("Nama Pembeli : " + namaPelanggan);
            tvJmlBarang.setText("Jumlah Barang : " + item);
            tvUangBayar.setText("Uang bayar : " + uangBayar);

            tvTotal.setText("Total Belanja " + total);
            if (total >= 200000) {
                tvBonus.setText("Bonus : HardDisk 1TB");
            } else if (total >= 50000) {
                tvBonus.setText("Bonus : Keyboard Gaming");
            } else if (total >= 40000) {
                tvBonus.setText("Bonus : Mouse Gaming");
            } else {
                tvBonus.setText("Bonus : Tidak ada bonus!");
            }

            order.clear();
            tvJmlItem.setText("0");
            tvTotalHargaItem.setText("0");
            item = 0;

            etNamaPelanggan.setText("");
            etJmlUang.setText("");

            tvKeterangan.setText("Keterangan : Tunggu kembalian");
            tvKembalian.setText("Uang Kembalian : Rp. " + kembalian);
        }
    };

    private final View.OnClickListener hapusData = v -> {
        order.clear();
        tvJmlItem.setText("0");
        tvTotalHargaItem.setText("0");
        item = 0;
        tvNamaPembeli.setText("");
        tvJmlBarang.setText("");
        tvUangBayar.setText("");
        tvTotal.setText("");
        tvBonus.setText("");
        tvKeterangan.setText("");
        tvKembalian.setText("");
        Toast.makeText(getApplicationContext(), "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
    };

    private final View.OnClickListener keluar = v -> {
        moveTaskToBack(true);
    };
}