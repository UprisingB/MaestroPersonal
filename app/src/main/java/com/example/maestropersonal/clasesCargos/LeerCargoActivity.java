package com.example.maestropersonal.clasesCargos;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.maestropersonal.MainActivity;
import com.example.maestropersonal.R;
import com.example.maestropersonal.adaptadores.LeerCargosAdapter;
import com.example.maestropersonal.adaptadores.LeerPaisesAdapter;
import com.example.maestropersonal.db.DbCargos;
import com.example.maestropersonal.entidades.Cargos;
import com.example.maestropersonal.entidades.Paises;

import java.util.ArrayList;

public class LeerCargoActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    RecyclerView leerCargos;
    AppCompatButton btnCrearCargo;
    AppCompatButton btnSalirListadoCargo;
    ArrayList<Cargos> listaArrayCargos;
    SearchView searchLeerCargo;
    LeerCargosAdapter adapter;
    Cargos cargos;
    int id=0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leer_cargo);

        searchLeerCargo =findViewById(R.id.searchLeerCargo);

        leerCargos = findViewById(R.id.leerCargos);
        btnCrearCargo = findViewById(R.id.btnLeerCrearCargo);
        btnSalirListadoCargo = findViewById(R.id.btnLeerSalirCargo);

        leerCargos.setLayoutManager(new LinearLayoutManager(this));
        DbCargos dbCargos = new DbCargos(LeerCargoActivity.this);
        listaArrayCargos= new ArrayList<>();
        adapter = new LeerCargosAdapter(dbCargos.leerCargos());
        leerCargos.setAdapter(adapter);


        btnCrearCargo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeerCargoActivity.this, CrearCargoActivity.class);
                startActivity(intent);


            }
        });



        btnSalirListadoCargo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeerCargoActivity.this, MainActivity.class);
                startActivity(intent);


            }
        });
        searchLeerCargo.setOnQueryTextListener(this);



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