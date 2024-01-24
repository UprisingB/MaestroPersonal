package com.example.maestropersonal.clasesCargos;
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
import com.example.maestropersonal.adaptadores.LeerCargosAdapter;
import com.example.maestropersonal.db.DbCargos;
import com.example.maestropersonal.entidades.Cargos;
import java.util.ArrayList;

public class LeerCargoActivity extends AppCompatActivity {
    RecyclerView leerCargos;
    AppCompatButton btnCrearCargo;
    AppCompatButton btnSalirListadoCargo;
    ArrayList<Cargos> listaArrayCargos;
    Cargos cargos;
    int id=0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leer_cargo);

        leerCargos = findViewById(R.id.leerCargos);
        btnCrearCargo = findViewById(R.id.btnLeerCrearCargo);
        btnSalirListadoCargo = findViewById(R.id.btnLeerSalirCargo);

        leerCargos.setLayoutManager(new LinearLayoutManager(this));
        DbCargos dbCargos = new DbCargos(LeerCargoActivity.this);
        listaArrayCargos= new ArrayList<>();
        LeerCargosAdapter adapter = new LeerCargosAdapter(dbCargos.leerCargos());
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
    }
}