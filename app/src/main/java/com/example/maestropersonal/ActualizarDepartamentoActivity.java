package com.example.maestropersonal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maestropersonal.db.DbDepartamentos;
import com.example.maestropersonal.entidades.Departamentos;

public class ActualizarDepartamentoActivity extends AppCompatActivity {

    EditText editarIdDepartamento,editarNombreDepartamento,editarEstadoDepartamento;
    AppCompatButton btnGuardarDepartamento,btnCancelarDepartamento;
    Departamentos departamento;
    boolean correcto =false;
    int id=0;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_departamento);

        editarIdDepartamento =findViewById(R.id.editarIdDepartamento);
        editarNombreDepartamento =findViewById(R.id.editarNombreDepartamento);
        editarEstadoDepartamento =findViewById(R.id.editarEstadoDepartamento);
        btnGuardarDepartamento = findViewById(R.id.btnGuardarDepartamento);
        btnCancelarDepartamento=findViewById(R.id.btnCancelarDepartamento);

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

       final DbDepartamentos dbDepartamentos = new DbDepartamentos(ActualizarDepartamentoActivity.this);
        departamento = dbDepartamentos.verDepartamentos(id);

        if(departamento!=null){
            editarIdDepartamento.setText(String.valueOf(departamento.getId()));
            editarNombreDepartamento.setText(departamento.getNombre());
            editarEstadoDepartamento.setText(departamento.getEstadoRegistro());
            editarIdDepartamento.setInputType(InputType.TYPE_NULL);


        }

        btnCancelarDepartamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActualizarDepartamentoActivity.this, LeerDepartamentoActivity.class);
                startActivity(intent);
            }
        });
        btnGuardarDepartamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editarNombreDepartamento.getText().toString().equals("") && !editarEstadoDepartamento.getText().toString().equals("")){
                   correcto =  dbDepartamentos.actualizarDepartamentos(id,editarNombreDepartamento.getText().toString(),editarEstadoDepartamento.getText().toString());
                    if(correcto){
                        Toast.makeText(ActualizarDepartamentoActivity.this,"REGISTRO MODIFICADO",Toast.LENGTH_LONG).show();
                        VerListaDepartamentos();
                    }else{
                        Toast.makeText(ActualizarDepartamentoActivity.this,"ERROR AL  MODIFICADAR REGISTRO ",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(ActualizarDepartamentoActivity.this,"LLENAR TODOS LOS CAMPOS: OBLIGATORIO",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    private void VerListaDepartamentos(){
        Intent intent = new Intent(ActualizarDepartamentoActivity.this, LeerDepartamentoActivity.class);
        intent.putExtra("ID",id);
        startActivity(intent);

    }

}