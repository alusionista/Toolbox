package br.com.cofermeta.toolbox.model.dataclasses

import com.google.gson.JsonElement

data class QueryResult(
    var status: String = "",
    var statusMessage: String = "",
    var fieldsMetadata: JsonElement? = null,
    var numberOfHeaders: Int = 0,
    var rows: JsonElement? = null,
    var numberOfRows: Int = 0,
    var selectedItem: Int = 0
)
