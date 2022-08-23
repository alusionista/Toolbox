package br.com.cofermeta.toolbox.model.dataclasses

import br.com.cofermeta.toolbox.data.CODEMP_1
import br.com.cofermeta.toolbox.data.INTEGRACAO_PASSWORD
import br.com.cofermeta.toolbox.data.INTEGRACAO

data class Sankhya(
    var status: String = "",
    var responseBody: String = "",
    var jsessionid: String = "",
    var statusMessage: String = "",

    var user: String = INTEGRACAO,
    var password: String = INTEGRACAO_PASSWORD,

    var firstName: String = "",
    var codusu: String = "",
    var codgrupo: String = "",
    var codemp: String = CODEMP_1,
)
