package br.com.cofermeta.listagem_de_produtos.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import br.com.cofermeta.listagem_de_produtos.data.BASE
import br.com.cofermeta.listagem_de_produtos.data.NO_RESPONSE_MESSAGE
import br.com.cofermeta.listagem_de_produtos.data.PORT
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.ProtocolException
import java.net.URL
import java.util.concurrent.TimeoutException

abstract class Connection {
    private fun baseUrl(serviceName: String, jsessionid: String): String {
        return if (jsessionid.isEmpty()) "$BASE:$PORT/mge/service.sbr?serviceName=$serviceName&outputType=json"
        else "$BASE:$PORT/mge/service.sbr?serviceName=$serviceName&mgeSession=$jsessionid&outputType=json"
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
        return false
    }

    @Throws(IOException::class, InterruptedException::class, TimeoutException::class, ProtocolException::class)
    fun connect(
        serviceName: String,
        requestBody: String,
        requestMethod: String,
        jsessionid: String = ""
    ): String {

        var response: StringBuilder
        val bytes = requestBody.toByteArray()
        Log.d("requestBody query", requestBody)
        val conn = URL(baseUrl(serviceName, jsessionid)).openConnection() as HttpURLConnection
        conn.requestMethod = requestMethod
        conn.setRequestProperty("Content-type", "application/json; charset=utf-8")
        conn.setRequestProperty("Accept", "application/json")
        if (jsessionid.isNotEmpty()) conn.setRequestProperty("cookie", "JSESSIONID=$jsessionid")
        conn.readTimeout = 10_000
        conn.connectTimeout = 10_000
        conn.doOutput = true
        conn.doInput = true

        try {
            conn.outputStream.use { stream -> stream.write(bytes) }
            val status: Int = conn.responseCode
            val reader = if (status < 400) {
                InputStreamReader(conn.inputStream)
            } else {
                InputStreamReader(conn.errorStream)
            }
            reader.use { stream ->
                response = StringBuilder()
                val bufferedReader = BufferedReader(stream)
                bufferedReader.useLines { lines ->
                    lines.forEach {
                        response.append(it.trim())
                    }
                }
            }
            Log.d("Response", response.toString())
            return response.toString()
        } catch (e: Exception) {
            Log.d("Exception", e.printStackTrace().toString())
            return NO_RESPONSE_MESSAGE
        }

    }
}