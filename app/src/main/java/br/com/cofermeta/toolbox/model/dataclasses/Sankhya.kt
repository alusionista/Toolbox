package br.com.cofermeta.toolbox.model.dataclasses

import java.util.*

var sankhya = Sankhya()
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
