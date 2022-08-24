package br.com.cofermeta.toolbox.model

import android.content.Context
import android.util.Log
import br.com.cofermeta.toolbox.data.*
import br.com.cofermeta.toolbox.network.Connection
import br.com.cofermeta.toolbox.sankhya
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class Auth : Connection() {
    private fun jsonElement(value: String): JsonElement = JsonParser.parseString(value)

    fun verifyLogin(context: Context, user: String, password: String) {
        clearSankhya()
        if (isOnline(context)) {
            sankhya.user = user
            sankhya.password = password

            MainScope().launch(Dispatchers.IO) {
                this@Auth.getJsessionId()
                if (sankhya.status == "1") this@Auth.getUserData()
                createLogs()
                return@launch
            }

            while (true) {
                if (sankhya.jsessionid.isNotEmpty() || sankhya.statusMessage.isNotEmpty()) break
                Thread.sleep(SLEEP_300)
            }
        } else sankhya.statusMessage = NO_CONNECTION_MESSAGE
    }

    fun updateJsession() {
        getJsessionId()
        while (true) {
            if (sankhya.jsessionid.isNotEmpty() || sankhya.statusMessage.isNotEmpty()) break
            Thread.sleep(SLEEP_300)
        }
    }

    fun getJsessionId() {
        val response = connect(
            serviceName = LOGIN_SERVICE,
            requestBody = loginBody(sankhya.user, sankhya.password)
        )
        checkAndUpdateAuth(response)
    }

    private fun getUserData() {
        val response = connect(
            serviceName = EXECUTE_QUERY,
            requestBody = queryTSIUSUBody(sankhya.user),
            jsessionid = sankhya.jsessionid
        )
        if (sankhya.statusMessage.isEmpty()) checkAndUpdateUserData(response)
    }

    private fun checkAndUpdateAuth(response: String) {
        val jsonElement = jsonElement(response)
        if (response.contains("status\"")) sankhya.status = jsonElement.asJsonObject["status"].asString
        if (response.contains("responseBody")) {
            sankhya.responseBody =
                jsonElement.asJsonObject["responseBody"].toString()
        }
        if (response.contains("statusMessage")) sankhya.statusMessage =
            jsonElement.asJsonObject["statusMessage"].asString
        if (response.contains("jsessionid")) {
            sankhya.jsessionid = jsonElement.asJsonObject["responseBody"]
                .asJsonObject["jsessionid"]
                .asJsonObject["$"]
                .asString
        }
    }

    private fun checkAndUpdateUserData(response: String) {
        val jsonElement = jsonElement(response)
        sankhya.responseBody =
            jsonElement.asJsonObject["responseBody"].toString()
        sankhya.codusu =
            jsonElement.asJsonObject["responseBody"].asJsonObject["rows"].asJsonArray[0].asJsonArray[0].asString
        sankhya.codgrupo =
            jsonElement.asJsonObject["responseBody"].asJsonObject["rows"].asJsonArray[0].asJsonArray[2].asString
        sankhya.codemp =
            jsonElement.asJsonObject["responseBody"].asJsonObject["rows"].asJsonArray[0].asJsonArray[3].asString

        val fullName = jsonElement
            .asJsonObject["responseBody"]
            .asJsonObject["rows"]
            .asJsonArray[0].asJsonArray[1].asString
        val i: Int = fullName.indexOf(' ')
        sankhya.firstName = fullName.substring(0, i)
    }

    private fun createLogs() {
        Log.d("sankhya status", sankhya.status)
        Log.d("sankhya statusMessage", sankhya.statusMessage)
        Log.d("sankhya id", sankhya.jsessionid)
        Log.d("sankhya user", sankhya.user)
        Log.d("sankhya password", sankhya.password)
        Log.d("sankhya firstName", sankhya.firstName)
        Log.d("sankhya codusu", sankhya.codusu)
        Log.d("sankhya codgrupo", sankhya.codgrupo)
        Log.d("sankhya codemp", sankhya.codemp)
    }

    fun logout() {
        MainScope().launch(Dispatchers.IO) {
            val response = connect(
                serviceName = LOGOUT_SERVICE,
                requestBody = logoutBody,
                jsessionid = sankhya.jsessionid
            )
            if (response.contains("status\":\"1\"")) sankhya.jsessionid = ""
            return@launch
        }
    }

    private fun clearSankhya() {
        sankhya.status = ""
        sankhya.responseBody = ""
        sankhya.jsessionid = ""
        sankhya.statusMessage = ""

        sankhya.user = ""
        sankhya.password = ""

        sankhya.firstName = ""
        sankhya.codusu = ""
        sankhya.codgrupo = ""
        sankhya.codemp = ""
    }

}
