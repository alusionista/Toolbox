package br.com.cofermeta.toolbox.network

import android.content.Context
import android.util.Log
import br.com.cofermeta.toolbox.data.model.Sankhya
import br.com.cofermeta.toolbox.data.model.ProductQuery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class Query : Connection() {

    fun tryQuery(context: Context, sankhya: Sankhya, productQuery: ProductQuery, query: String) {
        if (isOnline(context)) {
            MainScope().launch(Dispatchers.IO) {
                Auth().updateJsession(sankhya)
                Query().query(sankhya, productQuery, query)
                return@launch
            }
            while (true) {
                if (productQuery.body.isNotEmpty()) break
                Thread.sleep(threadSleep)
            }
        } else  sankhya.statusMessage = connectionErrorMessage
    }

    private fun query(sankhya: Sankhya, productQuery: ProductQuery, query: String): ProductQuery {
        val response = connect(
            serviceName = loadRecords,
            requestBody = loadRecordsBody(query),
            jsessionid = sankhya.jsessionid
        )
        productQuery.body = response
        Log.d("productQuery body", productQuery.body)

        return productQuery
/*


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

        logout()
        return jsession
        */
    }

}
