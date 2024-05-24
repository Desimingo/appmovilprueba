package com.example.connectsalud;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Profile extends AppCompatActivity {

    EditText dniTextView, nombreTextView, apellidoTextView, telefonoTextView, fechaNacimientoTextView, emailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Obtener datos de SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);
        String dni = sharedPreferences.getString("dni", "");
        String nombre = sharedPreferences.getString("nombre", "");
        String apellido = sharedPreferences.getString("apellido", "");
        String telefono = sharedPreferences.getString("telefono", "");
        String fechaNacimiento = sharedPreferences.getString("fecha_nacimiento", "");
        String email = sharedPreferences.getString("email", "");

        // Convertir la fecha al formato dd/mm/aaaa
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            Date date = sdf.parse(fechaNacimiento);
            sdf.applyPattern("dd/MM/yyyy");
            fechaNacimiento = sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Asignar datos a los EditTexts
        dniTextView = findViewById(R.id.dni);
        nombreTextView = findViewById(R.id.nombre);
        apellidoTextView = findViewById(R.id.apellido);
        telefonoTextView = findViewById(R.id.telefono);
        fechaNacimientoTextView = findViewById(R.id.fecha_nacimiento);
        emailTextView = findViewById(R.id.email);

        dniTextView.setText(dni);
        nombreTextView.setText(nombre);
        apellidoTextView.setText(apellido);
        telefonoTextView.setText(telefono);
        fechaNacimientoTextView.setText(fechaNacimiento);
        emailTextView.setText(email);
    }
}
