package com.example.maestropersonal.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "maestroPersonal.db";
    public static final String TABLE_MAESTRO_PERSONAL = "t_maestro_personal";
    public static final String TABLE_PAIS = "t_pais";
    public static final String TABLE_CARGO = "t_cargo";
    public static final String TABLE_DEPARTAMENTO = "t_departamento";
    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_MAESTRO_PERSONAL + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "dni TEXT NOT NULL," +
                "fecha_nacimiento TEXT NOT NULL," +
                "pais_id INTEGER NOT NULL," +
                "cargo_id INTEGER NOT NULL," +
                "departamento_id INTEGER NOT NULL," +
                "estado_registro TEXT NOT NULL," +
                "FOREIGN KEY (pais_id) REFERENCES " + TABLE_PAIS + "(id)," +
                "FOREIGN KEY (cargo_id) REFERENCES " + TABLE_CARGO + "(id)," +
                "FOREIGN KEY (departamento_id) REFERENCES " + TABLE_DEPARTAMENTO + "(id))");


        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PAIS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "estado_registro TEXT NOT NULL)");


        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CARGO + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "estado_registro TEXT NOT NULL)");


        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_DEPARTAMENTO + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "estado_registro TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase ,int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MAESTRO_PERSONAL);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PAIS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CARGO);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DEPARTAMENTO);
        onCreate(sqLiteDatabase);

    }
}
