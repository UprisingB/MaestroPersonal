package com.example.maestropersonal.ClasesPaises;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.maestropersonal.MainActivity;
import com.example.maestropersonal.R;
import com.example.maestropersonal.adaptadores.LeerPaisesAdapter;
import com.example.maestropersonal.db.DbPaises;
import com.example.maestropersonal.entidades.Paises;
import java.util.ArrayList;

public class LeerPaisActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    RecyclerView leerPaises;
    AppCompatButton btnCrearPais,btnLeerActualizarPais;
    AppCompatButton btnSalirListadoPais;
    SearchView searchLeerPais;
    ArrayList<Paises> listaArrayPaises;
    LeerPaisesAdapter adapter;
    Paises paises;
    int id=0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leer_pais);
        btnLeerActualizarPais = findViewById(R.id.btnLeerActualizarPais);

        searchLeerPais =findViewById(R.id.searchLeerPais);

        leerPaises = findViewById(R.id.leerPaises);
        btnCrearPais = findViewById(R.id.btnLeerCrearPais);

        btnSalirListadoPais = findViewById(R.id.btnLeerSalirPais);

        leerPaises.setLayoutManager(new LinearLayoutManager(this));
        DbPaises dbPaises = new DbPaises(LeerPaisActivity.this);
        listaArrayPaises= new ArrayList<>();
        adapter = new LeerPaisesAdapter(dbPaises.leerPaises());
        leerPaises.setAdapter(adapter);


        btnCrearPais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeerPaisActivity.this, CrearPaisActivity.class);
                startActivity(intent);

            }
        });

        btnCrearPais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeerPaisActivity.this, CrearPaisActivity.class);
                startActivity(intent);

            }
        });
        btnLeerActualizarPais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnSalirListadoPais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeerPaisActivity.this, MainActivity.class);
                startActivity(intent);


            }
        });
        searchLeerPais.setOnQueryTextListener(this);



    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filtrado(newText);
        return false;
    }
}