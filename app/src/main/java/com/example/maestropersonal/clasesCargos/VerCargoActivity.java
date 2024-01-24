package com.example.maestropersonal.clasesCargos;
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
import com.example.maestropersonal.db.DbCargos;
import com.example.maestropersonal.entidades.Cargos;


public class VerCargoActivity extends AppCompatActivity {

    EditText editVerIdCargo,editVerNombreCargo,editVerEstadoCargo;
    AppCompatButton btnActualizarCargo,btnSalirCargo,btnEliminarVerCargo;
    Cargos cargo;
    int id=0;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_cargo);
        editVerIdCargo =findViewById(R.id.editVerIdCargo);
        editVerNombreCargo =findViewById(R.id.editVerNombreCargo);
        editVerEstadoCargo =findViewById(R.id.editVerEstadoCargo);
        btnActualizarCargo = findViewById(R.id.btnVerActualizarCargo);
        btnSalirCargo=findViewById(R.id.btnVerSalirCargo);
        btnEliminarVerCargo=findViewById(R.id.btnVerEliminarCargo);

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

        DbCargos dbCargos = new DbCargos(VerCargoActivity.this);
        cargo = dbCargos.verCargos(id);

        if(cargo!=null){
            editVerIdCargo.setText(String.valueOf(cargo.getId()));
            editVerNombreCargo.setText(cargo.getNombre());
            editVerEstadoCargo.setText(cargo.getEstadoRegistro());
            editVerIdCargo.setInputType(InputType.TYPE_NULL);
            editVerNombreCargo.setInputType(InputType.TYPE_NULL);
            editVerEstadoCargo.setInputType(InputType.TYPE_NULL);

        }

        btnSalirCargo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerCargoActivity.this, LeerCargoActivity.class);
                startActivity(intent);
            }
        });
        btnActualizarCargo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerCargoActivity.this, ActualizarCargoActivity.class);
                intent.putExtra("ID",id);
                startActivity(intent);
            }
        });
        btnEliminarVerCargo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerCargoActivity.this);
                builder.setMessage("¿Desea Eliminar Esta Contacto de forma permanente?")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(dbCargos.eliminarCargo(id)){
                                    Toast.makeText(VerCargoActivity.this,"Cargo Eliminado Con Exito",Toast.LENGTH_LONG).show();
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
        Intent intent = new Intent(VerCargoActivity.this,LeerCargoActivity.class);
        startActivity(intent);
    }
}