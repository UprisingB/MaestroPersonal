package com.example.maestropersonal.clasesCargos;

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
import com.example.maestropersonal.db.DbCargos;

public class CrearCargoActivity extends AppCompatActivity {
    EditText txtNombreCargo,txtEstadoCargo;
    AppCompatButton btnGuardarCargo,btnCancelarCargo,btnDesactivarCargo,btnActivarCargo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cargo);
        txtNombreCargo = findViewById(R.id.editCrearNombreCargo);
        txtEstadoCargo = findViewById(R.id.editCrearEstadoCargo);
        btnGuardarCargo = findViewById(R.id.btnCrearGuardarCargo);
        btnDesactivarCargo = findViewById(R.id.btnCrearDesactivarCargo);
        btnActivarCargo = findViewById(R.id.btnCrearActivarCargo);
        btnCancelarCargo = findViewById(R.id.btnCrearCancelarCargo);
        txtEstadoCargo.setInputType(InputType.TYPE_NULL);
        btnGuardarCargo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbCargos dbCargos = new DbCargos(CrearCargoActivity.this);
                long id = dbCargos.crearCargo(txtNombreCargo.getText().toString(), txtEstadoCargo.getText().toString());

                if (id > 0) {
                    Toast.makeText(CrearCargoActivity.this, "Registro Guardado", Toast.LENGTH_LONG).show();
                    limpiar();
                    Intent intent = new Intent(CrearCargoActivity.this, LeerCargoActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(CrearCargoActivity.this, "Error al Guardar Registro", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnCancelarCargo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CrearCargoActivity.this, LeerCargoActivity.class);
                startActivity(intent);
            }
        });
        btnDesactivarCargo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtEstadoCargo.setText("D");
            }
        });
        btnActivarCargo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtEstadoCargo.setText("A");
            }
        });
    }

    private void limpiar(){
        txtNombreCargo.setText("");
        txtEstadoCargo.setText("");
    }
}