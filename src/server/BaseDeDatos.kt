package server

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.lang.NullPointerException

val gson: Gson = Gson()
inline fun <reified T> Gson.fromJson(json: String) = fromJson<T>(json, object: TypeToken<T>() {}.type)

object BaseDeDatos {

    @JvmStatic
    fun contieneCuenta(id: String): Boolean {
        val response = apiRequest("query GetID(\$id: ID!){Cuenta(id: \$id){id}}", Variables(id = id))?.getCuenta()
        return response != null
    }

    @JvmStatic
    fun login(id: String, password: Int): Boolean {
        val response = apiRequest("query Login(\$id: ID!, \$contrasenna: Int!){Login(idCuenta: \$id, contrasenna: \$contrasenna)}", Variables(id = id, contrasenna = password))
        return response?.getLogin() ?: false
    }

    @JvmStatic
    fun obtenerSaldo(id: String): Int {
        val response: Cuenta? = apiRequest("query GetSaldo(\$id: ID!){Cuenta(id: \$id){saldo}}", Variables(id = id))?.getCuenta()
        return response?.saldo?.toInt() ?: 0
    }

    @JvmStatic
    fun last(id: String): String {
        val transaccion = apiRequest("query UltimaTransaccion(\$id: ID!){Transaccion: UltimaTransaccion(idCuenta: \$id){id fecha operacionDescripcion operacionTipo idCuenta}}", Variables(id = id))?.getTransaccion()
        return transaccion?.toString() ?: "No hay transacciones disponibles"
    }

    @JvmStatic
    fun retiro(id: String, valor: Int): Boolean {
        val response = apiRequest("mutation Retirar(\$input: TransaccionInputRequired!){Transaccion: CrearTransaccion(input: \$input){id}}", Variables(input = Input(idCuenta = id, operacionTipo = "Retiro", operacionDescripcion = valor.toString())))?.getTransaccion()
        return response != null
    }

    @JvmStatic
    fun transaccion(id: String, valor: Int, toId: String): Boolean {
        val response = apiRequest("mutation Transferir(\$input: TransaccionInputRequired!){Transaccion: CrearTransaccion(input: \$input){id}}", Variables(input = Input(idCuenta = id, operacionTipo = "Transferencia", operacionDescripcion = "$toId $valor")))?.getTransaccion()
        return response != null
    }

    private fun apiRequest(query: String, variables: Variables? = null): Info? {
        val response = buildRetrofit().create(API::class.java).query(body = Json(query, variables)).execute().body()
        return response?.data
    }

    private fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://graphql-bank.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}

interface API {
    @POST
    fun query(@Url url: String = "", @Body body: Json): Call<Data>
}

data class Data(val data: Info)
class Info {
    private val Cuenta: Any? = null
    private val Cuentas: Any? = null
    private val Transaccion: Any? = null
    private val Transacciones: Any? = null
    private val Login: Any? = null

    fun getCuenta(): Cuenta? {
        return gson.fromJson(gson.toJson(this.Cuenta), server.Cuenta::class.java)
    }
    fun getCuentas(): ArrayList<Cuenta>? {
        return  gson.fromJson<ArrayList<Cuenta>>(gson.toJson(this.Cuentas))
    }
    fun getTransaccion(): Transaccion? {
        return gson.fromJson(gson.toJson(this.Transaccion), server.Transaccion::class.java)
    }
    fun getTransacciones(): ArrayList<Transaccion>? {
        return  gson.fromJson<ArrayList<Transaccion>>(gson.toJson(this.Transacciones))
    }

    fun getLogin(): Boolean {
        return if(this.Login != null) gson.fromJson(gson.toJson(this.Login), Boolean::class.java) else false
    }

    override fun toString(): String {
        return this.getCuenta().toString() + " " + getCuentas().toString() + " " + getTransaccion().toString() + " " + getTransacciones().toString() + " " + getLogin().toString()
    }
}

data class Cuenta(@SerializedName("id") val id: String?, @SerializedName("saldo") val saldo: String?, @SerializedName("tipo")val tipo: String?, @SerializedName("contrasenna")val contrasenna: Int?)
data class Transaccion(@SerializedName("id") val id: String?, @SerializedName("fecha") val fecha: String?, @SerializedName("operacionDescripcion")val descripcion: String?, @SerializedName("operacionTipo")val tipo: String?, @SerializedName("idCuenta")val idCuenta: String?)

data class Json(@SerializedName("query")val query: String, @SerializedName("variables") val variables: Variables? = null)

data class Variables(val id: String? = null, val idCuenta: String? = null, val contrasenna : Int? = null, val input: Input? = null);

data class Input(val operacionDescripcion: String, val operacionTipo: String, val idCuenta: String)