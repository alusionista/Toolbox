package br.com.cofermeta.toolbox.network.login

import br.com.cofermeta.toolbox.data.model.Jsession
import br.com.cofermeta.toolbox.network.SankhyaAuth
import br.com.cofermeta.toolbox.network.base
import br.com.cofermeta.toolbox.network.connectionErrorMessage
import br.com.cofermeta.toolbox.network.port
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.junit.Assert.*
import org.junit.Test
import java.net.InetSocketAddress

class SankhyaAuthTest {
    @Test
    fun testConnection(){
        assertFalse(InetSocketAddress("$base:$port/", port).isUnresolved)
    }

    @Test
    fun tryVerifyLogin(user: String = "integracao", password: String = "654321", jsession: Jsession = Jsession()){
        var i = 0
        do {
            MainScope().launch(Dispatchers.IO) {
                if (!InetSocketAddress("$base:$port/", port).isUnresolved) {
                    SankhyaAuth().getJsessionId(jsession)
                } else jsession.statusMessage = connectionErrorMessage
                return@launch
            }
            if (jsession.id.isNotEmpty() || jsession.statusMessage.isNotEmpty()) break
            Thread.sleep(500)
            i++
        } while (i < 10)

    }
}