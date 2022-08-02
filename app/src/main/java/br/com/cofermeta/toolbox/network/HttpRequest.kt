package br.com.cofermeta.toolbox.network

interface SimpleHttpRequest {
    fun connect(serviceName: String, requestBody: String): String
}