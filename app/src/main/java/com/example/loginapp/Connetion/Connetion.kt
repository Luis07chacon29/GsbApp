

package com.example.loginapp

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet

class ConnectionBD {

    private val ip = "51.222.138.68"
    private val usuario = "App_Employees"
    private val password = "swE7uf91wU@#"
    private val bd = "GSBEmployees"

    fun authenticate(username: String, password: String): Boolean {
        var connection: Connection? = null
        var resultSet: ResultSet? = null

        try {
            val url = "jdbc:jtds:sqlserver://$ip;databaseName=$bd"
            connection = DriverManager.getConnection(url, usuario, this.password)

            val statement = connection.createStatement()
            resultSet = statement.executeQuery("SELECT * FROM Empleado WHERE username = '$username' AND password = '$password'")

            // Si hay resultados, significa que el usuario fue encontrado y autenticado
            return resultSet.next()
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        } finally {
            resultSet?.close()
            connection?.close()
        }
    }
}
