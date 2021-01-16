package server

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

val gson: Gson = Gson()
inline fun <reified T> Gson.fromJson(json: String) = fromJson<T>(json, object: TypeToken<T>() {}.type)

object BaseDeDatos {

    @JvmStatic
    fun contieneCuenta(id: String): Boolean {
        val response = APIRequest("query GetCuenta(\$id: ID!){Cuenta(id: \$id){id}}", Variables(id = id))?.getCuenta()
        return response?.exists()!!
    }

    @JvmStatic
    fun validarContrasenia(id: String, password: Int): Boolean {
        return password == obtenerContrasenia(id)
    }

    @JvmStatic
    fun obtenerSaldo(id: String): Int {
        val response: Cuenta? = APIRequest("query getCuenta(\$id: ID){Cuenta(id: \$id){saldo}}", Variables(id = id))?.getCuenta()
        return response?.saldo?.toInt()!!
    }

    @JvmStatic
    fun last(id: String): String {
        /*var last = APIRequest("{TransaccionesPorCuenta(idCuenta:\"$id\"){fecha{dia,mes,anno},id,operacionTipo,operacionDescripcion,idCuenta}}")
        last = if(last.length < 32) return "No hay transacciones disponibles" else last.substring(32, last.length)
        var result = "Día = "
        var j = 0
        var cache = ""
        for (i in last.indices) {
            if(last[i] == '=') {
                for (k in i+1 until last.length-1) {
                    if (last[k]!=',' && last[k]!='=' && last[k]!=' ') {
                        if (last[k].isLetterOrDigit()) {
                            result+=last[k]
                        }
                    }
                    else {
                        var l = k+1
                        cache = ""
                        while (last[l].isDigit()){
                            cache+=last[l]
                            l++
                        }
                        j++
                        val text = when (j) {
                            1 -> "Mes = "
                            2 -> "Año = "
                            3 -> "Id del movimiento = "
                            4 -> "Tipo = "
                            5 -> if(result.contains("Transferencia")) "Id de cuenta destino = " else "Valor = "
                            6 -> if(result.contains("Transferencia")) "Valor = $cache\nId de cuenta = " else "Id de cuenta = "
                            else -> return result
                        }
                        result += "\n$text"
                        break
                    }
                }
            }
        }*/
        return "" /*result*/
    }

    @JvmStatic
    fun retiro(id: String, valor: Int): Boolean {
        return try {
            APIRequest("mutation {CrearTransaccion(input:{operacionTipo:\"Retiro\",idCuenta:$id,operacionDescripcion:\"$valor\"}) {operacionTipo,idCuenta,operacionDescripcion}}")
            true
        } catch (e: Exception) {
            false
        }
    }

    @JvmStatic
    fun transaccion(id: String, valor: Int, toId: String): Boolean {
        return try {
            APIRequest("mutation%7BCrearTransaccion(input%3A%7BoperacionTipo%3A\"Transferencia\"%2CidCuenta%3A$id%2CoperacionDescripcion%3A\"$toId%20$valor\"%7D)%7BoperacionTipo%2CidCuenta%2CoperacionDescripcion%7D%7D")
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    private fun obtenerContrasenia(id: String): Int {
        /*var contrasenia = APIRequest("{Cuenta(id:$id){contrasenna}}")
        //elimina no numeros a la izquierda
        while (!contrasenia[0].isDigit()){
            contrasenia = contrasenia.substring(1,contrasenia.length)
        }
        //elimina los no numeros a la derecha
        while (!contrasenia.last().isDigit()) {
            contrasenia = contrasenia.substring(0, contrasenia.length-1)
        }*/
        return 1234 /*contrasenia.toInt()*/
    }

    private fun APIRequest(query: String, variables: Variables? = null): Info? {
        return buildRetrofit().create(API::class.java).getAccount(body = Json(query, variables)).execute().body()?.data
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
    fun getAccount(@Url url: String = "", @Body body: Json): Call<Data>
}

data class Data(val data: Info)
class Info {
    private val Cuenta: Any? = null
    private val Cuentas: Any? = null
    private val Transaccion: Any? = null
    private val Transacciones: Any? = null

    fun getCuenta(): Cuenta? {
        return gson.fromJson(gson.toJson(this.Cuenta), server.Cuenta::class.java)
    }
    fun getCuentas(): ArrayList<Cuenta>? {
        return  Gson().fromJson<ArrayList<Cuenta>>(gson.toJson(this.Cuentas))
    }
    fun getTransaccion(): Transaccion? {
        return gson.fromJson(gson.toJson(this.Transaccion), server.Transaccion::class.java)
    }
    fun getTransacciones(): ArrayList<Transaccion>? {
        return  Gson().fromJson<ArrayList<Transaccion>>(gson.toJson(this.Transacciones))
    }
}

abstract class Content {
    abstract fun exists(): Boolean
}

data class Cuenta(@SerializedName("id") val id: String?, @SerializedName("saldo") val saldo: String?, @SerializedName("tipo")val tipo: String?, @SerializedName("contrasenna")val contrasenna: Int?) : Content() {
    override fun exists(): Boolean {
        return (this.id?.length!! > 0) or (this.saldo?.length!! > 0) or (this.tipo?.length!! > 0) or (this.contrasenna!! > 0)
    }
}
data class Transaccion(@SerializedName("id") val id: String?, @SerializedName("fecha") val fecha: String?, @SerializedName("operacionDescripcion")val descripcion: String?, @SerializedName("operacionTipo")val tipo: String?, @SerializedName("idCuenta")val idCuenta: String?) : Content() {
    override fun exists(): Boolean {
        return (this.id?.length!! > 0) or (this.fecha?.length!! > 0) or (this.descripcion?.length!! > 0) or (this.tipo?.length!! > 0) or (this.idCuenta?.length!! > 0)
    }
}

data class Json(@SerializedName("query")val query: String, @SerializedName("variables") val variables: Variables? = null)

data class Variables(val id: String? = null, val idCuenta: String? = null, val input: Input? = null);

data class Input(val operacionDescripcion: String, val operacionTipo: String, val idCuenta: String)