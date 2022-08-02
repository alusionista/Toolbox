package br.com.cofermeta.toolbox.data.model

import java.util.*

data class JsessionDataClass(
    var user: String = "",
    var password: String = "",
    var status: String = "",
    var responseBody: String = "",
    var id: String = "",
    var statusMessage: String = "",
    var time: Date? = null
)