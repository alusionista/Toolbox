package br.com.cofermeta.toolbox.network

import android.content.Context
import android.util.Log
import br.com.cofermeta.toolbox.data.model.Jsession
import com.google.gson.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.*

class SankhyaAuth : Connection() {

    fun verifyLogin(context: Context, user: String, password: String, jsession: Jsession) {
        jsession.user = user
        jsession.password = password
        if (isOnline(context)) {
            MainScope().launch(Dispatchers.IO) {
                SankhyaAuth().getJsessionId(jsession)
                return@launch
            }
            while (true) {
                if (jsession.id.isNotEmpty() || jsession.statusMessage.isNotEmpty()) break
                Thread.sleep(threadSleep)
            }
        } else jsession.statusMessage = connectionErrorMessage
    }

    fun updateJsession(jsession: Jsession) {
            SankhyaAuth().getJsessionId(jsession)
        while (true) {
            if (jsession.id.isNotEmpty() || jsession.statusMessage.isNotEmpty()) break
            Thread.sleep(threadSleep)
        }
    }

    fun getJsessionId(jsession: Jsession): Jsession {
        val response = connect(
            serviceName = loginService,
            requestBody = loginBody(jsession.user, jsession.password)
        )
        val rs = JsonParser.parseString(response)

        if (response.contains("status")) jsession.status = rs.asJsonObject["status"].asString
        if (response.contains("responseBody")) {
            jsession.responseBody =
                rs.asJsonObject["responseBody"].toString()
        }
        if (response.contains("statusMessage")) jsession.statusMessage =
            rs.asJsonObject["statusMessage"].asString
        if (response.contains("jsessionid")) {
            jsession.id = rs.asJsonObject["responseBody"]
                .asJsonObject["jsessionid"]
                .asJsonObject["$"]
                .asString
        }

        jsession.time = Calendar.getInstance().time

        Log.d("response", response)
        Log.d("jsession status", jsession.status)
        Log.d("jsession id", jsession.id)
        Log.d("jsession time", jsession.time.toString())
        Log.d("jsession user", jsession.user)
        Log.d("jsession password", jsession.password)
        Log.d("jsession statusMessage", jsession.statusMessage)
        return jsession
    }

    private fun logout(id: String) {
        val response = connect(
            serviceName = logoutService,
            requestBody = logoutBody,
            jsessionid = id)
        Log.d("logout", response)
    }

    fun clearJsession(jsession: Jsession) {
        jsession.id = ""
        jsession.responseBody = ""
        jsession.status = ""
        jsession.user = ""
        jsession.password = ""
        jsession.statusMessage = ""
    }

}
