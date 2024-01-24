package com.example.maestropersonal.db;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;;
import androidx.annotation.Nullable;

import com.example.maestropersonal.entidades.Cargos;
import com.example.maestropersonal.entidades.Departamentos;
import com.example.maestropersonal.entidades.Personales;

import java.util.ArrayList;

public class DbPersonales extends DbHelper{
    Context context;
    public DbPersonales(@Nullable Context context) {
        super(context);
        this.context=context;

    }

    public long crearPersonales(String nombre, String dni, String fechaNacimiento,
                           int paisId, int cargoId, int departamentoId,
                           String estado_registro){
        long id=0;
        try {

            DbHelper dbHelper=new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues  values= new ContentValues();
            values.put("nombre", nombre);
            values.put("dni", dni);
            values.put("fecha_nacimiento", fechaNacimiento);
            values.put("pais_id", paisId);
            values.put("cargo_id", cargoId);
            values.put("departamento_id", departamentoId);
            values.put("estado_registro", estado_registro);

            id=db.insert(TABLE_MAESTRO_PERSONAL,null,values);

        }catch (Exception ex){
            ex.toString();

        }
        return id;

    }
    public ArrayList<Personales> leerPersonales(){


        DbHelper dbHelper=new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Personales> listaPersonales = new ArrayList<>();
        Personales personales = null;
        Cursor cursorPersonales=null;
        cursorPersonales = db.rawQuery("SELECT * FROM " + TABLE_MAESTRO_PERSONAL,null);
        if (cursorPersonales.moveToFirst()){
            do{
                personales = new Personales();
                personales.setId(cursorPersonales.getInt(0));
                personales.setNombre(cursorPersonales.getString(1));
                personales.setEstadoRegistro(cursorPersonales.getString(7));
                listaPersonales.add(personales);
            }while (cursorPersonales.moveToNext());
        }

        cursorPersonales.close();



        return listaPersonales;

    }
//ME QUEDE AQUI
    public Personales verPersonales(int id){

        DbHelper dbHelper=new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Personales personales = null;
        Cursor cursorPersonales;
        cursorPersonales = db.rawQuery("SELECT * FROM " + TABLE_MAESTRO_PERSONAL + " WHERE id = " + id + " LIMIT 1 ",null);
        if (cursorPersonales.moveToFirst()){
            personales = new Personales();
            personales.setId(cursorPersonales.getInt(0));
            personales.setNombre(cursorPersonales.getString(1));
            personales.setDni(cursorPersonales.getString(2));
            personales.setFechaNacimiento(cursorPersonales.getString(3));
            personales.setPaisId(cursorPersonales.getInt(4));
            personales.setCargoId(cursorPersonales.getInt(5));
            personales.setDepartamentoId(cursorPersonales.getInt(6));
            personales.setEstadoRegistro(cursorPersonales.getString(7));
        }
        cursorPersonales.close();
        return personales;
    }
    public boolean actualizarPersonales(int id, String nombre, String estado_registro) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_CARGO+ " SET nombre = '" + nombre + "', estado_registro = '" + estado_registro + "' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
    public boolean eliminarPersonal(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM "+ TABLE_MAESTRO_PERSONAL + " WHERE id ='" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
    public ArrayList<String> leerDepartamentosNombres() {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<String> listaDepartamentos = new ArrayList<>();
        Cursor cursorDepartamentos = db.rawQuery("SELECT nombre FROM " + TABLE_DEPARTAMENTO, null);
        if (cursorDepartamentos.moveToFirst()) {
            do {
                listaDepartamentos.add(cursorDepartamentos.getString(0));
            } while (cursorDepartamentos.moveToNext());
        }
        cursorDepartamentos.close();
        return listaDepartamentos;
    }
    public ArrayList<String> leerCargosNombres() {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<String> listaCargos = new ArrayList<>();
        Cursor cursorCargos = db.rawQuery("SELECT nombre FROM " + TABLE_CARGO, null);
        if (cursorCargos.moveToFirst()) {
            do {
                listaCargos.add(cursorCargos.getString(0));
            } while (cursorCargos.moveToNext());
        }
        cursorCargos.close();
        return listaCargos;
    }
    public ArrayList<String> leerPaisesNombres() {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<String> listaPaises = new ArrayList<>();
        Cursor cursorPaises = db.rawQuery("SELECT nombre FROM " + TABLE_PAIS, null);
        if (cursorPaises.moveToFirst()) {
            do {
                listaPaises.add(cursorPaises.getString(0));
            } while (cursorPaises.moveToNext());
        }
        cursorPaises.close();
        return listaPaises;
    }
    public int obtenerIdPais(String nombrePais) {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        int idPais = -1; // Valor predeterminado si no se encuentra

        Cursor cursor = db.rawQuery("SELECT id FROM " + TABLE_PAIS + " WHERE nombre = ?", new String[]{nombrePais});

        if (cursor.moveToFirst()) {
            idPais = cursor.getInt(0);
        }

        cursor.close();
        db.close();

        return idPais;
    }
    public int obtenerIdCargo(String nombreCargo) {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        int idCargo = -1; // Valor predeterminado si no se encuentra

        Cursor cursor = db.rawQuery("SELECT id FROM " + TABLE_CARGO + " WHERE nombre = ?", new String[]{nombreCargo});

        if (cursor.moveToFirst()) {
            idCargo = cursor.getInt(0);
        }

        cursor.close();
        db.close();

        return idCargo;
    }
    public int obtenerIdDepartamento(String nombreDepartamento) {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        int idDepartamento = -1; // Valor predeterminado si no se encuentra

        Cursor cursor = db.rawQuery("SELECT id FROM " + TABLE_DEPARTAMENTO + " WHERE nombre = ?", new String[]{nombreDepartamento});

        if (cursor.moveToFirst()) {
            idDepartamento = cursor.getInt(0);
        }

        cursor.close();
        db.close();

        return idDepartamento;
    }
    @SuppressLint("Range")
    public String obtenerNombrePaisPorId(int paisId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String nombrePais = null;

        Cursor cursor = db.query(TABLE_PAIS, new String[]{"nombre"}, "id = ?",
                new String[]{String.valueOf(paisId)}, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                nombrePais = cursor.getString(cursor.getColumnIndex("nombre"));
            }
            cursor.close();
        }

        return nombrePais;
    }
    @SuppressLint("Range")
    public String obtenerNombreCargoPorId(int cargoId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String nombreCargo = null;

        Cursor cursor = db.query(TABLE_CARGO, new String[]{"nombre"}, "id = ?",
                new String[]{String.valueOf(cargoId)}, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                nombreCargo = cursor.getString(cursor.getColumnIndex("nombre"));
            }
            cursor.close();
        }

        return nombreCargo;
    }

    @SuppressLint("Range")
    public String obtenerNombreDepartamentoPorId(int departamentoId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String nombreDepartamento = null;

        Cursor cursor = db.query(TABLE_DEPARTAMENTO, new String[]{"nombre"}, "id = ?",
                new String[]{String.valueOf(departamentoId)}, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                nombreDepartamento = cursor.getString(cursor.getColumnIndex("nombre"));
            }
            cursor.close();
        }

        return nombreDepartamento;
    }











}
