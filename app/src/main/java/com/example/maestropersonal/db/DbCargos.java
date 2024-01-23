package com.example.maestropersonal.db;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;;
import androidx.annotation.Nullable;

import com.example.maestropersonal.entidades.Cargos;
import com.example.maestropersonal.entidades.Departamentos;

import java.util.ArrayList;

public class DbCargos extends DbHelper{
    Context context;
    public DbCargos(@Nullable Context context) {
        super(context);
        this.context=context;

    }

    public long crearCargo(String nombre, String estado_registro){
        long id=0;
        try {

            DbHelper dbHelper=new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues  values= new ContentValues();
            values.put("nombre",nombre);
            values.put("estado_registro",estado_registro);
            id=db.insert(TABLE_DEPARTAMENTO,null,values);

        }catch (Exception ex){
            ex.toString();

        }
        return id;

    }
    public ArrayList<Cargos> leerCargos(){


        DbHelper dbHelper=new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Cargos> listaCargos = new ArrayList<>();
        Cargos cargos = null;
        Cursor cursorCargos=null;
        cursorCargos = db.rawQuery("SELECT * FROM " + TABLE_CARGO,null);
        if (cursorCargos.moveToFirst()){
            do{
                cargos = new Cargos();
                cargos.setId(cursorCargos.getInt(0));
                cargos.setNombre(cursorCargos.getString(1));
                cargos.setEstadoRegistro(cursorCargos.getString(2));
                listaCargos.add(cargos);
            }while (cursorCargos.moveToNext());
        }

        cursorCargos.close();



        return listaCargos;

    }

    public Cargos verCargos(int id){

        DbHelper dbHelper=new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cargos cargos = null;
        Cursor cursorCargos;
        cursorCargos = db.rawQuery("SELECT * FROM " + TABLE_CARGO + " WHERE id = " + id + " LIMIT 1 ",null);
        if (cursorCargos.moveToFirst()){
            cargos = new Cargos();
            cargos.setId(cursorCargos.getInt(0));
            cargos.setNombre(cursorCargos.getString(1));
            cargos.setEstadoRegistro(cursorCargos.getString(2));
        }
        cursorCargos.close();
        return cargos;
    }
    public boolean actualizarCargos(int id, String nombre, String estado_registro) {

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
    public boolean eliminarCargo(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM "+ TABLE_CARGO + " WHERE id ='" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }











}
