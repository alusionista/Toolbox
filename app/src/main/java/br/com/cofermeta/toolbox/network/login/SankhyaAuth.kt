package br.com.cofermeta.toolbox.network.login

import android.util.Log
import br.com.cofermeta.toolbox.data.model.JsessionDataClass
import br.com.cofermeta.toolbox.network.Connection
import com.google.gson.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.*

class SankhyaAuth: Connection() {

    fun verifyLogin(user: String, password: String, jsession: JsessionDataClass) {
        MainScope().launch(Dispatchers.IO) {
            SankhyaAuth().getJsessionId(user, password, jsession)
            return@launch
        }
    }

    //fun isJSessionIdValid(jsession: JsessionDataClass) = jsession.id.length == 40

    private fun getJsessionId(user: String, password: String, jsession: JsessionDataClass): JsessionDataClass {
        val response = connect(
            serviceName = "MobileLoginSP.login",
            requestBody = loginBody(user, password)
        )
        val rs = JsonParser.parseString(response)

        if (response.contains("status")) jsession.status = rs.asJsonObject["status"].asString
/*        if (response.contains("responseBody")) {
            jsession.responseBody =
                rs.asJsonObject["responseBody"].asJsonObject.asString
        }*/
        if (response.contains("statusMessage")) jsession.statusMessage =
            rs.asJsonObject["statusMessage"].asString
        if (response.contains("jsessionid")) {
            jsession.id = rs.asJsonObject["responseBody"]
                .asJsonObject["jsessionid"]
                .asJsonObject["$"]
                .asString
        }

        jsession.time = Calendar.getInstance().time
        jsession.user = user
        jsession.password = password

        Log.d("response", response)
        Log.d("jsession status", jsession.status)
        Log.d("jsession id", jsession.id)
        Log.d("jsession time", jsession.time.toString())
        Log.d("jsession user", jsession.user)
        Log.d("jsession password", jsession.password)
        Log.d("jsession statusMessage", jsession.statusMessage)

        logout()
        return jsession
    }

    private fun logout() {
        val response = connect("MobileLoginSP.logout", logoutBody)
        Log.d("logout", response)

    }

    private fun loginBody(user: String, password: String) =
        """
            {
                "serviceName": "MobileLoginSP.login",
                "requestBody": {
                   "NOMUSU": {
                      "$": "$user"
                   },
                   "INTERNO":{
                      "$": "$password"
                   },
                  "KEEPCONNECTED": {
                      "$": "S"
                  }
                }
            }
           """.trimIndent()

    private val logoutBody =
        """
            {
                "serviceName":"MobileLoginSP.logout",
                "status":"1",
                "pendingPrinting":"false",
            }
            """.trimIndent()


}
