package br.com.cofermeta.toolbox.network

import android.util.Log
import br.com.cofermeta.toolbox.data.model.Sankhya
import com.google.gson.JsonParser
import org.junit.Assert.*
import org.junit.Test

class AuthTest : Connection() {
    @Test
    fun getUserData(sankhya: Sankhya): Sankhya {
        val response = connect(
            serviceName = executeQuery,
            requestBody = queryTSIUSUBody(sankhya.user),
            jsessionid = sankhya.jsessionid
        )
        checkAndUpdateUserData(response, sankhya)
        Log.d("getUserData body", response)

        return sankhya

    }

    @Test
    fun checkAndUpdateUserData(response: String, sankhya: Sankhya) {
        val jsonElement = JsonParser.parseString(response)
        println("responseBody = ${jsonElement.asJsonObject[" responseBody "]}")
        println("codusu = ${jsonElement.asJsonObject[" responseBody "].asJsonObject["rows"].asJsonArray.asJsonArray[0]}")
        println("firstName = ${jsonElement.asJsonObject[" responseBody "].asJsonObject["rows"].asJsonArray.asJsonArray[1]}")
        println("codgrupo = ${jsonElement.asJsonObject[" responseBody "].asJsonObject["rows"].asJsonArray.asJsonArray[2]}")
        println("codemp = ${jsonElement.asJsonObject[" responseBody "].asJsonObject["rows"].asJsonArray.asJsonArray[3]}")

    }
}