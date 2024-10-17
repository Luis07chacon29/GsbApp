package com.example.loginapp

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class DocumentosPer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_documentosper)

        // Referencia al botón de regreso
        val backButton = findViewById<ImageButton>(R.id.regresar)

        // Agregar la función para volver a la pantalla anterior
        backButton.setOnClickListener {
            finish() // Cierra esta actividad y regresa a la anterior
        }
    }
}