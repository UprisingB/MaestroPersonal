package com.example.maestropersonal.db;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;;
import androidx.annotation.Nullable;
import com.example.maestropersonal.entidades.Departamentos;
import com.example.maestropersonal.entidades.Paises;

import java.util.ArrayList;

public class DbPaises extends DbHelper{
    Context context;
    public DbPaises(@Nullable Context context) {
        super(context);
        this.context=context;

    }

    public long crearPaises(String nombre, String estado_registro){
        long id=0;
        try {

            DbHelper dbHelper=new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues  values= new ContentValues();
            values.put("nombre",nombre);
            values.put("estado_registro",estado_registro);
            id=db.insert(TABLE_PAIS,null,values);

        }catch (Exception ex){
            ex.toString();

        }
        return id;

    }
    public ArrayList<Paises> leerPaises(){


        DbHelper dbHelper=new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Paises> listaPaises = new ArrayList<>();
        Paises paises = null;
        Cursor cursorPaises=null;
        cursorPaises = db.rawQuery("SELECT * FROM " + TABLE_PAIS,null);
        if (cursorPaises.moveToFirst()){
            do{
                paises = new Paises();
                paises.setId(cursorPaises.getInt(0));
                paises.setNombre(cursorPaises.getString(1));
                paises.setEstadoRegistro(cursorPaises.getString(2));
                listaPaises.add(paises);
            }while (cursorPaises.moveToNext());
        }

        cursorPaises.close();



        return listaPaises;

    }

    public Paises verPaises(int id){

        DbHelper dbHelper=new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Paises paises = null;
        Cursor cursorPaises;
        cursorPaises = db.rawQuery("SELECT * FROM " + TABLE_PAIS+ " WHERE id = " + id + " LIMIT 1 ",null);
        if (cursorPaises.moveToFirst()){
            paises = new Paises();
            paises.setId(cursorPaises.getInt(0));
            paises.setNombre(cursorPaises.getString(1));
            paises.setEstadoRegistro(cursorPaises.getString(2));
        }
        cursorPaises.close();
        return paises;
    }
    public boolean actualizarPaises(int id, String nombre, String estado_registro) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_PAIS+ " SET nombre = '" + nombre + "', estado_registro = '" + estado_registro + "' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
    public boolean eliminarPaises(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM "+ TABLE_PAIS + " WHERE id ='" + id + "'");
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
