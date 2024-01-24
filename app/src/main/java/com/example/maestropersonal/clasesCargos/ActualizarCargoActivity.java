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
import com.example.maestropersonal.entidades.Cargos;


public class ActualizarCargoActivity extends AppCompatActivity {

    EditText editarIdCargo,editarNombreCargo,editarEstadoCargo;
    AppCompatButton btnGuardarCargo,btnCancelarCargo,btnDesactivarCargo,btnActivarCargo;
    Cargos cargo;
    boolean correcto =false;
    int id=0;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_cargo);

        editarIdCargo =findViewById(R.id.editActualizarIdCargo);
        editarNombreCargo =findViewById(R.id.editActualizarNombreCargo);
        editarEstadoCargo =findViewById(R.id.editActualizarEstadoCargo);
        btnGuardarCargo = findViewById(R.id.btnActualizarGuardarCargo);
        btnCancelarCargo=findViewById(R.id.btnActualizarCancelarCargo);
        btnDesactivarCargo = findViewById(R.id.btnActualizarDesactivarCargo);
        btnActivarCargo = findViewById(R.id.btnActualizarActivarCargo);

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

        final DbCargos dbCargos = new DbCargos(ActualizarCargoActivity.this);
        cargo = dbCargos.verCargos(id);

        if(cargo!=null){
            editarIdCargo.setText(String.valueOf(cargo.getId()));
            editarNombreCargo.setText(cargo.getNombre());
            editarEstadoCargo.setText(cargo.getEstadoRegistro());
            editarIdCargo.setInputType(InputType.TYPE_NULL);
            editarEstadoCargo.setInputType(InputType.TYPE_NULL);


        }

        btnCancelarCargo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActualizarCargoActivity.this, LeerCargoActivity.class);
                startActivity(intent);
            }
        });
        btnGuardarCargo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editarNombreCargo.getText().toString().equals("") && !editarEstadoCargo.getText().toString().equals("")){
                    correcto =  dbCargos.actualizarCargos(id,editarNombreCargo.getText().toString(),editarEstadoCargo.getText().toString());
                    if(correcto){
                        Toast.makeText(ActualizarCargoActivity.this,"REGISTRO MODIFICADO",Toast.LENGTH_LONG).show();
                        VerListaCargos();
                    }else{
                        Toast.makeText(ActualizarCargoActivity.this,"ERROR AL  MODIFICADAR REGISTRO ",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(ActualizarCargoActivity.this,"LLENAR TODOS LOS CAMPOS: OBLIGATORIO",Toast.LENGTH_LONG).show();
                }

            }
        });
        btnDesactivarCargo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editarEstadoCargo.setText("D");
            }
        });
        btnActivarCargo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editarEstadoCargo.setText("A");
            }
        });
    }
    private void VerListaCargos(){
        Intent intent = new Intent(ActualizarCargoActivity.this, LeerCargoActivity.class);
        intent.putExtra("ID",id);
        startActivity(intent);

    }

}