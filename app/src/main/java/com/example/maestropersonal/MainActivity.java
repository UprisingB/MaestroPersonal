package com.example.maestropersonal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.maestropersonal.ClasesPaises.LeerPaisActivity;
import com.example.maestropersonal.clasesCargos.LeerCargoActivity;
import com.example.maestropersonal.clasesDepartamentos.CrearDepartamentoActivity;
import com.example.maestropersonal.clasesDepartamentos.LeerDepartamentoActivity;

public class MainActivity extends AppCompatActivity {
    //Piero es guano
    AppCompatButton btnCrearBD;
    AppCompatButton btnDepartamentos;
    AppCompatButton btnPaises;
    AppCompatButton btnCargo;
    AppCompatButton btnPersonal;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        btnCrearBD = findViewById(R.id.btnCrearBD);
        btnCrearBD.setOnClickListener(new View.OnClickListener() {
          @Override
            public void onClick(View v) {
                DbHelper dbHelper= new DbHelper(MainActivity.this);
                SQLiteDatabase db =dbHelper.getWritableDatabase();
                if(db!=null){
                    Toast.makeText(MainActivity.this, "BASE DE DATOS CREADA", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "ERROR AL CREAR BASE DE DATOS", Toast.LENGTH_LONG).show();

                }

            }
        });*/
        btnDepartamentos = findViewById(R.id.btnDepartamento);
        btnDepartamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LeerDepartamentoActivity.class);
                startActivity(intent);


            }
        });
        btnCargo = findViewById(R.id.btnCargo);
        btnCargo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LeerCargoActivity.class);
                startActivity(intent);


            }
        });
        btnPaises= findViewById(R.id.btnPais);
        btnPaises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LeerPaisActivity.class);
                startActivity(intent);


            }
        });
        btnPersonal= findViewById(R.id.btnPersonal);
        btnPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CrearDepartamentoActivity.class);
                startActivity(intent);


            }
        });

    }


}