package br.com.cofermeta.toolbox.data.model.dataClass

import java.util.*

data class Sankhya(
    var status: String = "",
    var responseBody: String = "",
    var jsessionid: String = "",
    var statusMessage: String = "",
    var time: Date? = null,

    var user: String = "",
    var password: String = "",

    var firstName: String = "",
    var codusu: String = "",
    var codgrupo: String = "",
    var codemp: String = "",
)