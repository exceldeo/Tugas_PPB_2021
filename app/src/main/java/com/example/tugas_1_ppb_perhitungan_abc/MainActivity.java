package com.example.tugas_1_ppb_perhitungan_abc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.Math.sqrt;


public class MainActivity extends AppCompatActivity {
    private EditText etInputA,etInputB;
    private TextView tvHasil;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupItemView();
        setupView();

    }

    private void setupItemView(){
        etInputA = (EditText) findViewById(R.id.et_input_a);
        etInputB = (EditText) findViewById(R.id.et_input_b);

        tvHasil = (TextView) findViewById(R.id.tv_hasil);

    }

    public void operasi(View v){

        double a = Double.parseDouble(etInputA.getText().toString());
        double b = Double.parseDouble(etInputB.getText().toString());
        double h = 0;

        switch (v.getId()){
            case R.id.btn_tambah: h = a + b ; break;
            case R.id.btn_kurang: h = a - b ; break;
            case R.id.btn_kali: h = a * b ; break;
            case R.id.btn_bagi: h = a / b ; break;
        }

        tvHasil.setText("hasil = " + h);
    }

    private void setupView(){
    }

}