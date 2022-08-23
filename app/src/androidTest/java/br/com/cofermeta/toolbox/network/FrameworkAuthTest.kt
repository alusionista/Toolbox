package br.com.cofermeta.toolbox.network

import br.com.cofermeta.toolbox.data.BASE
import br.com.cofermeta.toolbox.data.PORT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.junit.Assert.*
import org.junit.Test
import java.net.InetSocketAddress

class FrameworkAuthTest {
    @Test
    fun testConnection(){
        MainScope().launch(Dispatchers.IO) {
            assertFalse(InetSocketAddress("$BASE:$PORT/", PORT).isUnresolved)
            return@launch
        }
    }

    @org.junit.Test
    fun verifyLogin() {
    }

    @org.junit.Test
    fun getJsessionId() {
    }

    @org.junit.Test
    fun clearJsession() {
    }
}