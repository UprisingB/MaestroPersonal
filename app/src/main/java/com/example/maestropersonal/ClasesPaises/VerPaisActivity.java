package com.example.maestropersonal.ClasesPaises;
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

import com.example.maestropersonal.R;
import com.example.maestropersonal.db.DbPaises;
import com.example.maestropersonal.entidades.Paises;


public class VerPaisActivity extends AppCompatActivity {

    EditText editVerIdPais,editVerNombrePais,editVerEstadoPais;
    AppCompatButton btnActualizarPais,btnSalirPais,btnEliminarVerPais;
    Paises pais;
    int id=0;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_pais);
        editVerIdPais =findViewById(R.id.editVerIdPais);
        editVerNombrePais =findViewById(R.id.editVerNombrePais);
        editVerEstadoPais =findViewById(R.id.editVerEstadoPais);
        btnActualizarPais = findViewById(R.id.btnVerActualizarPais);
        btnSalirPais=findViewById(R.id.btnVerSalirPais);
        btnEliminarVerPais=findViewById(R.id.btnVerEliminarPais);

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

        DbPaises dbPaises = new DbPaises(VerPaisActivity.this);
        pais = dbPaises.verPaises(id);

        if(pais!=null){
            editVerIdPais.setText(String.valueOf(pais.getId()));
            editVerNombrePais.setText(pais.getNombre());
            editVerEstadoPais.setText(pais.getEstadoRegistro());
            editVerIdPais.setInputType(InputType.TYPE_NULL);
            editVerNombrePais.setInputType(InputType.TYPE_NULL);
            editVerEstadoPais.setInputType(InputType.TYPE_NULL);

        }

        btnSalirPais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerPaisActivity.this, LeerPaisActivity.class);
                startActivity(intent);
            }
        });
        btnActualizarPais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerPaisActivity.this, ActualizarPaisActivity.class);
                intent.putExtra("ID",id);
                startActivity(intent);
            }
        });
        btnEliminarVerPais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerPaisActivity.this);
                builder.setMessage("¿Desea Eliminar Este Contacto de forma permanente?")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(dbPaises.eliminarPaises(id)){
                                    Toast.makeText(VerPaisActivity.this,"Pais Eliminado Con Exito",Toast.LENGTH_LONG).show();
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
        Intent intent = new Intent(VerPaisActivity.this,LeerPaisActivity.class);
        startActivity(intent);
    }
}