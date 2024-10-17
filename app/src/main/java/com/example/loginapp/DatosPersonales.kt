package com.example.loginapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DatosPersonales : AppCompatActivity() {

    private lateinit var connectionBD: ConnectionBD
    private lateinit var textViewNombre: TextView
    private lateinit var textViewApellidoPaterno: TextView
    private lateinit var textViewApellidoMaterno: TextView
    private lateinit var textViewCorreoPersonal: TextView
    private lateinit var textViewCorreoGsb: TextView
    private lateinit var textViewTelefonoMovil: TextView
    private lateinit var textViewNacionalidad: TextView
    private lateinit var textViewFechaNacimiento: TextView
    private lateinit var textViewIdentificadorFiscal: TextView
    private lateinit var textViewNumeroIdentificacion: TextView
    private lateinit var textViewVigenciaId: TextView
    private lateinit var textViewDireccion: TextView
    private lateinit var textViewCodigoPostal: TextView
    private lateinit var textViewCiudad: TextView
    private lateinit var textViewEstadoProvincia: TextView
    private lateinit var textViewGenero: TextView
    private lateinit var textViewEstadoCivil: TextView
    private lateinit var textViewNumeroHijos: TextView
    private lateinit var textViewNombreContactoEmergencia: TextView
    private lateinit var textViewParentesco: TextView
    private lateinit var textViewTelefonoEmergencias: TextView
    private lateinit var textViewNivelEstudios: TextView
    private lateinit var textViewSituacionAcademica: TextView
    private lateinit var textViewNss: TextView
    private lateinit var textViewVigenciaSs: TextView
    private lateinit var textViewCurp: TextView
    private lateinit var textViewBanco: TextView
    private lateinit var textViewNumeroCuenta: TextView
    private lateinit var textViewClabeInterbancaria: TextView
    private lateinit var textViewCertificacionesVigentes: TextView
    private lateinit var textViewPuesto: TextView
    private lateinit var textViewAreaDepartamento: TextView
    private lateinit var textViewJefeDirecto: TextView
    private lateinit var textViewFechaContratacion: TextView
    private lateinit var textViewHorarioLaboral: TextView
    private lateinit var textViewTipoEmpleado: TextView

    lateinit var correoGsb: String

    // Declara más TextViews según sea necesario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datospersonales) // Asegúrate de que el nombre de tu layout sea correcto
        correoGsb = intent.getStringExtra("correo_gsb") ?: "juan.perez@gsb.lat" // Usa un valor por defecto si es nulo

// Recuperar el correo GSB desde el Intent
        val correoGsb = intent.getStringExtra("correo_gsb")

        // Inicializar el botón de regresar
        val backButton: ImageButton = findViewById(R.id.backButton)

        // Establecer el OnClickListener para el botón de regresar
        backButton.setOnClickListener {
            // Devolver el resultado con el correo GSB
            val resultIntent = Intent()
            resultIntent.putExtra("correo_gsb", correoGsb)
            setResult(RESULT_OK, resultIntent) // Establece el resultado
            finish() // Cierra la actividad
        }



// Inicializa los TextViews
        textViewNombre = findViewById(R.id.txtNombre)
        textViewApellidoPaterno = findViewById(R.id.txtApellidoPaterno)
        textViewApellidoMaterno = findViewById(R.id.txtApellidoMaterno)
        textViewCorreoPersonal = findViewById(R.id.txtCorreoPersonal)
        textViewCorreoGsb = findViewById(R.id.txtCorreoGsb)
        textViewTelefonoMovil = findViewById(R.id.txtTelefono)
        textViewNacionalidad = findViewById(R.id.txtNacionalidad)
        textViewFechaNacimiento = findViewById(R.id.txtFechaNacimiento)
        textViewIdentificadorFiscal = findViewById(R.id.txtIdentificadorFiscal)
        textViewNumeroIdentificacion = findViewById(R.id.txtNumeroIdentificacion)
        textViewVigenciaId = findViewById(R.id.txtVigenciaID)
        textViewDireccion = findViewById(R.id.txtDireccion)
        textViewCodigoPostal = findViewById(R.id.txtCodigoPostal)
        textViewCiudad = findViewById(R.id.txtCiudad)
        textViewEstadoProvincia = findViewById(R.id.txtEstado)
        textViewGenero = findViewById(R.id.txtGenero)
        textViewEstadoCivil = findViewById(R.id.txtEstadoCivil)
        textViewNumeroHijos = findViewById(R.id.txtNumeroHijos)
        textViewNombreContactoEmergencia = findViewById(R.id.txtContactoEmergencia)
        textViewParentesco = findViewById(R.id.txtParentesco)
        textViewTelefonoEmergencias = findViewById(R.id.txtTelefonoEmergencias)
        textViewNivelEstudios = findViewById(R.id.txtNivelEstudios)
        textViewSituacionAcademica = findViewById(R.id.txtSituacionAcademica)
        textViewNss = findViewById(R.id.txtNSS)
        textViewVigenciaSs = findViewById(R.id.txtVigenciaSS)
        textViewCurp = findViewById(R.id.txtCURP)
        textViewBanco = findViewById(R.id.txtBanco)
        textViewNumeroCuenta = findViewById(R.id.txtNumeroCuenta)
        textViewClabeInterbancaria = findViewById(R.id.txtClabeInterbancaria)
        textViewCertificacionesVigentes = findViewById(R.id.txtCertificaciones)
        textViewPuesto = findViewById(R.id.txtPuesto)
        textViewAreaDepartamento = findViewById(R.id.txtAreaDepartamento)
        textViewJefeDirecto = findViewById(R.id.txtJefeDirecto)
        textViewFechaContratacion = findViewById(R.id.txtFechaContratacion)
        textViewHorarioLaboral = findViewById(R.id.txtHorarioLaboral)
        textViewTipoEmpleado = findViewById(R.id.txtTipoEmpleado)


        connectionBD = ConnectionBD.getInstance()

        // Obtén el correo del usuario desde el intent (deberías pasarlo al iniciar la actividad)
        val correo_gsb = intent.getStringExtra("correo_gsb")

        correo_gsb?.let {
            // Llama a la función para obtener los datos del usuario
            fetchUserData(it)
        } ?: run {
            Toast.makeText(this, "Error: No se encontró el correo electrónico", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchUserData(correo_gsb: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val userData = connectionBD.getUserData(correo_gsb)

            withContext(Dispatchers.Main) {
                if (userData != null) {
                    // Actualiza los TextViews con los datos del usuario
                    textViewNombre.text = userData.nombre
                    textViewApellidoPaterno.text = userData.apellidoPaterno
                    textViewApellidoMaterno.text = userData.apellidoMaterno
                    textViewCorreoPersonal.text = userData.correoPersonal
                    textViewCorreoGsb.text = userData.correoGsb
                    textViewTelefonoMovil.text = userData.telefonoMovil
                    textViewNacionalidad.text = userData.nacionalidad
                    textViewFechaNacimiento.text = userData.fechaNacimiento
                    textViewIdentificadorFiscal.text = userData.identificadorFiscal
                    textViewNumeroIdentificacion.text = userData.numeroIdentificacion
                    textViewVigenciaId.text = userData.vigenciaId
                    textViewDireccion.text = userData.direccion
                    textViewCodigoPostal.text = userData.codigoPostal
                    textViewCiudad.text = userData.ciudad
                    textViewEstadoProvincia.text = userData.estadoProvincia
                    textViewGenero.text = userData.genero
                    textViewEstadoCivil.text = userData.estadoCivil
                    textViewNumeroHijos.text = userData.numeroHijos
                    textViewNombreContactoEmergencia.text = userData.nombreContactoEmergencia
                    textViewParentesco.text = userData.parentesco
                    textViewTelefonoEmergencias.text = userData.telefonoEmergencias
                    textViewNivelEstudios.text = userData.nivelEstudios
                    textViewSituacionAcademica.text = userData.situacionAcademica
                    textViewNss.text = userData.nss
                    textViewVigenciaSs.text = userData.vigenciaSs
                    textViewCurp.text = userData.curp
                    textViewBanco.text = userData.banco
                    textViewNumeroCuenta.text = userData.numeroCuenta
                    textViewClabeInterbancaria.text = userData.clabeInterbancaria
                    textViewCertificacionesVigentes.text = userData.certificacionesVigentes
                    textViewPuesto.text = userData.puesto
                    textViewAreaDepartamento.text = userData.areaDepartamento
                    textViewJefeDirecto.text = userData.jefeDirecto
                    textViewFechaContratacion.text = userData.fechaContratacion
                    textViewHorarioLaboral.text = userData.horarioLaboral
                    textViewTipoEmpleado.text = userData.tipoEmpleado
                    // Actualiza más TextViews según sea necesario
                } else {
                    Toast.makeText(this@DatosPersonales, "Error: No se encontraron datos del usuario", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
