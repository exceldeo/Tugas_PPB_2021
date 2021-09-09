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
    private EditText etInputA,etInputB,etInputC;
    private TextView tvHasil;
    private Button btnRumus;

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
        etInputC = (EditText) findViewById(R.id.et_input_c);
        btnRumus = (Button) findViewById(R.id.btn_rumus);
        tvHasil = (TextView) findViewById(R.id.tv_hasil);

        btnRumus.setOnClickListener(perhutungan);
    }

    private View.OnClickListener perhutungan = v -> {

        Log.d("inputA" , etInputA.getText().toString());
        Log.d("inputB" , etInputB.getText().toString());
        Log.d("inputC" , etInputC.getText().toString());

        double a = Double.parseDouble(etInputA.getText().toString());
        double b = Double.parseDouble(etInputB.getText().toString());
        double c = Double.parseDouble(etInputC.getText().toString());
        double d,x1,x2;

        d = (b*b)-(4*a*c);

        if ( d > 0 ){
            x1 = (-b + sqrt(d)) / (2*a);
            x2 = (-b - sqrt(d)) / (2*a);
            tvHasil.setText("x1 = " + x1 + " ,x2 = " + x2);
        }else if (d == 0){
            x1 = (-b + sqrt(d)) / (2*a);
            x2 = x1;
            tvHasil.setText("x1 = " + x1 + " ,x2 = " + x2);
        }else {
            tvHasil.setText("imajiner");
        }

    };

    private void setupView(){
    }

}