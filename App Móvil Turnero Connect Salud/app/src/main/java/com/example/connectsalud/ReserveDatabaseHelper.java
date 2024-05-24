package com.example.connectsalud;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ReserveDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "reservas.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_RESERVAS = "reservas";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_ESPECIALIDAD = "especialidad";
    private static final String COLUMN_PROFESIONAL = "profesional";
    private static final String COLUMN_FECHA = "fecha";


    public ReserveDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            // Crear la tabla de reservas
            String createTable = "CREATE TABLE " + TABLE_RESERVAS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_ESPECIALIDAD + " TEXT, " +
                    COLUMN_PROFESIONAL + " TEXT, " +
                    COLUMN_FECHA + " DATE ) ";
                    db.execSQL(createTable);

            Log.d("Database", "Database created successfully");
        } catch (SQLException e) {
            Log.e("Database", "Error creating the database: " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Manejar la actualización de la base de datos si es necesario
    }

    public long insertReserva(String especialidad, String profesional, String fecha) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ESPECIALIDAD, especialidad);
        values.put(COLUMN_PROFESIONAL, profesional);
        values.put(COLUMN_FECHA, fecha);


        // Insertar la reserva
        long reservaId = db.insert(TABLE_RESERVAS, null, values);
        db.close();
        return reservaId;
    }
}

