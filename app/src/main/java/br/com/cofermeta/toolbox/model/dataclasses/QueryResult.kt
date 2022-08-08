package br.com.cofermeta.toolbox.model.dataclasses

data class QueryResult(
    var status: String = "",
    var statusMessage: String = "",
    var responseBody: String = "",

    var header: String = "",
    var rows: String = ""
)
