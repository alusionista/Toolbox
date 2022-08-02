package br.com.cofermeta.toolkit.network

interface SimpleHttpRequest {
    fun connect(serviceName: String, requestBody: String): String
}