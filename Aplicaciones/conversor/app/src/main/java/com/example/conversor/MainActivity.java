package com.example.conversor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    LongitudItem longitudItem = new LongitudItem();
    TemperaturaItem temperaturaItem = new TemperaturaItem();
    PesoItem pesoItem = new PesoItem();
    VolumenItem volumenItem = new VolumenItem();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedItem);

        loadFragment(longitudItem);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedItem = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.longitudItem:
                    loadFragment(longitudItem);
                    return true;
                case R.id.pesoItem:
                    loadFragment(pesoItem);
                    return true;
                case R.id.volumenItem:
                    loadFragment(volumenItem);
                    return true;
                case R.id.temperaturaItem:
                    loadFragment(temperaturaItem);
                    return true;
            }
            return false;
        }
    };

    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }
}