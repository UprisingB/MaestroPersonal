package com.example.maestropersonal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maestropersonal.db.DbDepartamentos;
import com.example.maestropersonal.entidades.Departamentos;

public class VerDepartamentoActivity extends AppCompatActivity {

    EditText editVerIdDepartamento,editVerNombreDepartamento,editVerEstadoDepartamento;
    AppCompatButton btnActualizarDepartamento,btnSalirDepartamento,btnEliminarVerDepartamento;
    Departamentos departamento;
    int id=0;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_departamento);
        editVerIdDepartamento =findViewById(R.id.editarIdDepartamento);
        editVerNombreDepartamento =findViewById(R.id.editarNombreDepartamento);
        editVerEstadoDepartamento =findViewById(R.id.editarEstadoDepartamento);
        btnActualizarDepartamento = findViewById(R.id.btnActualizarDepartamento);
        btnSalirDepartamento=findViewById(R.id.btnSalirDepartamento);
        btnEliminarVerDepartamento=findViewById(R.id.btnEliminarVerDepartamento);

        if(savedInstanceState==null){
            Bundle extras = getIntent().getExtras();
            if(extras==null){
                id = Integer.parseInt(null);
            }else{
                id=extras.getInt("ID");
                Toast.makeText(this, "Error en update"+extras+"", Toast.LENGTH_SHORT).show();

            }

        }else{
            id = (int)savedInstanceState.getSerializable("ID");
        }

        DbDepartamentos dbDepartamentos = new DbDepartamentos(VerDepartamentoActivity.this);
        departamento = dbDepartamentos.verDepartamentos(id);

        if(departamento!=null){
            editVerIdDepartamento.setText(String.valueOf(departamento.getId()));
            editVerNombreDepartamento.setText(departamento.getNombre());
            editVerEstadoDepartamento.setText(departamento.getEstadoRegistro());
            editVerIdDepartamento.setInputType(InputType.TYPE_NULL);
            editVerNombreDepartamento.setInputType(InputType.TYPE_NULL);
            editVerEstadoDepartamento.setInputType(InputType.TYPE_NULL);

        }

        btnSalirDepartamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerDepartamentoActivity.this, LeerDepartamentoActivity.class);
                startActivity(intent);
            }
        });
        btnActualizarDepartamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerDepartamentoActivity.this, ActualizarDepartamentoActivity.class);
                intent.putExtra("ID",id);
                startActivity(intent);
            }
        });
        btnEliminarVerDepartamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerDepartamentoActivity.this);
                builder.setMessage("¿Desea Eliminar Esta Contacto de forma permanente?")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(dbDepartamentos.eliminarDepartamentos(id)){
                                    Toast.makeText(VerDepartamentoActivity.this,"Departamento Eliminado Con Exito",Toast.LENGTH_LONG).show();
                                    lista();

                                }

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();


            }
        });
    }
    private void  lista(){
        Intent intent = new Intent(VerDepartamentoActivity.this,LeerDepartamentoActivity.class);
        startActivity(intent);
    }
}