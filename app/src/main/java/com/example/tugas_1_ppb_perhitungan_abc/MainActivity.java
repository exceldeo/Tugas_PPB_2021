package com.example.tugas_1_ppb_perhitungan_abc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugas_1_ppb_perhitungan_abc.contact.KontakActivity;


public class MainActivity extends AppCompatActivity {

    private Button btnKontak,btnKeluar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Aplikasi Tugas PPB");

        setupItemView();
        setupView();

    }

    private void setupItemView(){
        //Button
        btnKontak = findViewById(R.id.btnKontak);
        btnKeluar = findViewById(R.id.btnKeluar);

        btnKontak.setOnClickListener(RedirectToKontakPage);
        btnKeluar.setOnClickListener(keluar);

    }

    private void setupView(){
    }

    private final View.OnClickListener RedirectToKontakPage = v -> {
        startActivity(new Intent(MainActivity.this, KontakActivity.class));
    };

    private final View.OnClickListener keluar = v -> {
        moveTaskToBack(true);
    };

}