package com.example.maestropersonal.ClasesPaises;
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
import com.example.maestropersonal.db.DbPaises;
import com.example.maestropersonal.entidades.Paises;
import java.util.ArrayList;

public class LeerPaisActivity extends AppCompatActivity {
    RecyclerView leerPaises;
    AppCompatButton btnCrearPais;
    AppCompatButton btnSalirListadoPais;
    ArrayList<Paises> listaArrayPaises;
    Paises paises;
    int id=0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leer_pais);

        leerPaises = findViewById(R.id.leerPaises);
        btnCrearPais = findViewById(R.id.btnLeerCrearPais);
        btnSalirListadoPais = findViewById(R.id.btnLeerSalirPais);

        leerPaises.setLayoutManager(new LinearLayoutManager(this));
        DbPaises dbPaises = new DbPaises(LeerPaisActivity.this);
        listaArrayPaises= new ArrayList<>();
        LeerPaisesAdapter adapter = new LeerPaisesAdapter(dbPaises.leerPaises());
        leerPaises.setAdapter(adapter);


        btnCrearPais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeerPaisActivity.this, CrearPaisActivity.class);
                startActivity(intent);


            }
        });



        btnSalirListadoPais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeerPaisActivity.this, MainActivity.class);
                startActivity(intent);


            }
        });
    }
}