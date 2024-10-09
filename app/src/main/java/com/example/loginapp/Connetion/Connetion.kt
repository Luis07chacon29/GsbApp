package com.example.loginapp

import android.util.Log
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class ConnectionBD {

    private val ip = "51.222.138.68"
    private val usuario = "App_Employees"
    private val password = "swE7uf91wU@#"
    private val bd = "GSBEmployees"

    fun authenticate(username: String, password: String): Boolean {
        val sql = "SELECT * FROM Empleado WHERE username = ? AND password = ?"

        try {
            val url = "jdbc:jtds:sqlserver://$ip;databaseName=$bd"
            DriverManager.getConnection(url, usuario, this.password).use { connection ->
                connection.prepareStatement(sql).use { statement ->
                    statement.setString(1, username)
                    statement.setString(2, password)

                    val resultSet: ResultSet = statement.executeQuery()
                    // Si hay resultados, significa que el usuario fue encontrado y autenticado
                    return resultSet.next()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return false // Devuelve false en caso de error
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
        val sql =
            "INSERT INTO registroGsbApp (Nombre, ApellidoMaterno, ApellidoPaterno, Correo, NumeroMovil, JefeDirecto) VALUES (?, ?, ?, ?, ?, ?)"

        try {
            val url = "jdbc:jtds:sqlserver://$ip;databaseName=$bd"
            DriverManager.getConnection(url, usuario, this.password).use { connection ->
                connection.prepareStatement(sql).use { statement ->

                    // Agrega el log aquí
                    Log.i(
                        "Registro User",
                        "Intentando insertar: $name, $lastnameMother, $lastnameFather, $email, $number, $jefe"
                    )

                    statement.setString(1, name)
                    statement.setString(2, lastnameMother)
                    statement.setString(3, lastnameFather)
                    statement.setString(4, email)
                    statement.setString(5, number)
                    statement.setString(6, jefe)

                    val rowsAffected = statement.executeUpdate()
                    return rowsAffected > 0 // Devuelve true si al menos una fila fue insertada
                }
            }
        } catch (e: SQLException) {
            Log.e("Database Error", "Error en la inserción: ${e.message}")
            e.printStackTrace()  // Aquí imprime el error exacto en la consola
            return false  // Devuelve false en caso de error
        }
    }
}

