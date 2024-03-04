package com.example.senati;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SalidaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salida);
        TimerTask tarea = new TimerTask() { //dar instrucciones al timer
            @Override
            public void run() {
                Intent intent = new Intent(SalidaActivity.this, PrincipalActivity.class); // se crea un intent y se crea una nueva Vista en LAYOUT
                startActivity(intent); //iNICIAMOS LA NUEVA VISTA
                finish(); // PARA QUE SOLO APAREZCA AL INICIO
            }
        };
        Timer tiempo = new Timer();
        tiempo.schedule(tarea, 2000); // espere 2 seg y ejecute Tarea
    }

}