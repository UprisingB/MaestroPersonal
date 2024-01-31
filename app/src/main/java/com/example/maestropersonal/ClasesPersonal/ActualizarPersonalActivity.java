package com.example.maestropersonal.ClasesPersonal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import android.annotation.SuppressLint;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.maestropersonal.R;
import com.example.maestropersonal.db.DbPersonales;

import com.example.maestropersonal.entidades.Personales;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;


public class ActualizarPersonalActivity extends AppCompatActivity {

    EditText editActualizarIdPersonal ,editActualizarNombrePersonal, editActualizarDniPersonal, editActualizarEstadoPersonal;
    EditText editActualizarPaisPersonal,editActualizarCargoPersonal, editActualizarDepartamentoPersonal;
    Spinner spinnerActualizarPaisPersonal, spinnerActualizarCargoPersonal, spinnerActualizarDepartamentoPersonal;
    AppCompatButton btnActualizarGuardarPersonal, btnActualizarCancelarPersonal, btnActualizarActivarPersonal, btnActualizarDesactivarPersonal,btnActualizarEliminarPersonal;
    Personales personales;
    boolean correcto =false;
    int id = 0;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_personal);


        Spinner spinnerDepartamento = findViewById(R.id.spinnerActualizarDepartamentoPersonal3);
        Spinner spinnerCargo = findViewById(R.id.spinnerActualizarCargoPersonal3);
        Spinner spinnerPais = findViewById(R.id.spinnerActualizarPaisPersonal3);

        // Obtén tus datos de la base de datos
        DbPersonales dbPersonales = new DbPersonales(ActualizarPersonalActivity.this);
        ArrayList<String> departamentos = dbPersonales.leerDepartamentosNombres(); // Reemplaza con tu método específico
        ArrayList<String> cargos = dbPersonales.leerCargosNombres(); // Reemplaza con tu método específico
        ArrayList<String> paises = dbPersonales.leerPaisesNombres(); // Reemplaza con tu método específico

        // Crea adaptadores para los Spinners
        ArrayAdapter<String> adapterDepartamento = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, departamentos);
        ArrayAdapter<String> adapterCargo = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cargos);
        ArrayAdapter<String> adapterPais = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paises);

        // Establece los adaptadores a los Spinners
        spinnerDepartamento.setAdapter(adapterDepartamento);
        spinnerCargo.setAdapter(adapterCargo);
        spinnerPais.setAdapter(adapterPais);



        TextInputLayout tilFechaNacimiento = findViewById(R.id.tilActualizarFechaNacimientoPersonal3);
        final TextInputEditText editActualizarFechaNacimientoPersonal = findViewById(R.id.editActualizarFechaNacimientoPersonal3);
        Button btnFechaNacimiento = findViewById(R.id.btnActualizarFechaNacimientoPersonal3);

        btnFechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtén la fecha actual para mostrar en el DatePicker
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                // Crea el DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        ActualizarPersonalActivity.this, // Reemplaza con el nombre de tu actividad
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Actualiza el campo de fecha de nacimiento con la fecha seleccionada
                                String fechaSeleccionada = dayOfMonth + "/" + (month + 1) + "/" + year;
                                editActualizarFechaNacimientoPersonal.setText(fechaSeleccionada);
                            }
                        },
                        year,
                        month,
                        dayOfMonth
                );

                // Muestra el DatePickerDialog
                datePickerDialog.show();
            }




        });





        editActualizarIdPersonal =findViewById(R.id.editActualizarIdPersonal3);
        editActualizarNombrePersonal =findViewById(R.id.editActualizarNombrePersonal3);
        editActualizarDniPersonal =findViewById(R.id.editActualizarDniPersonal3);
        spinnerActualizarPaisPersonal =findViewById(R.id.spinnerActualizarPaisPersonal3);
        spinnerActualizarCargoPersonal =findViewById(R.id.spinnerActualizarCargoPersonal3);
        spinnerActualizarDepartamentoPersonal =findViewById(R.id.spinnerActualizarDepartamentoPersonal3);
        editActualizarEstadoPersonal =findViewById(R.id.editActualizarEstadoPersonal3);
        btnActualizarGuardarPersonal = findViewById(R.id.btnActualizarGuardarPersonal3);
        btnActualizarCancelarPersonal=findViewById(R.id.btnActualizarCancelarPersonal3);
        editActualizarIdPersonal.setInputType(InputType.TYPE_NULL);
        editActualizarEstadoPersonal.setInputType(InputType.TYPE_NULL);


        if(savedInstanceState==null){
            Bundle extras = getIntent().getExtras();
            if(extras==null){
                id = Integer.parseInt(null);
                Toast.makeText(this, "cero"+extras+"", Toast.LENGTH_SHORT).show();
            }else{
                id=extras.getInt("ID");
                Toast.makeText(this, "Error en update"+extras+"", Toast.LENGTH_SHORT).show();

            }

        }else{
            id = (int)savedInstanceState.getSerializable("ID");
        }

        personales = dbPersonales.verPersonales(id);

        if (personales != null) {
            editActualizarIdPersonal.setText(String.valueOf(personales.getId()));
            editActualizarNombrePersonal.setText(personales.getNombre());
            editActualizarDniPersonal.setText(personales.getDni());
            editActualizarFechaNacimientoPersonal.setText(personales.getFechaNacimiento());


            String nombrePais = dbPersonales.obtenerNombrePaisPorId(personales.getPaisId());
            String nombreCargo = dbPersonales.obtenerNombreCargoPorId(personales.getCargoId());
            String nombreDepartamento = dbPersonales.obtenerNombreDepartamentoPorId(personales.getDepartamentoId());

      /*      spinnerActualizarPaisPersonal.setId(personales.getPaisId());
            spinnerActualizarCargoPersonal.setId(personales.getCargoId());
            spinnerActualizarDepartamentoPersonal.setId(personales.getDepartamentoId());
            editActualizarEstadoPersonal.setText(personales.getEstadoRegistro());*/


            seleccionarItemSpinner(spinnerActualizarPaisPersonal, nombrePais);
            seleccionarItemSpinner(spinnerActualizarCargoPersonal, nombreCargo);
            seleccionarItemSpinner(spinnerActualizarDepartamentoPersonal, nombreDepartamento);
            editActualizarEstadoPersonal.setText(personales.getEstadoRegistro());

            editActualizarFechaNacimientoPersonal.setInputType(InputType.TYPE_NULL);



        }
        // Restricción de entrada para la fecha de nacimiento



        btnActualizarCancelarPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActualizarPersonalActivity.this, LeerPersonalActivity.class);
                startActivity(intent);
            }
        });
        btnActualizarGuardarPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int idPais = dbPersonales.obtenerIdPais(spinnerActualizarPaisPersonal.getSelectedItem().toString());
                int idCargo = dbPersonales.obtenerIdCargo(spinnerActualizarCargoPersonal.getSelectedItem().toString());
                int idDepartamento = dbPersonales.obtenerIdDepartamento(spinnerActualizarDepartamentoPersonal.getSelectedItem().toString());

                if(!editActualizarNombrePersonal.getText().toString().equals("")&&
                        !editActualizarDniPersonal.getText().toString().equals("")&&
                        !editActualizarFechaNacimientoPersonal.getText().toString().equals("")&&
                        !spinnerActualizarPaisPersonal.getSelectedItem().toString().equals("")&&
                        !spinnerActualizarCargoPersonal.getSelectedItem().toString().equals("")&&
                        !spinnerActualizarDepartamentoPersonal.getSelectedItem().toString().equals("")&&
                        !editActualizarEstadoPersonal.getText().toString().equals("")){

                   correcto =  dbPersonales.actualizarPersonales(id,
                           editActualizarNombrePersonal.getText().toString(),
                           editActualizarDniPersonal.getText().toString(),
                           editActualizarFechaNacimientoPersonal.getText().toString(),
                           idPais,
                           idCargo,
                           idDepartamento,
                           editActualizarEstadoPersonal.getText().toString());
                    if(correcto){
                        Toast.makeText(ActualizarPersonalActivity.this,"REGISTRO MODIFICADO",Toast.LENGTH_LONG).show();
                        lista();
                    }else{
                        Toast.makeText(ActualizarPersonalActivity.this,"ERROR AL  MODIFICADAR REGISTRO ",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(ActualizarPersonalActivity.this,"LLENAR TODOS LOS CAMPOS: OBLIGATORIO",Toast.LENGTH_LONG).show();
                }

            }
        });
        btnActualizarEliminarPersonal = findViewById(R.id.btnActualizarEliminarPersonal3);
        btnActualizarEliminarPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    editActualizarEstadoPersonal.setText("*");

            }
        });
        btnActualizarActivarPersonal = findViewById(R.id.btnActualizarActivarPersonal3);
        btnActualizarActivarPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editActualizarEstadoPersonal.setText("A");

            }
        });

        btnActualizarDesactivarPersonal = findViewById(R.id.btnActualizarDesactivarPersonal3);
        btnActualizarDesactivarPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editActualizarEstadoPersonal.setText("D");

            }
        });

    }

    private void  lista(){
        Intent intent = new Intent(ActualizarPersonalActivity.this,LeerPersonalActivity.class);
        startActivity(intent);
    }
    private void seleccionarItemSpinner(Spinner spinner, String nombreItem) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(nombreItem)) {
                spinner.setSelection(i);
                break;
            }
        }
    }


}