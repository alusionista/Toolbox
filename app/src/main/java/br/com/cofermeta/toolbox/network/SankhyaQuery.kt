package br.com.cofermeta.toolbox.network

import android.content.Context
import android.util.Log
import br.com.cofermeta.toolbox.data.model.Jsession
import br.com.cofermeta.toolbox.data.model.ProductQuery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.*

class SankhyaQuery : Connection() {

    fun tryQuery(context: Context, jsession: Jsession, productQuery: ProductQuery) {
        if (isOnline(context)) {
            MainScope().launch(Dispatchers.IO) {
                SankhyaAuth().updateJsession(jsession)
                SankhyaQuery().query(jsession, productQuery)
                return@launch
            }
            while (true) {
                if (productQuery.body.isNotEmpty()) break
                Thread.sleep(threadSleep)
            }
        } else  jsession.statusMessage = connectionErrorMessage
    }

    private fun query(jsession: Jsession, productQuery: ProductQuery, query: String = "12027"): ProductQuery {
        val response = connect(
            serviceName = loadRecords,
            requestBody = loadRecordsBody(query),
            jsessionid = jsession.id
        )
        productQuery.body = response
        Log.d("response", response)
        Log.d("productQuery body", productQuery.body)
        logout()
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

    private fun logout() {
        val response = connect(logoutService, logoutBody)
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
