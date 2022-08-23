package br.com.cofermeta.toolbox.model.dataclasses

import br.com.cofermeta.toolbox.data.JSON_ELEMENT
import com.google.gson.JsonElement

data class QueryResult(
    var status: String = "",
    var statusMessage: String = "",
    var fieldsMetadata: JsonElement = JSON_ELEMENT,
    var numberOfHeaders: Int = 0,
    var rows: JsonElement = JSON_ELEMENT,
    var numberOfRows: Int = 0,
)
