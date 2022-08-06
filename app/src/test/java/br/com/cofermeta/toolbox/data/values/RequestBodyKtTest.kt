package br.com.cofermeta.toolbox.data.values

import org.junit.Test
import org.junit.Assert.*


internal class RequestBodyKtTest {
    @Test
    fun requestBody() {
        assertEquals(
            setQueryWhere(
                "asd",
                "asd",
                "asd",
                "asd",
                "asd",
                "asd",
            ),
            "PRO.REFERENCIA = asd  AND PRO.CODPROD = asd  AND PRO.MARCA LIKE '%asd%'  AND LOCPRIN LIKE '%asd'%'  AND PRO.DESCRPROD LIKE '%asd%'  AND LOC.CODEMP = asd "
        )
    }

    @Test
    fun requestBodyCodEmp() {
        val test = setQueryWhere(
            "",
            "12027",
            "",
            "",
            "",
            "2",
        )
        println(test)
        assertEquals(
            test,
            "PRO.CODPROD = 12027  AND LOC.CODEMP = 2  AND EST.CODEMP = 2 "
        )
    }
}