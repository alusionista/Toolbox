package br.com.cofermeta.toolbox.model

import br.com.cofermeta.toolbox.data.RESPONSE_SAMPLE
import br.com.cofermeta.toolbox.model.dataclasses.QueryResult
import com.google.gson.JsonParser
import org.junit.Test

internal class ProductQueryTest {
    @Test
    fun testProductQueryOutput() {
        val response = RESPONSE_SAMPLE
        val value = QueryResult()

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

        println("value.status ${value.status}")
        println("value.statusMessage ${value.statusMessage}")
        println("value.fieldsMetadata ${value.fieldsMetadata?.asJsonArray?.get(1)}")
        println("value.numberOfHeaders ${value.numberOfHeaders}")
        println("value.rows ${value.rows?.asJsonArray?.get(1)?.asJsonArray?.get(1)}")
        println("value.numberOfRows ${value.numberOfRows}")
    }
}