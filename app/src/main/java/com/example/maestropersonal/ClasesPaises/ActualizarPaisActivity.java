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
import com.example.maestropersonal.entidades.Paises;


public class ActualizarPaisActivity extends AppCompatActivity {

    EditText editarIdPais,editarNombrePais,editarEstadoPais;
    AppCompatButton btnGuardarPais,btnCancelarPais,btnDesactivarPais,btnActivarPais,btnEliminarPais;
    Paises pais;
    boolean correcto =false;
    int id=0;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_pais);

        editarIdPais =findViewById(R.id.editActualizarIdPais);
        editarNombrePais =findViewById(R.id.editActualizarNombrePais);
        editarEstadoPais =findViewById(R.id.editActualizarEstadoPais);
        btnGuardarPais = findViewById(R.id.btnActualizarGuardarPais);
        btnCancelarPais=findViewById(R.id.btnActualizarCancelarPais);
        btnDesactivarPais = findViewById(R.id.btnActualizarDesactivarPais);
        btnActivarPais = findViewById(R.id.btnActualizarActivarPais);
        btnEliminarPais = findViewById(R.id.btnActualizarEliminarPais);

        if(savedInstanceState==null){
            Bundle extras = getIntent().getExtras();
            if (extras == null ) {
                id =Integer.parseInt(null);
                Toast.makeText(this, "Error en update"+extras+"", Toast.LENGTH_SHORT).show();


            }else{
                id=extras.getInt("ID");
            }

        }else{
            id = (int)savedInstanceState.getSerializable("ID");
        }

        final DbPaises dbPaises = new DbPaises(ActualizarPaisActivity.this);
        pais = dbPaises.verPaises(id);

        if(pais!=null){
            editarIdPais.setText(String.valueOf(pais.getId()));
            editarNombrePais.setText(pais.getNombre());
            editarEstadoPais.setText(pais.getEstadoRegistro());
            editarIdPais.setInputType(InputType.TYPE_NULL);
            editarEstadoPais.setInputType(InputType.TYPE_NULL);


        }

        btnCancelarPais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActualizarPaisActivity.this, LeerPaisActivity.class);
                startActivity(intent);
            }
        });
        btnGuardarPais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editarNombrePais.getText().toString().equals("") && !editarEstadoPais.getText().toString().equals("")){
                    correcto =  dbPaises.actualizarPaises(id,editarNombrePais.getText().toString(),editarEstadoPais.getText().toString());
                    if(correcto){
                        Toast.makeText(ActualizarPaisActivity.this,"REGISTRO MODIFICADO",Toast.LENGTH_LONG).show();
                        VerListaPaises();
                    }else{
                        Toast.makeText(ActualizarPaisActivity.this,"ERROR AL  MODIFICADAR REGISTRO ",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(ActualizarPaisActivity.this,"LLENAR TODOS LOS CAMPOS: OBLIGATORIO",Toast.LENGTH_LONG).show();
                }

            }
        });
        btnDesactivarPais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editarEstadoPais.setText("D");
            }
        });
        btnActivarPais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editarEstadoPais.setText("A");
            }
        });
        btnEliminarPais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editarEstadoPais.setText("*");
            }
        });
    }
    private void VerListaPaises(){
        Intent intent = new Intent(ActualizarPaisActivity.this, LeerPaisActivity.class);
        intent.putExtra("ID",id);
        startActivity(intent);

    }

}