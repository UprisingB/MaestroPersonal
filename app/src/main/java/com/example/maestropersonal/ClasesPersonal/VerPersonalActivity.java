package com.example.maestropersonal.ClasesPersonal;
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
import com.example.maestropersonal.db.DbPersonales;
import com.example.maestropersonal.entidades.Personales;


public class VerPersonalActivity extends AppCompatActivity {

    EditText editVerIdPersonal,editVerNombrePersonal,editVerEstadoPersonal,editVerDniPersonal,editVerFechaNacimientoPersonal,editVerPaisPersonal,editVerCargoPersonal,editVerDepartamentoPersonal;
    AppCompatButton btnActualizarPersonal,btnSalirPersonal,btnEliminarVerPersonal;
    Personales personales;
    int id=0;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_personal);

        editVerIdPersonal =findViewById(R.id.editVerIdPersonal2);
        editVerNombrePersonal =findViewById(R.id.editVerNombrePersonal2);
        editVerDniPersonal =findViewById(R.id.editVerDniPersonal2);
        editVerFechaNacimientoPersonal =findViewById(R.id.editVerFechaNacimientoPersonal2);
        editVerPaisPersonal =findViewById(R.id.editVerPaisPersonal2);
        editVerCargoPersonal =findViewById(R.id.editVerCargoPersonal2);
        editVerDepartamentoPersonal =findViewById(R.id.editVerDepartamentoPersonal2);
        editVerEstadoPersonal =findViewById(R.id.editVerEstadoPersonal2);
        btnActualizarPersonal = findViewById(R.id.btnVerActualizarPersonal2);
        btnSalirPersonal=findViewById(R.id.btnVerSalirPersonal2);
        btnEliminarVerPersonal=findViewById(R.id.btnVerEliminarPersonal2);

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

        DbPersonales dbPersonales= new DbPersonales(VerPersonalActivity.this);
        personales = dbPersonales.verPersonales(id);

        if(personales!=null){
            editVerIdPersonal.setText(String.valueOf(personales.getId()));
            editVerNombrePersonal.setText(personales.getNombre());
            editVerDniPersonal.setText(personales.getDni());
            editVerFechaNacimientoPersonal.setText(personales.getFechaNacimiento());


            String nombrePais = dbPersonales.obtenerNombrePaisPorId(personales.getPaisId());
            String nombreCargo = dbPersonales.obtenerNombreCargoPorId(personales.getCargoId());
            String nombreDepartamento = dbPersonales.obtenerNombreDepartamentoPorId(personales.getDepartamentoId());

            editVerPaisPersonal.setText(nombrePais);
            editVerCargoPersonal.setText(nombreCargo);
            editVerDepartamentoPersonal.setText(nombreDepartamento);


            editVerEstadoPersonal.setText(personales.getEstadoRegistro());





            editVerIdPersonal.setInputType(InputType.TYPE_NULL);
            editVerNombrePersonal.setInputType(InputType.TYPE_NULL);
            editVerDniPersonal.setInputType(InputType.TYPE_NULL);
            editVerFechaNacimientoPersonal.setInputType(InputType.TYPE_NULL);
            editVerPaisPersonal.setInputType(InputType.TYPE_NULL);
            editVerCargoPersonal.setInputType(InputType.TYPE_NULL);
            editVerDepartamentoPersonal.setInputType(InputType.TYPE_NULL);
            editVerEstadoPersonal.setInputType(InputType.TYPE_NULL);

        }

        btnSalirPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerPersonalActivity.this, LeerPersonalActivity.class);
                startActivity(intent);
            }
        });
        btnActualizarPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerPersonalActivity.this, ActualizarPersonalActivity.class);
                intent.putExtra("ID",id);
                startActivity(intent);
            }
        });
        btnEliminarVerPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerPersonalActivity.this);
                builder.setMessage("¿Desea Eliminar Este Personal de forma permanente?")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(dbPersonales.eliminarPersonal(id)){
                                    Toast.makeText(VerPersonalActivity.this,"Personal Eliminado Con Exito",Toast.LENGTH_LONG).show();
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
        Intent intent = new Intent(VerPersonalActivity.this,LeerPersonalActivity.class);
        startActivity(intent);
    }
}