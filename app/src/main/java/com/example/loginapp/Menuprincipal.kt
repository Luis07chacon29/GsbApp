package com.example.loginapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class Menuprincipal : AppCompatActivity() {

    lateinit var welcomeText: TextView

    companion object {
        private const val REQUEST_CODE_DATOS_PERSONALES = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menuprincipal)

        // Recuperar el correo GSB desde el Intent
        val correoGsb = intent.getStringExtra("correo_gsb")
        Log.d("Menuprincipal", "Correo GSB recibido: $correoGsb")

        // Inicializar el TextView del mensaje de bienvenida
        welcomeText = findViewById(R.id.welcomeText)

        // Actualizar el texto de bienvenida con el correo del usuario
        welcomeText.text = "Bienvenido, $correoGsb"

        // Inicializar el CardView para Datos Personales
        val cardDatosPersonales: CardView = findViewById(R.id.cardDatosPersonales)

        // Establecer el OnClickListener para el CardView
        cardDatosPersonales.setOnClickListener {
            // Verificar si el correo GSB no es nulo
            if (correoGsb != null) {
                // Iniciar la actividad de Datos Personales
                val intent = Intent(this, DatosPersonales::class.java)
                intent.putExtra("correo_gsb", correoGsb) // Enviar el correo GSB
                startActivityForResult(intent, REQUEST_CODE_DATOS_PERSONALES)
            } else {
                Toast.makeText(this, "Error: El correo electrónico es nulo", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        // Inicializar el botón de Cerrar Sesión
        val buttonCerrarSesion: Button = findViewById(R.id.buttonCerrarSesion)

        // Establecer el OnClickListener para el botón de Cerrar Sesión
        buttonCerrarSesion.setOnClickListener {
            // Aquí puedes manejar la lógica de cierre de sesión
            // Por ejemplo, puedes regresar a la pantalla de login
            val intent = Intent(this, MainActivity::class.java)
            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // Limpiar la pila de actividades
            startActivity(intent)
            finish() // Cerrar la actividad actual
        }

        // Configuración del botón para abrir DocumentosContract
        val documentosCard: CardView =
            findViewById(R.id.cardDocumentosContrac) // Asegúrate de tener un botón con este ID en tu layout
        documentosCard.setOnClickListener {
            // Crear un Intent para abrir la actividad DocumentosContract
            val intent = Intent(this, DocumentosContrac::class.java)
            startActivity(intent) // Iniciar la nueva actividad
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_DATOS_PERSONALES && resultCode == RESULT_OK) {
            // Recuperar el correo GSB de los datos devueltos
            val correoGsb = data?.getStringExtra("correo_gsb")
            welcomeText.text = "Bienvenido, $correoGsb" // Actualiza el texto de bienvenida
        }


        // Configuración del botón para abrir DocumentosPer
        val DocumentosPersonales: CardView =
            findViewById(R.id.cardDocumentosPer) // Asegúrate de tener un botón con este ID en tu layout
        DocumentosPersonales.setOnClickListener {
            // Crear un Intent para abrir la actividad DocumentosContract
            val intent = Intent(this, DocumentosPer::class.java)
            startActivity(intent) // Iniciar la nueva actividad
        }
    }
}
