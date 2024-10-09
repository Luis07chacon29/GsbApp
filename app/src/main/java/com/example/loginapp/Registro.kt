package com.example.loginapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Registro : AppCompatActivity() {
    lateinit var name: EditText
    lateinit var lastnameMother: EditText
    lateinit var lastnameFather: EditText
    lateinit var email: EditText
    lateinit var number: EditText
    lateinit var jefe: EditText
    lateinit var registroBtn: Button
    lateinit var backButton: ImageButton  // Añadir una variable para el botón de regreso

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)

        // Inicializa los EditText y el Button utilizando los IDs definidos en el XML
        name = findViewById(R.id.name)
        lastnameMother = findViewById(R.id.lastnameMother)
        lastnameFather = findViewById(R.id.lastnameFather)
        email = findViewById(R.id.email)
        number = findViewById(R.id.number)
        jefe = findViewById(R.id.jefe)
        registroBtn = findViewById(R.id.registroBtn)
        backButton = findViewById(R.id.backButton)  // Inicializa el botón de regreso

        // Configura el OnClickListener para el botón de registro
        registroBtn.setOnClickListener {
            // Obtén los valores de los campos
            val nameValue = name.text.toString()
            val lastnameMotherValue = lastnameMother.text.toString()
            val lastnameFatherValue = lastnameFather.text.toString()
            val emailValue = email.text.toString()
            val numberValue = number.text.toString()
            val jefeValue = jefe.text.toString()

            // Imprime los valores en el log
            Log.i("Registro Info", "Nombre: $nameValue, Apellido Materno: $lastnameMotherValue, Apellido Paterno: $lastnameFatherValue, Correo: $emailValue, Número: $numberValue, Jefe: $jefeValue")
        }

        // Configura el OnClickListener para el botón de regreso
        backButton.setOnClickListener {
            finish()  // Cierra la actividad actual y regresa a la anterior
        }
    }
}
