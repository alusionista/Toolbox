package br.com.cofermeta.toolbox.model

import android.content.Context
import android.util.Log
import br.com.cofermeta.toolbox.data.*
import br.com.cofermeta.toolbox.model.dataclasses.Sankhya
import br.com.cofermeta.toolbox.network.*
import com.google.gson.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.*

class Auth : Connection() {
    fun verifyLogin(context: Context, user: String, password: String, sankhya: Sankhya) {
        clearSankhya(sankhya)
        if (isOnline(context)) {
            sankhya.user = user
            sankhya.password = password
            MainScope().launch(Dispatchers.IO) {
                val auth = Auth()
                auth.getJsessionId(sankhya)
                if (sankhya.status == "1") auth.getUserData(sankhya)
                createLogs(sankhya)
                return@launch
            }
            while (true) {
                if (sankhya.jsessionid.isNotEmpty() || sankhya.statusMessage.isNotEmpty()) break
                Thread.sleep(threadSleep)
            }
        } else sankhya.statusMessage = connectionErrorMessage
    }

    fun updateJsession(sankhya: Sankhya) {
        Auth().getJsessionId(sankhya)
        while (true) {
            if (sankhya.jsessionid.isNotEmpty() || sankhya.statusMessage.isNotEmpty()) break
            Thread.sleep(threadSleep)
        }
    }

    fun getJsessionId(sankhya: Sankhya): Sankhya {
        val response = connect(
            serviceName = loginService,
            requestBody = loginBody(sankhya.user, sankhya.password)
        )
        checkAndUpdateAuth(response, sankhya)
        return sankhya
    }

    private fun getUserData(sankhya: Sankhya): Sankhya {
        val response = connect(
            serviceName = executeQuery,
            requestBody = queryTSIUSUBody(sankhya.user),
            jsessionid = sankhya.jsessionid
        )
        if (sankhya.statusMessage.isEmpty())checkAndUpdateUserData(response, sankhya)
        return sankhya

    }

    private fun checkAndUpdateAuth(response: String, value: Sankhya) {
        val jsonElement = JsonParser.parseString(response)
        value.time = Calendar.getInstance().time
        if (response.contains("status\":\"")) value.status = jsonElement.asJsonObject["status"].asString
        if (response.contains("responseBody")) {
            value.responseBody =
                jsonElement.asJsonObject["responseBody"].toString()
        }
        if (response.contains("statusMessage")) value.statusMessage =
            jsonElement.asJsonObject["statusMessage"].asString
        if (response.contains("jsessionid")) {
            value.jsessionid = jsonElement.asJsonObject["responseBody"]
                .asJsonObject["jsessionid"]
                .asJsonObject["$"]
                .asString
        }
    }

    private fun checkAndUpdateUserData(response: String, sankhya: Sankhya) {
        val jsonElement = JsonParser.parseString(response)
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

    private fun createLogs(value: Sankhya) {
        Log.d("sankhya status", value.status)
        Log.d("sankhya statusMessage", value.statusMessage)
        Log.d("sankhya id", value.jsessionid)
        Log.d("sankhya time", value.time.toString())
        Log.d("sankhya user", value.user)
        Log.d("sankhya password", value.password)
        Log.d("sankhya firstName", value.firstName)
        Log.d("sankhya codusu", value.codusu)
        Log.d("sankhya codgrupo", value.codgrupo)
        Log.d("sankhya codemp", value.codemp)
    }

    fun logout(sankhya: Sankhya) {
        MainScope().launch(Dispatchers.IO) {
            val response = connect(
                serviceName = logoutService,
                requestBody = logoutBody,
                jsessionid = sankhya.jsessionid
            )
            if (response.contains("\"status\":\"1\"")) sankhya.jsessionid = ""
            createLogs(sankhya)
            return@launch
        }
    }

    private fun clearSankhya(sankhya: Sankhya) {
        sankhya.status = ""
        sankhya.responseBody = ""
        sankhya.jsessionid = ""
        sankhya.statusMessage = ""
        sankhya.time = null

        sankhya.user = ""
        sankhya.password = ""

        sankhya.firstName = ""
        sankhya.codusu = ""
        sankhya.codgrupo = ""
        sankhya.codemp = ""
    }

}
