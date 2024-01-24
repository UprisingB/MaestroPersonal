package com.example.maestropersonal.ClasesPaises;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maestropersonal.R;
import com.example.maestropersonal.db.DbPaises;

public class CrearPaisActivity extends AppCompatActivity {
    EditText txtNombrePais,txtEstadoPais;
    AppCompatButton btnGuardarPais,btnCancelarPais,btnDesactivarPais,btnActivarPais;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_pais);
        txtNombrePais = findViewById(R.id.editCrearNombrePais);
        txtEstadoPais = findViewById(R.id.editCrearEstadoPais);
        btnGuardarPais = findViewById(R.id.btnCrearGuardarPais);
        btnDesactivarPais = findViewById(R.id.btnCrearDesactivarPais);
        btnActivarPais = findViewById(R.id.btnCrearActivarPais);
        btnCancelarPais = findViewById(R.id.btnCrearCancelarPais);
        txtEstadoPais.setInputType(InputType.TYPE_NULL);
        btnGuardarPais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbPaises dbPaises = new DbPaises(CrearPaisActivity.this);
                long id = dbPaises.crearPaises(txtNombrePais.getText().toString(), txtEstadoPais.getText().toString());

                if (id > 0) {
                    Toast.makeText(CrearPaisActivity.this, "Registro Guardado", Toast.LENGTH_LONG).show();
                    limpiar();
                    Intent intent = new Intent(CrearPaisActivity.this, LeerPaisActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(CrearPaisActivity.this, "Error al Guardar Registro", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnCancelarPais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CrearPaisActivity.this, LeerPaisActivity.class);
                startActivity(intent);
            }
        });
        btnDesactivarPais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtEstadoPais.setText("D");
            }
        });
        btnActivarPais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtEstadoPais.setText("A");
            }
        });
    }

    private void limpiar(){
        txtNombrePais.setText("");
        txtEstadoPais.setText("");
    }
}