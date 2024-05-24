package com.example.connectsalud;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.Toast;
import android.util.Log;


public class Reserve extends AppCompatActivity {

    private ReserveDatabaseHelper databaseHelper;
    private Spinner spinnerEspecialidades, spinnerProfesionales;
    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        // Inicializar el ayudante de la base de datos
        databaseHelper = new ReserveDatabaseHelper(this);

        // Obtener referencias a los elementos de la interfaz de usuario
        spinnerEspecialidades = findViewById(R.id.spinner_especialidad);
        spinnerProfesionales = findViewById(R.id.spinner_profesionales);
        calendarView = findViewById(R.id.calendar);

        // Configurar adaptadores para los Spinners
        ArrayAdapter<CharSequence> especialidadesAdapter = ArrayAdapter.createFromResource(
                this, R.array.item_esp, android.R.layout.simple_spinner_item);
        especialidadesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEspecialidades.setAdapter(especialidadesAdapter);

        ArrayAdapter<CharSequence> profesionalesAdapter = ArrayAdapter.createFromResource(
                this, R.array.item_prof, android.R.layout.simple_spinner_item);
        profesionalesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProfesionales.setAdapter(profesionalesAdapter);

        // Configurar listener para el CalendarView
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // Obtener la fecha seleccionada
                String fechaSeleccionada = year + "-" + (month + 1) + "-" + dayOfMonth;
                // Realizar operaciones con la fecha seleccionada según sea necesario
            }
        });
    }

    // Cuando el usuario confirma la reserva (por ejemplo, al hacer clic en un botón de reserva)
    public void confirmarReserva(View view) {
        // Obtener los datos seleccionados por el usuario
        String especialidad = spinnerEspecialidades.getSelectedItem().toString();
        String profesional = spinnerProfesionales.getSelectedItem().toString();
        String fecha = obtenerFechaSeleccionada();
        String hora = obtenerhoraSeleccionada();

        Log.d("Reserva", "Especialidad: " + especialidad);
        Log.d("Reserva", "Profesional: " + profesional);
        Log.d("Reserva", "Fecha: " + fecha);
        Log.d("Reserva", "Hora: " + hora);

        // Insertar la reserva en la base de datos
        long reservaId = databaseHelper.insertReserva(especialidad, profesional, fecha);

        // Mostrar un mensaje al usuario
        if (reservaId != -1) {
            // La inserción fue exitosa, mostrar mensaje "turno reservado"
            showToast("Turno reservado");
        } else {
            // La inserción falló, mostrar mensaje de error si es necesario
            showToast("Error al reservar el turno");
        }
    }

    private String obtenerFechaSeleccionada() {
        // Implementa lógica para obtener la fecha seleccionada del CalendarView
        // Por ejemplo, podrías obtenerla como una cadena en el formato deseado
        // (año-mes-día) y devolverla desde este método
        return "15-11-2023 10:30"; // Ejemplo de fecha seleccionada (debes implementar la lógica real)
    }
    private String obtenerhoraSeleccionada() {
        return "09:00";
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Cerrar el ayudante de la base de datos al destruir la actividad
        if (databaseHelper != null) {
            databaseHelper.close();
        }
    }
}
