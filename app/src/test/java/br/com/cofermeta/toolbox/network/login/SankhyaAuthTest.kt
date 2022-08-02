package br.com.cofermeta.toolbox.network.login

import br.com.cofermeta.toolbox.data.model.Jsession

import org.junit.Test

internal class SankhyaAuthTest {
    @Test
    fun verifyLogin() {
        val foo = Jsession()
        SankhyaAuth().verifyLogin("integracao", "654321", foo)
        println(foo.responseBody)
        println(foo.status)
        println(foo.id)
        println(foo.user)
        println(foo.password)
    }
}