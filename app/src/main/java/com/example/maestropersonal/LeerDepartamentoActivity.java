package com.example.maestropersonal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.maestropersonal.adaptadores.LeerDepartamentosAdapter;
import com.example.maestropersonal.db.DbDepartamentos;
import com.example.maestropersonal.entidades.Departamentos;

import java.util.ArrayList;

public class LeerDepartamentoActivity extends AppCompatActivity {
    RecyclerView leerDepartamentos;
    AppCompatButton btnCrearDepartamento;
    AppCompatButton btnSalirListadoDespartamento;
    AppCompatButton btnActualizarDepartamento;
    AppCompatButton btnDesactivarListaDepartamento;
    ArrayList<Departamentos> listaArrayDepartamentos;
    Departamentos departamento;
    int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leer_departamento);

        leerDepartamentos = findViewById(R.id.leerDepartamentos);
        leerDepartamentos.setLayoutManager(new LinearLayoutManager(this));
        DbDepartamentos dbDepartamentos = new DbDepartamentos(LeerDepartamentoActivity.this);
        listaArrayDepartamentos= new ArrayList<>();
        LeerDepartamentosAdapter adapter = new LeerDepartamentosAdapter(dbDepartamentos.leerDepartamentos());
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


    }
}