package com.example.maestropersonal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maestropersonal.db.DbDepartamentos;

public class CrearDepartamentoActivity extends AppCompatActivity {
    EditText txtNombreDepartamento,txtEstadoDepartamento;
    AppCompatButton btnGuardarDepartamento,btnCancelarDepartamento;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_departamento);
        txtNombreDepartamento = findViewById(R.id.editarNombreDepartamento);
        txtEstadoDepartamento = findViewById(R.id.editarEstadoDepartamento);
        btnGuardarDepartamento = findViewById(R.id.btnGuardarDepartamento);
        btnGuardarDepartamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbDepartamentos dbDepartamentos = new DbDepartamentos(CrearDepartamentoActivity.this);
                long id = dbDepartamentos.crearDepartamento(txtNombreDepartamento.getText().toString(), txtEstadoDepartamento.getText().toString());

                if (id > 0) {
                    Toast.makeText(CrearDepartamentoActivity.this, "Registro Guardado", Toast.LENGTH_LONG).show();
                    limpiar();
                    Intent intent = new Intent(CrearDepartamentoActivity.this, LeerDepartamentoActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(CrearDepartamentoActivity.this, "Error al Guardar Registro", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnCancelarDepartamento = findViewById(R.id.btnSalirDepartamento);
        btnCancelarDepartamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CrearDepartamentoActivity.this, LeerDepartamentoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void limpiar(){
        txtNombreDepartamento.setText("");
        txtEstadoDepartamento.setText("");
    }
}