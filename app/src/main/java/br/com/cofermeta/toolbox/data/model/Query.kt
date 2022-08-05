package br.com.cofermeta.toolbox.data.model

import android.content.Context
import android.util.Log
import br.com.cofermeta.toolbox.data.model.dataClass.ProductQuery
import br.com.cofermeta.toolbox.data.model.dataClass.Sankhya
import br.com.cofermeta.toolbox.data.values.connectionErrorMessage
import br.com.cofermeta.toolbox.data.values.loadRecords
import br.com.cofermeta.toolbox.data.values.loadRecordsBody
import br.com.cofermeta.toolbox.data.values.threadSleep
import br.com.cofermeta.toolbox.network.*
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
    }

}
