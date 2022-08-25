package br.com.cofermeta.listagem_de_produtos.model.dataclasses

import br.com.cofermeta.listagem_de_produtos.data.JSON_ELEMENT
import com.google.gson.JsonElement

data class QueryResult(
    var status: String = "",
    var statusMessage: String = "",
    var fieldsMetadata: JsonElement = JSON_ELEMENT,
    var numberOfHeaders: Int = 0,
    var rows: JsonElement = JSON_ELEMENT,
    var numberOfRows: Int = 0,
)
