package com.example.tugas_1_ppb_perhitungan_abc;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MainActivity extends AppCompatActivity {

    private EditText nrp, nama;
    private SQLiteDatabase dbku;
    private SQLiteOpenHelper Opendb;
    private Button simpan,ambildata,update,hapus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Buat Database Sederhana");

        setupItemView();
        setupView();
        declaceDB();
    }

    private void setupItemView(){
        //EditText
        nrp = (EditText) findViewById (R.id.etNrp);
        nama = (EditText) findViewById(R.id.etNama);

        simpan = (Button) findViewById(R.id.BtnSimpan);
        ambildata = (Button) findViewById(R.id.BtnAmbilData);
        update = (Button) findViewById(R.id.BtnUpdate);
        hapus = (Button) findViewById(R.id.BtnHapus);

        simpan.setOnClickListener(operasi);
        ambildata.setOnClickListener(operasi);
        update.setOnClickListener(operasi);
        hapus.setOnClickListener(operasi);

    }

    private void declaceDB(){
        Opendb = new SQLiteOpenHelper(this, "db.sql", null, 1) {
            @Override
            public void onCreate(SQLiteDatabase db) {}

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
        };
        dbku = Opendb.getWritableDatabase();
        dbku.execSQL("create table if not exists mhs(nrp TEXT, nama TEXT)");
    }

    private void setupView(){

    }

    @Override
    protected void onStop(){
        dbku.close();
        Opendb.close();
        super.onStop();
    }

    View.OnClickListener operasi = v -> {
        switch (v.getId()){
            case R.id.BtnSimpan:simpan(); break;
            case R.id.BtnAmbilData:ambildata(); break;
            case R.id.BtnUpdate:update(); break;
            case R.id.BtnHapus:hapus(); break;
        }
    };

    private void simpan(){
        ContentValues dataku = new ContentValues();

        dataku.put("nrp", nrp.getText().toString());
        dataku.put("nama", nama.getText().toString());
        dbku.insert("mhs", null, dataku);
        Toast.makeText(this, "Data Tersimpan", Toast.LENGTH_LONG).show();
    }

    private void ambildata(){
        @SuppressLint("Recycle") Cursor cur = dbku.rawQuery("select * from mhs where nrp='" + nrp.getText().toString() + "'", null);
        if(cur.getCount() > 0){
            Toast.makeText(this, "Data Ditemukan Sejumlah " + cur.getCount(), Toast.LENGTH_LONG).show();
            cur.moveToFirst();
            nama.setText(cur.getString(cur.getColumnIndex("nama")));
        }
        else Toast.makeText(this, "Data Tidak Ditemukan", Toast.LENGTH_LONG).show();
    }

    private void update(){
        ContentValues dataku = new ContentValues();

        dataku.put("nrp", nrp.getText().toString());
        dataku.put("nama", nama.getText().toString());
        dbku.update("mhs", dataku, "nrp='" + nrp.getText().toString() + "'", null);
        Toast.makeText(this, "Data Terupdate", Toast.LENGTH_LONG).show();
    }

    private void hapus(){
        dbku.delete("mhs", "nrp='" + nrp.getText().toString() + "'", null);
        Toast.makeText(this, "Data Terhapus", Toast.LENGTH_LONG).show();
    }
}