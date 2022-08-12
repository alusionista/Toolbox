package br.com.cofermeta.toolbox.model.dataclasses

import br.com.cofermeta.toolbox.data.defaultCodemp
import br.com.cofermeta.toolbox.data.defaultPassword
import br.com.cofermeta.toolbox.data.defaultUser
import java.util.*

data class Sankhya(
    var status: String = "",
    var responseBody: String = "",
    var jsessionid: String = "",
    var statusMessage: String = "",
    var time: Date? = null,

    var user: String = defaultUser,
    var password: String = defaultPassword,

    var firstName: String = "",
    var codusu: String = "",
    var codgrupo: String = "",
    var codemp: String = defaultCodemp,
)
val sankhya = Sankhya()
