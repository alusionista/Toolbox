package br.com.cofermeta.toolbox.model

import android.content.Context
import android.util.Log
import br.com.cofermeta.toolbox.model.dataclasses.QueryFields
import br.com.cofermeta.toolbox.model.dataclasses.QueryResult
import br.com.cofermeta.toolbox.model.dataclasses.Sankhya
import br.com.cofermeta.toolbox.data.*
import br.com.cofermeta.toolbox.model.dataclasses.sankhya
import br.com.cofermeta.toolbox.network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class ProductQuery : Connection() {

    fun tryQuery(context: Context, queryField: QueryFields) {
        if (isOnline(context)) {
            val queryResult = QueryResult()
            MainScope().launch(Dispatchers.IO) {
                Auth().updateJsession(sankhya)
                ProductQuery().sankhyaQuery(sankhya,queryField, queryResult)
                return@launch
            }
            while (true) {
                if (queryResult.responseBody.isNotEmpty()) break
                Thread.sleep(threadSleep)
            }
        } else  sankhya.statusMessage = connectionErrorMessage
    }

    private fun sankhyaQuery(sankhya: Sankhya, queryField: QueryFields, queryResult: QueryResult): QueryResult {
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
        queryResult.responseBody = response
        Log.d("queryResult body", queryResult.responseBody)
        return queryResult
    }
}
