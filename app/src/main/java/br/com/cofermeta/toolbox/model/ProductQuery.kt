package br.com.cofermeta.toolbox.model

import android.content.Context
import android.util.Log
import br.com.cofermeta.toolbox.model.dataclasses.*
import br.com.cofermeta.toolbox.data.*
import br.com.cofermeta.toolbox.sankhya
import br.com.cofermeta.toolbox.network.*
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class ProductQuery(private val queryResult: QueryResult) : Connection() {

    private val auth = Auth()
    private fun jsonElement(string: String = ""): JsonElement = JsonParser.parseString(string)

    fun tryQuery(context: Context, queryField: QueryFields): QueryResult {
        clearQueryResult()
        if (isOnline(context)) {
            MainScope().launch(Dispatchers.IO) {
                auth.updateJsession()
                ProductQuery(queryResult).sankhyaQuery(queryField, queryResult)
                return@launch
            }
            while (true) {
                if (queryResult.status == "1" || queryResult.statusMessage.isNotEmpty()) break
                Thread.sleep(SLEEP_300)
            }
        } else queryResult.statusMessage = NO_CONNECTION_MESSAGE
        return queryResult
    }

    private fun sankhyaQuery(
        queryField: QueryFields,
        queryResult: QueryResult
    ): QueryResult {
        val response = connect(
            serviceName = EXECUTE_QUERY,
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
        checkAndUpdateProductData(response)
        Log.d("queryResult body", response)
        Log.d("value.status", queryResult.status)
        Log.d("value.statusMessage", queryResult.statusMessage)
        Log.d("value.fieldsMetadata", queryResult.fieldsMetadata.toString())
        Log.d("value.numberOfHeaders", queryResult.numberOfHeaders.toString())
        Log.d("value.rows", queryResult.rows.toString())
        Log.d("value.numberOfRows", queryResult.numberOfRows.toString())

        return queryResult
    }

    private fun checkAndUpdateProductData(response: String) {
        val jsonElement = jsonElement(response)
        if (response.contains("status\"")) queryResult.status = jsonElement.asJsonObject["status"].asString
        if (queryResult.status == "1") {
            val responseBody = jsonElement.asJsonObject["responseBody"]
            queryResult.numberOfHeaders = responseBody.asJsonObject["fieldsMetadata"].asJsonArray.size()
            queryResult.fieldsMetadata = responseBody.asJsonObject["fieldsMetadata"].asJsonArray
            queryResult.numberOfRows = responseBody.asJsonObject["rows"].asJsonArray.size()
            queryResult.rows = responseBody.asJsonObject["rows"].asJsonArray
        } else {
            if (response.contains("statusMessage")) queryResult.statusMessage =
                jsonElement.asJsonObject["statusMessage"].asString
        }
    }

    private fun clearQueryResult() {
        queryResult.status = ""
        queryResult.statusMessage = ""
        queryResult.fieldsMetadata = jsonElement()
        queryResult.numberOfHeaders = 0
        queryResult.rows = jsonElement()
        queryResult.numberOfRows = 0
    }
}
