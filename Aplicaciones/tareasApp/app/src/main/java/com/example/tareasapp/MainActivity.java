package com.example.tareasapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private Button btnAnadir;
    private ListView lstView;
    private EditText etxt;

    public List<String> mLista = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        etxt = (EditText) findViewById(R.id.etxtNombre);
        lstView = (ListView) findViewById(R.id.txtView);
        lstView.setOnItemClickListener(this);
        btnAnadir = (Button) findViewById(R.id.btnAnadir);

        leerDatos();
    }

    public void agregar(View v) {
        String text = etxt.getText().toString().trim();
        mLista.add(text);
        etxt.getText().clear();

        customAdapter adaptador = new customAdapter(this,mLista);
        lstView.setAdapter(adaptador);
        guardarDatos(v);
    }

    public void guardarDatos(View v){
        File ruta = getApplicationContext().getFilesDir();
        String nombreArch = "datosTareas.tpo";

        try {
            FileOutputStream escribirArch = new FileOutputStream(new File(ruta,nombreArch));
            ObjectOutputStream streamArch = new ObjectOutputStream(escribirArch);
            streamArch.writeObject(mLista);
            streamArch.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void leerDatos(){
        File ruta = getApplicationContext().getFilesDir();
        String nombreArch = "datosTareas.tpo";

        try {
            FileInputStream leeArch = new FileInputStream (new File(ruta,nombreArch));
            ObjectInputStream streamArch = new ObjectInputStream (leeArch);
            mLista = (ArrayList<String>) streamArch.readObject();
            streamArch.close();

            //customAdapter adaptador = new customAdapter(this,mLista);
            ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,mLista);
            lstView.setAdapter(adaptador);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapteiew, View view, int i, long l) {
        String tareaCompletada = mLista.get(i);
        if(! tareaCompletada.contains("Terminado")){
            tareaCompletada = tareaCompletada + " - Terminado";
            mLista.remove(i);
            mLista.add(tareaCompletada);
            ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,mLista);
            lstView.setAdapter(adaptador);
            guardarDatos(view);
        }
    }
}