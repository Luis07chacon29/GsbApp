package com.example.loginapp

import android.util.Log
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException

class ConnectionBD {

    private val ip = "51.222.138.68"
    val usuario = "App_Employees"
    val password = "swE7uf91wU@#"
    private val bd = "GSBEmployees"

    // Singleton pattern
    companion object {
        @Volatile
        private var instance: ConnectionBD? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: ConnectionBD().also { instance = it }
        }
    }

    fun authenticate(username: String, password: String): Boolean {
        val sql = "SELECT * FROM datospersonales WHERE correo_gsb = ? AND password = ?"

        return try {
            val url = "jdbc:jtds:sqlserver://$ip;databaseName=$bd"
            DriverManager.getConnection(url, usuario, this.password).use { connection ->
                connection.prepareStatement(sql).use { statement ->
                    statement.setString(1, username)
                    statement.setString(2, password)

                    val resultSet: ResultSet = statement.executeQuery()
                    resultSet.next() // Devuelve true si el usuario fue encontrado
                }
            }
        } catch (e: SQLException) {
            Log.e("Database Error", "Error en la autenticación: ${e.message}")
            false // Devuelve false en caso de error
        }
    }

    fun getUserData(correo_gsb: String): UserData? {
        val sql = "SELECT * FROM datospersonales WHERE correo_gsb = ?"
        return try {
            val url = "jdbc:jtds:sqlserver://$ip;databaseName=$bd"
            DriverManager.getConnection(url, usuario, this.password).use { connection ->
                connection.prepareStatement(sql).use { statement ->
                    statement.setString(1, correo_gsb)

                    val resultSet: ResultSet = statement.executeQuery()
                    if (resultSet.next()) {
                        // Asumiendo que la tabla tiene las columnas 'Nombre', 'ApellidoPaterno', 'ApellidoMaterno', etc.
                        val nombre = resultSet.getString("Nombre")
                        val apellidoPaterno = resultSet.getString("Apellido_Paterno")
                        val apellidoMaterno = resultSet.getString("Apellido_Materno")
                        val correoPersonal = resultSet.getString("Correo_electronico_personal") // Asegúrate que el nombre de columna sea correcto
                        val correogsb = resultSet.getString("correo_gsb")
                        val telefonoMovil = resultSet.getString("telefono_movil") // Asegúrate que el nombre de columna sea correcto
                        val nacionalidad = resultSet.getString("nacionalidad") // Asegúrate que el nombre de columna sea correcto
                        val fechaNacimiento = resultSet.getString("fecha_nacimiento") // Asegúrate que el nombre de columna sea correcto
                        val identificadorFiscal = resultSet.getString("identificador_fiscal") // Asegúrate que el nombre de columna sea correcto
                        val numeroIdentificacion = resultSet.getString("numero_identificacion") // Asegúrate que el nombre de columna sea correcto
                        val vigenciaId = resultSet.getString("vigencia_id") // Asegúrate que el nombre de columna sea correcto
                        val direccion = resultSet.getString("direccion") // Asegúrate que el nombre de columna sea correcto
                        val codigoPostal = resultSet.getString("codigo_postal") // Asegúrate que el nombre de columna sea correcto
                        val ciudad = resultSet.getString("ciudad") // Asegúrate que el nombre de columna sea correcto
                        val estadoProvincia = resultSet.getString("estado_provincia") // Asegúrate que el nombre de columna sea correcto
                        val genero = resultSet.getString("genero") // Asegúrate que el nombre de columna sea correcto
                        val estadoCivil = resultSet.getString("estado_civil") // Asegúrate que el nombre de columna sea correcto
                        val numeroHijos = resultSet.getString("numero_hijos") // Asegúrate que el nombre de columna sea correcto
                        val nombreContactoEmergencia = resultSet.getString("nombre_contacto_emergencia") // Asegúrate que el nombre de columna sea correcto
                        val parentesco = resultSet.getString("parentesco") // Asegúrate que el nombre de columna sea correcto
                        val telefonoEmergencias = resultSet.getString("Telefono_emergencias") // Asegúrate que el nombre de columna sea correcto
                        val nivelEstudios = resultSet.getString("Nivel_Estudios") // Asegúrate que el nombre de columna sea correcto
                        val situacionAcademica = resultSet.getString("Situacion_Academica") // Asegúrate que el nombre de columna sea correcto
                        val nss = resultSet.getString("NSS") // Asegúrate que el nombre de columna sea correcto
                        val vigenciaSs = resultSet.getString("Vigencia_SS") // Asegúrate que el nombre de columna sea correcto
                        val curp = resultSet.getString("CURP") // Asegúrate que el nombre de columna sea correcto
                        val banco = resultSet.getString("Banco") // Asegúrate que el nombre de columna sea correcto
                        val numeroCuenta = resultSet.getString("Numero_Cuenta") // Asegúrate que el nombre de columna sea correcto
                        val clabeInterbancaria = resultSet.getString("Clabe_Interbancaria") // Asegúrate que el nombre de columna sea correcto
                        val certificacionesVigentes = resultSet.getString("Certificaciones_Vigentes") // Asegúrate que el nombre de columna sea correcto
                        val puesto = resultSet.getString("Puesto") // Asegúrate que el nombre de columna sea correcto
                        val areaDepartamento = resultSet.getString("Area_Departamento") // Asegúrate que el nombre de columna sea correcto
                        val jefeDirecto = resultSet.getString("Jefe_Directo") // Asegúrate que el nombre de columna sea correcto
                        val fechaContratacion = resultSet.getString("Fecha_Contratacion") // Asegúrate que el nombre de columna sea correcto
                        val horarioLaboral = resultSet.getString("Horario_Laboral") // Asegúrate que el nombre de columna sea correcto
                        val tipoEmpleado = resultSet.getString("Tipo_Empleado") // Asegúrate que el nombre de columna sea correcto
                        // Puedes agregar más campos según sea necesario

                        UserData(
                            nombre,
                            apellidoPaterno,
                            apellidoMaterno,
                            correoPersonal,
                            correogsb,
                            telefonoMovil,
                            nacionalidad,
                            fechaNacimiento,
                            identificadorFiscal,
                            numeroIdentificacion,
                            vigenciaId,
                            direccion,
                            codigoPostal,
                            ciudad,
                            estadoProvincia,
                            genero,
                            estadoCivil,
                            numeroHijos,
                            nombreContactoEmergencia,
                            parentesco,
                            telefonoEmergencias,
                            nivelEstudios,
                            situacionAcademica,
                            nss,
                            vigenciaSs,
                            curp,
                            banco,
                            numeroCuenta,
                            clabeInterbancaria,
                            certificacionesVigentes,
                            puesto,
                            areaDepartamento,
                            jefeDirecto,
                            fechaContratacion,
                            horarioLaboral,
                            tipoEmpleado
                        ) // Crear una instancia de UserData
                    } else {
                        null // Retorna null si no se encontró el usuario
                    }
                }
            }
        } catch (e: SQLException) {
            Log.e("Database Error", "Error en la consulta de datos: ${e.message}")
            null // Retorna null en caso de error
        }
    }

    fun registerUser(
        name: String,
        lastnameMother: String,
        lastnameFather: String,
        email: String,
        number: String,
        jefe: String
    ): Boolean {
        val sql = "INSERT INTO registroAppGsb (Nombre, ApellidoMaterno, ApellidoPaterno, Correo_gsb, NumeroMovil, JefeDirecto) VALUES (?, ?, ?, ?, ?, ?)"

        return try {
            val url = "jdbc:jtds:sqlserver://$ip;databaseName=$bd"
            DriverManager.getConnection(url, usuario, this.password).use { connection ->
                connection.prepareStatement(sql).use { statement ->
                    statement.setString(1, name)
                    statement.setString(2, lastnameMother)
                    statement.setString(3, lastnameFather)
                    statement.setString(4, email)
                    statement.setString(5, number)
                    statement.setString(6, jefe)

                    val rowsAffected = statement.executeUpdate()
                    rowsAffected > 0 // Devuelve true si al menos una fila fue insertada
                }
            }
        } catch (e: SQLException) {
            Log.e("Database Error", "Error en la inserción: ${e.message}")
            false // Devuelve false en caso de error
        }
    }
}

data class UserData(
    val nombre: String,
    val apellidoPaterno: String,
    val apellidoMaterno: String,
    val correoPersonal: String,
    val correoGsb: String,
    val telefonoMovil: String,
    val nacionalidad: String,
    val fechaNacimiento: String,
    val identificadorFiscal: String,
    val numeroIdentificacion: String,
    val vigenciaId: String,
    val direccion: String,
    val codigoPostal: String,
    val ciudad: String,
    val estadoProvincia: String,
    val genero: String,
    val estadoCivil: String,
    val numeroHijos: String,
    val nombreContactoEmergencia: String,
    val parentesco: String,
    val telefonoEmergencias: String,
    val nivelEstudios: String,
    val situacionAcademica: String,
    val nss: String,
    val vigenciaSs: String,
    val curp: String,
    val banco: String,
    val numeroCuenta: String,
    val clabeInterbancaria: String,
    val certificacionesVigentes: String,
    val puesto: String,
    val areaDepartamento: String,
    val jefeDirecto: String,
    val fechaContratacion: String,
    val horarioLaboral: String,
    val tipoEmpleado: String
)


