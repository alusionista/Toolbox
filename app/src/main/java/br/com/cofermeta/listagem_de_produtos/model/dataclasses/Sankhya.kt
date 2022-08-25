package br.com.cofermeta.listagem_de_produtos.model.dataclasses

import br.com.cofermeta.listagem_de_produtos.data.CODEMP_1
import br.com.cofermeta.listagem_de_produtos.data.INTEGRACAO_PASSWORD
import br.com.cofermeta.listagem_de_produtos.data.INTEGRACAO

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
