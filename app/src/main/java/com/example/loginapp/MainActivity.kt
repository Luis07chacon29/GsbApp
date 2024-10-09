package com.example.loginapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var usernameInput: EditText
    lateinit var passwordInput: EditText
    lateinit var loginBtn: Button
    lateinit var noAccountTextView: TextView
    lateinit var forgotPasswordTextView: TextView // Añade esta línea

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        usernameInput = findViewById(R.id.username_input)
        passwordInput = findViewById(R.id.password_input)
        loginBtn = findViewById(R.id.login_btn)

        // Inicializa el TextView para "No tengo cuenta GSB"
        noAccountTextView = findViewById(R.id.no_gsb_account)

        // Inicializa el TextView para "Olvidé mi contraseña"
        forgotPasswordTextView = findViewById(R.id.forgot_password)

        loginBtn.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()
            Log.i("Test Credenciales", "Username: $username and Password: $password")
        }

        // Agrega el listener para el TextView "No tengo cuenta GSB"
        noAccountTextView.setOnClickListener {
            val intent = Intent(this, Registro::class.java) // Asegúrate de que Registro sea el nombre correcto de tu actividad de registro
            startActivity(intent)
        }

        // Agrega el listener para el TextView "Olvidé mi contraseña"
        forgotPasswordTextView.setOnClickListener {
            val intent = Intent(this, RecuperarPass::class.java) // Cambia a la actividad de recuperación de contraseña
            startActivity(intent)
        }
    }
}
