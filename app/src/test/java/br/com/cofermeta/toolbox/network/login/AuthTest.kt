package br.com.cofermeta.toolbox.network.login

import android.util.Log
import br.com.cofermeta.toolbox.data.model.Sankhya
import br.com.cofermeta.toolbox.network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.junit.Assert.*
import org.junit.Test
import java.net.InetSocketAddress

class AuthTest {
    @Test
    fun testConnection(){
        assertFalse(InetSocketAddress("$base:$port/", port).isUnresolved)
    }

    @Test
    fun tryVerifyLogin(user: String = "integracao", password: String = "654321", sankhya: Sankhya = Sankhya()){
        var i = 0
        do {
            MainScope().launch(Dispatchers.IO) {
                if (!InetSocketAddress("$base:$port/", port).isUnresolved) {
                    Auth().getJsessionId(sankhya)
                } else sankhya.statusMessage = connectionErrorMessage
                return@launch
            }
            if (sankhya.jsessionid.isNotEmpty() || sankhya.statusMessage.isNotEmpty()) break
            Thread.sleep(500)
            i++
        } while (i < 10)

    }



}