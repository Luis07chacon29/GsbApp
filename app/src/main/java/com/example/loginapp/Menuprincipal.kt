package com.example.loginapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Menuprincipal : AppCompatActivity() {

    lateinit var welcomeText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menuprincipal)

        // Recuperar el nombre del usuario desde el Intent
        val username = intent.getStringExtra("USERNAME")

        // Inicializar el TextView del mensaje de bienvenida
        welcomeText = findViewById(R.id.welcomeText)

        // Actualizar el texto de bienvenida con el nombre del usuario
        welcomeText.text = "Bienvenido, $username"
    }
}
