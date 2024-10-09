package com.example.loginapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class Registro : AppCompatActivity() {
    lateinit var name: EditText
    lateinit var lastnameMother: EditText
    lateinit var lastnameFather: EditText
    lateinit var email: EditText
    lateinit var number: EditText
    lateinit var jefe: EditText
    lateinit var registroBtn: Button
    lateinit var backButton: ImageButton
    private lateinit var connectionBD: ConnectionBD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)

        // Inicializa los EditText y el Button
        name = findViewById(R.id.name)
        lastnameMother = findViewById(R.id.lastnameMother)
        lastnameFather = findViewById(R.id.lastnameFather)
        email = findViewById(R.id.email)
        number = findViewById(R.id.number)
        jefe = findViewById(R.id.jefe)
        registroBtn = findViewById(R.id.registroBtn)
        backButton = findViewById(R.id.backButton)

        // Inicializa la conexión a la base de datos
        connectionBD = ConnectionBD()

        // Configura el OnClickListener para el botón de registro
        registroBtn.setOnClickListener {
            val nameValue = name.text.toString()
            val lastnameMotherValue = lastnameMother.text.toString()
            val lastnameFatherValue = lastnameFather.text.toString()
            val emailValue = email.text.toString()
            val numberValue = number.text.toString()
            val jefeValue = jefe.text.toString()

            Log.i("Registro Info", "Nombre: $nameValue, Apellido Materno: $lastnameMotherValue, Apellido Paterno: $lastnameFatherValue, Correo: $emailValue, Número: $numberValue, Jefe: $jefeValue")

            // Llama al método para registrar al usuario en un hilo de fondo
            CoroutineScope(Dispatchers.IO).launch {
                // Realiza el registro
                val registrationSuccessful = connectionBD.registerUser(nameValue, lastnameMotherValue, lastnameFatherValue, emailValue, numberValue, jefeValue)

                // Maneja el resultado en el hilo principal
                withContext(Dispatchers.Main) {
                    if (registrationSuccessful) {
                        Toast.makeText(this@Registro, "Registro exitoso", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@Registro, "Error al registrar, por favor intente de nuevo", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        // Configura el OnClickListener para el botón de regreso
        backButton.setOnClickListener {
            finish() // Cierra la actividad actual
        }
    }
}
