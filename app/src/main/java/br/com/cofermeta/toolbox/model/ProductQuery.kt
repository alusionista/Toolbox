package br.com.cofermeta.toolbox.model

import android.content.Context
import android.util.Log
import br.com.cofermeta.toolbox.model.dataclasses.QueryFields
import br.com.cofermeta.toolbox.model.dataclasses.QueryResult
import br.com.cofermeta.toolbox.model.dataclasses.Sankhya
import br.com.cofermeta.toolbox.data.*
import br.com.cofermeta.toolbox.sankhya
import br.com.cofermeta.toolbox.network.*
import com.google.gson.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class ProductQuery : Connection() {

    fun tryQuery(context: Context, queryField: QueryFields, queryResult: QueryResult, auth: Auth) {
        clearQueryResult(queryResult)
        if (isOnline(context)) {
            MainScope().launch(Dispatchers.IO) {
                auth.updateJsession(sankhya)
                ProductQuery().sankhyaQuery(sankhya, queryField, queryResult)
                return@launch
            }
            while (true) {
                if (queryResult.status == "1" || queryResult.statusMessage.isNotEmpty()) break
                Thread.sleep(threadSleep)
            }
        } else queryResult.statusMessage = connectionErrorMessage
    }

    private fun sankhyaQuery(
        sankhya: Sankhya,
        queryField: QueryFields,
        queryResult: QueryResult
    ): QueryResult {
        val response = connect(
            serviceName = executeQuery,
            requestBody = queryListagemDeProdutosBody(
                referencia = queryField.referencia,
                codprod = queryField.codprod,
                marca = queryField.marca,
                locprin = queryField.locprin,
                descrprod = queryField.descrprod,
                codemp = queryField.codemp
            ),
            jsessionid = sankhya.jsessionid
        )
        checkAndUpdateProductData(response, queryResult)
        Log.d("queryResult body", response)
        Log.d("value.status", queryResult.status)
        Log.d("value.statusMessage", queryResult.statusMessage)
        Log.d("value.fieldsMetadata", queryResult.fieldsMetadata.toString())
        Log.d("value.numberOfHeaders", queryResult.numberOfHeaders.toString())
        Log.d("value.rows", queryResult.rows.toString())
        Log.d("value.numberOfRows", queryResult.numberOfRows.toString())

        return queryResult
    }

    private fun checkAndUpdateProductData(response: String, value: QueryResult) {
        val jsonElement = JsonParser.parseString(response)
        if (response.contains("status\":\"")) value.status = jsonElement.asJsonObject["status"].asString
        if (value.status == "1") {
            val responseBody = jsonElement.asJsonObject["responseBody"]
            value.numberOfHeaders = responseBody.asJsonObject["fieldsMetadata"].asJsonArray.size()
            value.fieldsMetadata = responseBody.asJsonObject["fieldsMetadata"].asJsonArray
            value.numberOfRows = responseBody.asJsonObject["rows"].asJsonArray.size()
            value.rows = responseBody.asJsonObject["rows"].asJsonArray
        } else {
            if (response.contains("statusMessage")) value.statusMessage =
                jsonElement.asJsonObject["statusMessage"].asString
        }
    }

    private fun clearQueryResult(value: QueryResult) {
        value.status = ""
        value.statusMessage = ""
        value.fieldsMetadata = null
        value.numberOfHeaders = 0
        value.rows = null
        value.numberOfRows = 0
    }
}
