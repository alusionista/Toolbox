package br.com.cofermeta.toolbox.network.login

import br.com.cofermeta.toolbox.model.Auth
import br.com.cofermeta.toolbox.model.dataclasses.Sankhya
import br.com.cofermeta.toolbox.data.BASE
import br.com.cofermeta.toolbox.data.NO_CONNECTION_MESSAGE
import br.com.cofermeta.toolbox.data.PORT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.junit.Assert.*
import org.junit.Test
import java.net.InetSocketAddress

class AuthTest {
    @Test
    fun testConnection(){
        assertFalse(InetSocketAddress("$BASE:$PORT/", PORT).isUnresolved)
    }

    @Test
    fun tryVerifyLogin(user: String = "integracao", password: String = "654321", sankhya: Sankhya = Sankhya()){
        var i = 0
        do {
            MainScope().launch(Dispatchers.IO) {
                if (!InetSocketAddress("$BASE:$PORT/", PORT).isUnresolved) {
                    Auth().getJsessionId()
                } else sankhya.statusMessage = NO_CONNECTION_MESSAGE
                return@launch
            }
            if (sankhya.jsessionid.isNotEmpty() || sankhya.statusMessage.isNotEmpty()) break
            Thread.sleep(500)
            i++
        } while (i < 10)

    }



}