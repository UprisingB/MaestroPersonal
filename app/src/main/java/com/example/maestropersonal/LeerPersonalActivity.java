package com.example.maestropersonal;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.maestropersonal.MainActivity;
import com.example.maestropersonal.R;
import com.example.maestropersonal.adaptadores.LeerPaisesAdapter;
import com.example.maestropersonal.adaptadores.LeerPersonalesAdapter;
import com.example.maestropersonal.db.DbPaises;
import com.example.maestropersonal.db.DbPersonales;
import com.example.maestropersonal.entidades.Paises;
import com.example.maestropersonal.entidades.Personales;

import java.util.ArrayList;

public class LeerPersonalActivity extends AppCompatActivity {
    RecyclerView leerPersonales;
    AppCompatButton btnCrearPersonal;
    AppCompatButton btnSalirListadoPersonal;
    ArrayList<Personales> listaArrayPersonales;
    Personales personales;
    int id=0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leer_personal);

        leerPersonales = findViewById(R.id.leerPersonal);
        btnCrearPersonal = findViewById(R.id.btnLeerCrearPersonal);
        btnSalirListadoPersonal = findViewById(R.id.btnLeerSalirPersonal);

        leerPersonales.setLayoutManager(new LinearLayoutManager(this));
        DbPersonales dbPersonales = new DbPersonales(LeerPersonalActivity.this);
        listaArrayPersonales= new ArrayList<>();
        LeerPersonalesAdapter adapter = new LeerPersonalesAdapter(dbPersonales.leerPersonales());
        leerPersonales.setAdapter(adapter);


        btnCrearPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeerPersonalActivity.this, CrearPersonalActivity.class);
                startActivity(intent);


            }
        });



        btnSalirListadoPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeerPersonalActivity.this, MainActivity.class);
                startActivity(intent);


            }
        });
    }
}