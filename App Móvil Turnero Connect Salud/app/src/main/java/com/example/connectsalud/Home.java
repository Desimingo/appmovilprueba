package com.example.connectsalud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void launchProfile(View view) {
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }

    public void launchReserve(View view) {
        Intent intent = new Intent(this, Reserve.class);
        startActivity(intent);
    }
    public void launchProfesionales(View view) {
        Intent intent = new Intent(this, Profesionales.class);
        startActivity(intent);
    }

    public void launchTurnos(View view) {
        Intent intent = new Intent(this, Turnos.class);
        startActivity(intent);
    }

    public void launchCerrar_Sesion(View view) {
        Intent intent = new Intent(this, CerrarSesion.class);
        startActivity(intent);
    }
}