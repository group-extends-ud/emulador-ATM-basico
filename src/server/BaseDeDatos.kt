package server

import com.fasterxml.jackson.databind.ObjectMapper
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

object BaseDeDatos {

    @JvmStatic
    fun contieneCuenta(id: String): Boolean {
        return !APIRequest("{Cuenta(id:$id){saldo}}").contains("null")
    }

    @JvmStatic
    fun validarContrasenia(id: String, password: Int): Boolean {
        return password == obtenerContrasenia(id)
    }

    @JvmStatic
    fun obtenerSaldo(id: String): Int {
        var saldo = APIRequest("{Cuenta(id:$id){saldo}}")
        while (!saldo[0].isDigit()){
            saldo = saldo.substring(1,saldo.length)
        }
        while (!saldo.last().isDigit()) {
            saldo = saldo.substring(0, saldo.length-1)
        }
        return saldo.toInt()
    }

    @JvmStatic
    fun last(id: String): String {
        var last = APIRequest("")
        return ""
    }

    @JvmStatic
    fun last5(id: String): String {
        var last = APIRequest("")
        return ""
    }

    @JvmStatic
    fun retiro(id: String, valor: Int) {
        1
    }

    @JvmStatic
    fun transaccion(id: String, valor: Int, toId: String) {
        1
    }

    private fun obtenerContrasenia(id: String): Int {
        var contrasenia = APIRequest("{Cuenta(id:$id){contrasenna}}")
        while (!contrasenia[0].isDigit()){
            contrasenia = contrasenia.substring(1,contrasenia.length)
        }
        while (!contrasenia.last().isDigit()) {
            contrasenia = contrasenia.substring(0, contrasenia.length-1)
        }
        return contrasenia.toInt()
    }

    private fun APIRequest(query: String): String {
        return try {
            // Create a neat value object to hold the URL
            val url = URL("https://graphql-bank.herokuapp.com/graphql?query=$query")

            // Open a connection(?) on the URL(??) and cast the response(???)
            val connection: HttpURLConnection?
            connection = url.openConnection() as HttpURLConnection

            // Now it's "open", we can set the request method, headers etc.
            connection.setRequestProperty("accept", "application/json")

            // This line makes the request
            val responseStream = connection.inputStream

            // Manually converting the response body InputStream to APOD using Jackson
            val mapper = ObjectMapper()
            val apod = mapper.readValue(responseStream, APOD::class.java)

            // Finally we have the response
            apod.data.toString()
        } catch (e: IOException) {
            "null"
        }
    }
}
