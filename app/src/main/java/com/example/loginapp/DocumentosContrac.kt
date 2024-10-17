package com.example.loginapp // Reemplaza con el nombre de tu paquete

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class DocumentosContrac : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_documentoscontrac) // Asegúrate de que este sea el nombre correcto de tu layout

        // Configuración del botón de regresar
        val backButton: ImageButton = findViewById(R.id.regresar)
        backButton.setOnClickListener {
            // Acción para regresar a la pantalla anterior
            finish() // Cierra la actividad actual
        }

        // Aquí puedes añadir el código adicional para cargar los documentos contractuales
        // y cualquier otra funcionalidad que necesites en esta actividad
    }
}
