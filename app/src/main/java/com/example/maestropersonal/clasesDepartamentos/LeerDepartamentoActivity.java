package com.example.maestropersonal.clasesDepartamentos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.maestropersonal.MainActivity;
import com.example.maestropersonal.R;
import com.example.maestropersonal.adaptadores.LeerDepartamentosAdapter;
import com.example.maestropersonal.db.DbDepartamentos;
import com.example.maestropersonal.entidades.Departamentos;

import java.util.ArrayList;

public class LeerDepartamentoActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    RecyclerView leerDepartamentos;
    AppCompatButton btnCrearDepartamento;
    AppCompatButton btnSalirListadoDespartamento;
    AppCompatButton btnActualizarListaDepartamento;
    AppCompatButton btnDesactivarListaDepartamento;
    SearchView searchLeerDepartamento;
    ArrayList<Departamentos> listaArrayDepartamentos;
    LeerDepartamentosAdapter adapter;
    Departamentos departamento;
    int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leer_departamento);

        searchLeerDepartamento =findViewById(R.id.searchLeerDepartamento);

        leerDepartamentos = findViewById(R.id.leerDepartamentos);
        leerDepartamentos.setLayoutManager(new LinearLayoutManager(this));
        DbDepartamentos dbDepartamentos = new DbDepartamentos(LeerDepartamentoActivity.this);
        listaArrayDepartamentos= new ArrayList<>();

        //haciendo publico el adapter
        adapter = new LeerDepartamentosAdapter(dbDepartamentos.leerDepartamentos());
        leerDepartamentos.setAdapter(adapter);

        btnCrearDepartamento = findViewById(R.id.btnCrearDepartamento);
        btnCrearDepartamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeerDepartamentoActivity.this, CrearDepartamentoActivity.class);
                startActivity(intent);


            }
        });


        btnSalirListadoDespartamento = findViewById(R.id.btnSalirListaDepartamento);
        btnSalirListadoDespartamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeerDepartamentoActivity.this, MainActivity.class);
                startActivity(intent);


            }
        });

        searchLeerDepartamento.setOnQueryTextListener(this);



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