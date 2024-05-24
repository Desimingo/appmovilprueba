package com.example.connectsalud;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "db1";
    private static final int DATABASE_VERSION = 1;

    public AdminSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuarios (\n" +
                "\tdni integer primary key,\n" +
                "\tnombre text,\n" +
                "\tapellido text,\n" +
                "\ttelefono integer,\n" +
                "\tfecha_nacimiento text,\n" +
                "\temail text,\n" +
                "\tpass text,\n" +
                "\tpassagain text\n" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Aquí puedes implementar la lógica de actualización si es necesario.
    }

    public Cursor leerPacientes() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {"dni", "nombre", "apellido", "telefono", "fecha_nacimiento", "email"};
        return db.query("usuarios", projection, null, null, null, null, null);
    }

    public long insertarUsuario(String dni, String nombre, String apellido, String telefono, String fecha_nacimiento, String email, String pass, String passagain) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("dni", dni);
        values.put("nombre", nombre);
        values.put("apellido", apellido);
        values.put("telefono", telefono);
        values.put("fecha_nacimiento", fecha_nacimiento);
        values.put("email", email);
        values.put("pass", pass);
        values.put("passagain", passagain);
        long newRowId = db.insert("usuarios", null, values);
        db.close();
        return newRowId;
    }


    public long obtenerPacienteIdDesdeBaseDeDatos(String dni, String nombre, String apellido, String telefono, String fecha_nacimiento, String email, String pass, String passagain) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {"dni", "nombre", "apellido", "telefono", "fecha-nacimiento", "email", "pass", "passagain"};
        String selection = "email = ?";
        String[] selectionArgs = {email};

        Cursor cursor = db.query(
                "usuarios",  // Nombre de la tabla
                projection,   // Columnas que quieres recuperar
                selection,    // Clausula WHERE
                selectionArgs, // Valores de la clausula WHERE
                null,         // No agrupar las filas
                null,         // No filtrar por grupos de filas
                null          // No ordenar las filas
        );

        long pacienteId = -1; // Valor predeterminado si no se encuentra el paciente

        if (cursor.moveToFirst()) {
            pacienteId = cursor.getLong(cursor.getColumnIndexOrThrow("dni"));
        }

        cursor.close();
        db.close();

        return pacienteId;
    }
}
