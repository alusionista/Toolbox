package br.com.cofermeta.toolbox.data.model

import android.content.Context
import android.util.Log
import br.com.cofermeta.toolbox.data.model.dataClass.Sankhya
import br.com.cofermeta.toolbox.data.values.*
import br.com.cofermeta.toolbox.network.*
import com.google.gson.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.*

class Auth : Connection() {
    fun verifyLogin(context: Context, user: String, password: String, sankhya: Sankhya) {
        clearSankhya(sankhya)
        sankhya.user = user
        sankhya.password = password
        if (isOnline(context)) {
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
        checkAndUpdateUserData(response, sankhya)
        return sankhya

    }

    private fun checkAndUpdateAuth(response: String, value: Sankhya) {
        val jsonElement = JsonParser.parseString(response)
        value.time = Calendar.getInstance().time
        if (response.contains("status")) value.status = jsonElement.asJsonObject["status"].asString
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
        sankhya.firstName =
            jsonElement.asJsonObject["responseBody"].asJsonObject["rows"].asJsonArray[0].asJsonArray[1].asString
        sankhya.codgrupo =
            jsonElement.asJsonObject["responseBody"].asJsonObject["rows"].asJsonArray[0].asJsonArray[2].asString
        sankhya.codemp =
            jsonElement.asJsonObject["responseBody"].asJsonObject["rows"].asJsonArray[0].asJsonArray[3].asString

    }


    private fun createLogs(value: Sankhya) {
        Log.d("jsession id", value.jsessionid)
        Log.d("jsession time", value.time.toString())
        Log.d("jsession user", value.user)
        Log.d("jsession password", value.password)
        Log.d("jsession statusMessage", value.statusMessage)
        Log.d("jsession firstName", value.firstName)
        Log.d("jsession codusu", value.codusu)
        Log.d("jsession codgrupo", value.codgrupo)
        Log.d("jsession codemp", value.codemp)
    }

    private fun logout(id: String) {
        val response = connect(
            serviceName = logoutService,
            requestBody = logoutBody,
            jsessionid = id
        )
        Log.d("logout", response)
    }

    private fun clearSankhya(sankhya: Sankhya) {
        sankhya.user = ""
        sankhya.password = ""
        sankhya.status = ""
        sankhya.responseBody = ""
        sankhya.jsessionid = ""
        sankhya.statusMessage = ""
        sankhya.time = null
        sankhya.firstName = ""
        sankhya.codusu = ""
        sankhya.codgrupo = ""
        sankhya.codemp = ""
    }

}
