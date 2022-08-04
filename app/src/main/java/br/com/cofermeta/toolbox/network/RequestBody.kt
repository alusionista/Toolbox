package br.com.cofermeta.toolbox.network

fun loginBody(user: String, password: String) =
    """
    {
    "serviceName": "$loginService",
        "requestBody": {
            "NOMUSU": {
                "$": "$user"
            },
            "INTERNO":{
                "$": "$password"
            },
            "KEEPCONNECTED": {
                "$": "S"
            }
        }
    }
    """.trimIndent()

val logoutBody =
    """
    {
        "serviceName":"$logoutService",
        "status":"1",
        "pendingPrinting":"false",
    }
    """.trimIndent()

fun loadRecordsBody(query: String) =
    """
{
  "serviceName": "$loadRecords",
  "requestBody": {
    "dataSet": {
      "rootEntity": "Produto",
      "includePresentationFields": "N",
      "offsetPage": "0",
      "criteria": {
        "expression": {
          "$": "this.CODPROD = $query"
        }
      },
      "entity": {
        "fieldset": {
          "list": "CODPROD,DESCRPROD,LOCAL,MARCA,CODVOL"
        }
      }
    }
  }
}

    """.trimIndent()

fun queryBody(query: String) =
    """
  {
    "serviceName":"$executeQuery",
    "requestBody": {
        "sql":"
            SELECT 
                CODPROD, 
                DESCRPROD, 
                LOCAL, 
                MARCA, 
                REFERENCIA 
            FROM TGFPRO 
            WHERE 
                CODPROD LIKE '%$query%' 
                    OR 
                DESCRPROD LIKE '%$query%' 
                    OR
                MARCA LIKE '%$query%' 
                    OR
                REFERENCIA LIKE '%$query%' 
        "
    }
  }
    """.trimIndent()
