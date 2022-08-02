package br.com.cofermeta.toolkit.network

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

abstract class Connection: SimpleHttpRequest {
    private fun baseUrl(serviceName: String): String {
        return "http://teste.cofermeta.com.br:8680/mge/service.sbr?serviceName=$serviceName&outputType=json"
    }

    override fun connect(serviceName: String,
                         requestBody: String
    ): String {
        var response: StringBuilder
        val conn = URL(baseUrl(serviceName)).openConnection() as HttpURLConnection
        conn.requestMethod = "POST"
        conn.setRequestProperty("Content-type", "application/json")
        conn.setRequestProperty("Accept", "application/json")
        conn.readTimeout = 10_000
        conn.connectTimeout = 10_000
        conn.doOutput = true
        conn.doInput = true
        conn.outputStream.use { stream -> stream.write(requestBody.toByteArray()) }
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
        return response.toString()
    }
}