package com.example.maestropersonal.db;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;;
import androidx.annotation.Nullable;
import com.example.maestropersonal.entidades.Departamentos;

import java.util.ArrayList;

public class DbDepartamentos extends DbHelper{
    Context context;
    public DbDepartamentos(@Nullable Context context) {
        super(context);
        this.context=context;

    }

    public long crearDepartamento(String nombre, String estado_registro){
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
    public ArrayList<Departamentos> leerDepartamentos(){


            DbHelper dbHelper=new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ArrayList<Departamentos> listaDepartamentos = new ArrayList<>();
            Departamentos departamentos = null;
            Cursor cursorDepartamentos=null;
            cursorDepartamentos = db.rawQuery("SELECT * FROM " + TABLE_DEPARTAMENTO,null);
            if (cursorDepartamentos.moveToFirst()){
                do{
                    departamentos = new Departamentos();
                    departamentos.setId(cursorDepartamentos.getInt(0));
                    departamentos.setNombre(cursorDepartamentos.getString(1));
                    departamentos.setEstadoRegistro(cursorDepartamentos.getString(2));
                    listaDepartamentos.add(departamentos);
                }while (cursorDepartamentos.moveToNext());
            }

            cursorDepartamentos.close();



            return listaDepartamentos;

    }

    public Departamentos verDepartamentos(int id){

        DbHelper dbHelper=new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Departamentos departamentos = null;
        Cursor cursorDepartamentos;
        cursorDepartamentos = db.rawQuery("SELECT * FROM " + TABLE_DEPARTAMENTO + " WHERE id = " + id + " LIMIT 1 ",null);
        if (cursorDepartamentos.moveToFirst()){
            departamentos = new Departamentos();
            departamentos.setId(cursorDepartamentos.getInt(0));
            departamentos.setNombre(cursorDepartamentos.getString(1));
            departamentos.setEstadoRegistro(cursorDepartamentos.getString(2));
        }
        cursorDepartamentos.close();
        return departamentos;
    }
    public boolean actualizarDepartamentos(int id, String nombre, String estado_registro) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_DEPARTAMENTO+ " SET nombre = '" + nombre + "', estado_registro = '" + estado_registro + "' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
    public boolean eliminarDepartamentos(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM "+ TABLE_DEPARTAMENTO + " WHERE id ='" + id + "'");
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
