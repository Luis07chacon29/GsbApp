package com.example.loginapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    lateinit var usernameInput: EditText
    lateinit var passwordInput: EditText
    lateinit var loginBtn: Button
    lateinit var noAccountTextView: TextView
    lateinit var forgotPasswordTextView: TextView

    // Instancia de conexión a la base de datos (aquí debes tener tu lógica de conexión)
    private val connectionBD = ConnectionBD()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialización de las vistas
        usernameInput = findViewById(R.id.username_input)
        passwordInput = findViewById(R.id.password_input)
        loginBtn = findViewById(R.id.login_btn)
        noAccountTextView = findViewById(R.id.no_gsb_account)
        forgotPasswordTextView = findViewById(R.id.forgot_password)

        // Lógica de login al hacer clic en el botón
        loginBtn.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, complete ambos campos", Toast.LENGTH_SHORT).show()
            } else {
                // Ejecutar la autenticación de usuario en un hilo separado
                authenticateUser(username, password)
            }
        }

        // Evento para "No tengo cuenta GSB"
        noAccountTextView.setOnClickListener {
            val intent = Intent(this, Registro::class.java)
            startActivity(intent)
        }

        // Evento para "Olvidé mi contraseña"
        forgotPasswordTextView.setOnClickListener {
            val intent = Intent(this, RecuperarPass::class.java)
            startActivity(intent)
        }
    }

    // Método para autenticar usuario
    private fun authenticateUser(username: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Intentamos la conexión a la base de datos y verificar el login
                val isAuthenticated = connectionBD.authenticate(username, password)

                // Volvemos al hilo principal para mostrar el resultado en la UI
                withContext(Dispatchers.Main) {
                    if (isAuthenticated) {
                        Toast.makeText(this@MainActivity, "Login exitoso", Toast.LENGTH_SHORT).show()

                        // Redirigir a la actividad MenuPrincipal y pasar el nombre del usuario
                        val intent = Intent(this@MainActivity, Menuprincipal::class.java)
                        intent.putExtra("USERNAME", username)  // Enviar el nombre de usuario
                        startActivity(intent)
                        finish()  // Cerrar la actividad de inicio de sesión para no volver atrás
                    } else {
                        Toast.makeText(this@MainActivity, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Error en la conexión: ${e.message}", Toast.LENGTH_SHORT).show()
                }
                Log.e("MainActivity", "Error autenticando el usuario", e)
            }
        }
    }
}
