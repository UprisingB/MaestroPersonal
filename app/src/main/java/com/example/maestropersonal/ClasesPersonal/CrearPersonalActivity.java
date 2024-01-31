package com.example.maestropersonal.ClasesPersonal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.maestropersonal.R;
import com.example.maestropersonal.db.DbPersonales;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;

public class CrearPersonalActivity extends AppCompatActivity {


    EditText txtEstadoPersonal,txtCrearNombrePersonal,txtCrearDniPersonal,txtCrearFechaNacimientoPersonal1;
    AppCompatButton btnGuardarPersonal,btnCancelarPersonal,btnDesactivarPersonal,btnActivarPersonal;


    Spinner txtSpinnerCrearPaisPersonal,txtSpinnerCrearCargoPersonal,txtSpinnerCrearDepartamentoPersonal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_personal);

        // Obtén referencias a tus Spinners
        Spinner spinnerDepartamento = findViewById(R.id.spinnerCrearDepartamentoPersonal);
        Spinner spinnerCargo = findViewById(R.id.spinnerCrearCargoPersonal);
        Spinner spinnerPais = findViewById(R.id.spinnerCrearPaisPersonal);

        // Obtén tus datos de la base de datos
        DbPersonales dbPersonales = new DbPersonales(CrearPersonalActivity.this);
        ArrayList<String> departamentos = dbPersonales.leerDepartamentosNombres();
        ArrayList<String> cargos = dbPersonales.leerCargosNombres();
        ArrayList<String> paises = dbPersonales.leerPaisesNombres();

        // Agrega una cadena vacía al principio de las listas de datos
        departamentos.add(0, "");
        cargos.add(0, "");
        paises.add(0, "");

        // Crea adaptadores para los Spinners
        ArrayAdapter<String> adapterDepartamento = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, departamentos);
        ArrayAdapter<String> adapterCargo = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cargos);
        ArrayAdapter<String> adapterPais = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paises);

        // Establece los adaptadores a los Spinners
        spinnerDepartamento.setAdapter(adapterDepartamento);
        spinnerCargo.setAdapter(adapterCargo);
        spinnerPais.setAdapter(adapterPais);

        // Agrega un listener al Spinner de País
        spinnerPais.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Verifica si se seleccionó una opción válida (distinta de la cadena vacía)
                if (position != 0) {
                    // Realiza tu lógica aquí para mostrar los datos correspondientes al país seleccionado
                    String selectedCountry = (String) parent.getItemAtPosition(position);
                    // Por ejemplo:
                    Toast.makeText(CrearPersonalActivity.this, "Seleccionaste: " + selectedCountry, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No es necesario realizar ninguna acción aquí
            }
        });



        TextInputLayout tilFechaNacimiento = findViewById(R.id.tilCrearFechaNacimientoPersonal);
        final TextInputEditText editFechaNacimiento = findViewById(R.id.editCrearFechaNacimientoPersonal1);
        Button btnFechaNacimiento = findViewById(R.id.btnCrearFechaNacimientoPersonal1);

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
                        CrearPersonalActivity.this, // Reemplaza con el nombre de tu actividad
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Actualiza el campo de fecha de nacimiento con la fecha seleccionada
                                String fechaSeleccionada = dayOfMonth + "/" + (month + 1) + "/" + year;
                                editFechaNacimiento.setText(fechaSeleccionada);
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




        txtCrearNombrePersonal = findViewById(R.id.editCrearNombrePersonal);
        txtCrearDniPersonal = findViewById(R.id.editCrearDniPersonal);
        txtCrearFechaNacimientoPersonal1 = findViewById(R.id.editCrearFechaNacimientoPersonal1);
        txtSpinnerCrearPaisPersonal = findViewById(R.id.spinnerCrearPaisPersonal);
        txtSpinnerCrearCargoPersonal = findViewById(R.id.spinnerCrearCargoPersonal);
        txtSpinnerCrearDepartamentoPersonal = findViewById(R.id.spinnerCrearDepartamentoPersonal);


        txtEstadoPersonal = findViewById(R.id.editCrearEstadoPersonal);
        btnGuardarPersonal = findViewById(R.id.btnCrearGuardarPersonal);
        btnDesactivarPersonal = findViewById(R.id.btnCrearDesactivarPersonal);
        btnActivarPersonal = findViewById(R.id.btnCrearActivarPersonal);
        btnCancelarPersonal = findViewById(R.id.btnCrearCancelarPersonal);
        txtEstadoPersonal.setText("A");
        txtEstadoPersonal.setInputType(InputType.TYPE_NULL);
        btnGuardarPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbPersonales dbPersonales = new DbPersonales(CrearPersonalActivity.this);

                int idPais = dbPersonales.obtenerIdPais(txtSpinnerCrearPaisPersonal.getSelectedItem().toString());
                int idCargo = dbPersonales.obtenerIdCargo(txtSpinnerCrearCargoPersonal.getSelectedItem().toString());
                int idDepartamento = dbPersonales.obtenerIdDepartamento(txtSpinnerCrearDepartamentoPersonal.getSelectedItem().toString());
                long id = dbPersonales.crearPersonales(
                        txtCrearNombrePersonal.getText( ).toString(),
                        txtCrearDniPersonal.getText().toString(),
                        txtCrearFechaNacimientoPersonal1.getText().toString(),
                        idPais,
                        idCargo,
                        idDepartamento,
                        txtEstadoPersonal.getText().toString());

                if (id > 0) {
                    Toast.makeText(CrearPersonalActivity.this, "Registro Guardado", Toast.LENGTH_LONG).show();
                    limpiar();
                    Intent intent = new Intent(CrearPersonalActivity.this, LeerPersonalActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(CrearPersonalActivity.this, "Error al Guardar Registro", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnCancelarPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CrearPersonalActivity.this, LeerPersonalActivity.class);
                startActivity(intent);
            }
        });
        btnDesactivarPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtEstadoPersonal.setText("D");
            }
        });
        btnActivarPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtEstadoPersonal.setText("A");
            }
        });
    }

    private void limpiar(){
        txtCrearNombrePersonal.setText("");
        txtEstadoPersonal.setText("");
    }

}