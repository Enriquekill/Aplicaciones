package com.example.cronometro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Chronometer;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Chronometer reloj;
    private boolean corriendo = false;
    private long valorPausa;

    private List<String> mLista = new ArrayList<>();
    private ArrayAdapter<String> mAdapter;
    private ListView vista;
    private long inicioCr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reloj = (Chronometer) findViewById(R.id.txtTiempo);
        inicioCr = reloj.getBase();
        vista = (ListView) findViewById(R.id.listVIew);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    public void comenzarCronometro(View v){
        if(!corriendo){
            reloj.setBase(SystemClock.elapsedRealtime()-valorPausa);
            reloj.start();
            corriendo = true;
        }
    }
    public void reiniciarCronometro(View v){
        reloj.setBase(SystemClock.elapsedRealtime());
        valorPausa = 0;
    }
    public void pausarCronometro(View v){
        if(corriendo){
            reloj.stop();
            valorPausa = SystemClock.elapsedRealtime() - reloj.getBase();
            corriendo = false;
        }
    }

    public void registrar(View v){
        String text = reloj.getText().toString();
        mLista.add(text);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,mLista);
        vista.setAdapter(mAdapter);
    }
}