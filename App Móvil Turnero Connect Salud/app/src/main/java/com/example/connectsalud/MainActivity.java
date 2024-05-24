package com.example.connectsalud;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = findViewById(R.id.input_email);
        passwordEditText = findViewById(R.id.input_pass1);
    }

    public void launchHome(View view) {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (isValidEmail(email) && isValidPassword(password)) {
            // Obtener el ID del paciente desde la base de datos o cualquier otra fuente
            long pacienteId = obtenerPacienteIdDesdeBaseDeDatos(email);

            // Credenciales válidas, iniciar la actividad Home con el ID del paciente como extra
            Intent intent = new Intent(this, Home.class);
            intent.putExtra("PACIENTE_ID", pacienteId);
            startActivity(intent);
            finish(); // Para evitar que el usuario vuelva atrás presionando el botón de retroceso
        } else {
            // Credenciales inválidas, mostrar un mensaje de error
            Toast.makeText(this, "Credenciales inválidas. Inténtalo de nuevo.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    private boolean isValidPassword(String password) {
        // Implementa tu lógica de validación de contraseña aquí
        // Por ejemplo, puedes verificar si la contraseña tiene una longitud mínima
        return password.length() >= 8;
    }

    private long obtenerPacienteIdDesdeBaseDeDatos(String email) {
        // Implementa la lógica para obtener el ID del paciente a partir del correo electrónico
        // Consulta la base de datos u otras fuentes según tus necesidades
        // Este método debe devolver el ID del paciente correspondiente al correo electrónico proporcionado
        return 1; // Ejemplo de ID del paciente (debes implementar la lógica real para obtenerlo)
    }

    public void launchRegister(View view) {
        Intent intent = new Intent(this, RegistroPaciente.class);
        startActivity(intent);
    }
}

