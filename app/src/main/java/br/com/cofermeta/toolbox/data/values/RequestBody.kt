package br.com.cofermeta.toolbox.data.values

import br.com.cofermeta.toolbox.data.model.Query

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

fun queryTSIUSUBody(user: String) =
    """
  {
    "serviceName":"$executeQuery",
    "requestBody": {
        "sql":"
            SELECT
                CODUSU,
                NOMEUSUCPLT,
                CODGRUPO,
                CODEMP
            FROM
                TSIUSU
            WHERE
                NOMEUSU = '$user'
        "
    }
  }
    """.trimIndent()


fun setQueryWhere(
    query: String,
    marca: String,
    locprin: String,
    descrprod: String,
    codemp: String
): String {
    val query_ = query.ifEmpty { "Login não realizado" }
    return """              
    (PRO.CODPROD = $query
        OR $query IS NULL)
    AND

    (PRO.MARCA = $marca
        OR $marca IS NULL)
    AND

    (LOCPRIN LIKE '%$locprin'%'
        OR $locprin IS NULL)
    AND

    (PRO.DESCRPROD LIKE '%$descrprod%'
        OR $descrprod IS NULL)
    AND
        LOC.CODEMP IN (2)
    AND
    
    EST.CODEMP = $codemp

    """.trimIndent()
}


fun queryListagemDeProdutosBody(query: String) =
    """
  {
    "serviceName":"$executeQuery",
    "requestBody": {
        "sql":"
            SELECT 
                CASE
                    WHEN PRO.CODPROD = NULL THEN 0
                    ELSE PRO.CODPROD
                END 'COD_PROD',
                CASE
                    WHEN PRO.AD_COF_CODPRODLEG = NULL THEN ' '
                    ELSE PRO.AD_COF_CODPRODLEG
                END 'CODIGO_LEGADO',
                CASE
                    WHEN PRO.DESCRPROD = NULL THEN ' '
                    ELSE PRO.DESCRPROD
                END 'PRODUTO',
                CASE
                    WHEN PRO.CODVOL = NULL THEN ' '
                    ELSE PRO.CODVOL
                END 'UNIDADE_MEDIDA',
                CASE
                    WHEN LOCPRIN = NULL THEN ' '
                    ELSE LOCPRIN
                END 'LOC_PRINCIPAL',
                CASE
                    WHEN MAX(EXC.VLRVENDA) = NULL THEN 0
                    ELSE MAX(EXC.VLRVENDA)
                END 'PRECO',
                CASE
                    WHEN PRO.AD_CODPRODFORN = NULL THEN ' '
                    ELSE PRO.AD_CODPRODFORN
                END 'CODIGO_FORNECEDOR',
                CASE
                    WHEN PRO.REFFORN = NULL THEN ' '
                    ELSE PRO.REFFORN
                END 'REFERENCIA_FORNECEDOR',
                CASE
                    WHEN PRO.REFERENCIA = NULL THEN ' '
                    ELSE PRO.REFERENCIA
                END 'CODIGO_DE_BARRAS',
                CASE
                    WHEN PRO.MARCA = NULL THEN ' '
                    ELSE PRO.MARCA
                END 'MARCA',
                CASE
                    WHEN PRO.REFFORN = NULL THEN ' '
                    ELSE PRO.REFFORN
                END 'REF_FORNECEDOR',
                CASE
                    WHEN DESCRGRUPOPROD = NULL THEN ' '
                    ELSE DESCRGRUPOPROD
                END 'CATEGORIA',
                CASE
                    WHEN PRO.PESOLIQ = NULL THEN 0
                    ELSE PRO.PESOLIQ
                END 'PESO',
                CASE
                    WHEN PRO.ORIGPROD = NULL THEN 0
                    ELSE PRO.ORIGPROD
                END 'ORIGEM',
                CASE
                    WHEN PRO.NCM = NULL THEN 0
                    ELSE PRO.NCM
                END 'NCM',
                CASE
                    WHEN PRO.ORIGPROD = NULL THEN ' '
                    ELSE PRO.ORIGPROD
                END 'ORIGEM_PRODUTO',
                CASE
                    WHEN EST.CODLOCAL = NULL THEN ' '
                    ELSE EST.CODLOCAL
                END 'COD_LOCAL',
                CASE
                    WHEN EST.RESERVADO = NULL THEN 0
                    ELSE EST.RESERVADO
                END 'EST_RESERVADO',
                CASE
                    WHEN (EST.ESTOQUE - EST.RESERVADO) < EST.ESTMIN THEN 'ESTOQUE MIN > ESTOQUE'
                    ELSE 'ESTOQUE NORMAL'
                END AS SITUACAO_ESTOQUE,
                CASE
                    WHEN EST.ESTOQUE = NULL THEN 0
                    ELSE EST.ESTOQUE
                END 'ESTOQUE',
                CASE
                    WHEN (EST.ESTOQUE - EST.RESERVADO) = NULL THEN 0
                    ELSE (EST.ESTOQUE - EST.RESERVADO)
                END 'ESTOQUE_DISPONIVEL',
                CASE
                    WHEN EST.ESTMIN = NULL THEN 0
                    ELSE EST.ESTMIN
                END 'ESTOQUE_MINIMO',
                CASE
                    WHEN EST.ESTMAX = NULL THEN 0
                    ELSE EST.ESTMAX
                END 'ESTOQUE_MAXIMO',
                CASE
                    WHEN PRO.ATIVO = 'S' THEN 'SIM'
                    ELSE 'NÃO'
                END 'ATIVO',
                -- ULTIMO CUSTO GERENCIAL
            
                ISNULL((
                SELECT
                    CUSGER
                FROM
                    TGFCUS
                WHERE
                    CODPROD = PRO.CODPROD
                    AND CODEMP = EST.CODEMP
                    AND DTATUAL = (
                    SELECT
                        MAX(DTATUAL)
                    FROM
                        TGFCUS
                    WHERE
                        CODPROD = PRO.CODPROD)),
                0) 'CUSTO_GERENCIAL',
                -- ULTIMO CUSTO REPOSIÇÃO  
            
                ISNULL((
                SELECT
                    CUSREP
                FROM
                    TGFCUS
                WHERE
                    CODPROD = PRO.CODPROD
                    AND CODEMP = EST.CODEMP
                    AND DTATUAL = (
                    SELECT
                        MAX(DTATUAL)
                    FROM
                        TGFCUS
                    WHERE
                        CODPROD = PRO.CODPROD)),
                0) 'CUSREP',
                ISNULL((
                SELECT
                    CUSVARIAVEL
                FROM
                    TGFCUS
                WHERE
                    CODPROD = PRO.CODPROD
                    AND CODEMP = EST.CODEMP
                    AND DTATUAL = (
                    SELECT
                        MAX(DTATUAL)
                    FROM
                        TGFCUS
                    WHERE
                        CODPROD = PRO.CODPROD)),
                0) 'CUSVAR',
                CASE
                    WHEN PAR.CODPARC = NULL THEN 0
                    ELSE PAR.CODPARC
                END 'COD_FORN',
                CASE
                    WHEN PAR.NOMEPARC = NULL THEN ' '
                    ELSE PAR.NOMEPARC
                END 'FORNECEDOR',
                CASE
                    WHEN PRO.AD_NULINHA = NULL THEN 0
                    ELSE PRO.AD_NULINHA
                END 'COD_LINHA',
                CASE
                    WHEN LIN.DESCRICAO = NULL THEN ' '
                    ELSE LIN.DESCRICAO
                END 'LINHA',
                CASE 
                    WHEN PRO.AD_COF_CODPRODECOM = NULL THEN ' '
                    ELSE PRO.AD_COF_CODPRODECOM 
                END 'CODPRODECOM',
                PRO.ENDIMAGEM
                
            FROM
                TGFPRO PRO
            
            /*INNER JOIN AD_TBHPRO DIV ON PRO.CODPROD = DIV.CODPROD*/
            LEFT JOIN TGFPAR PAR ON
                PRO.CODPARCFORN = PAR.CODPARC
            LEFT JOIN TGFEST EST ON
                PRO.CODPROD = EST.CODPROD
                AND EST.CODLOCAL = 200
                AND EST.ATIVO = 'S'
            LEFT JOIN TGFIPI IPI ON
                PRO.CODIPI = IPI.CODIPI
            LEFT JOIN TGFGRU GRU ON
                PRO.CODGRUPOPROD = GRU.CODGRUPOPROD
            LEFT JOIN TGFVOL VOL ON
                PRO.CODVOL = VOL.CODVOL
            LEFT JOIN TGFEXC EXC ON
                EXC.CODPROD = PRO.CODPROD
            LEFT JOIN TSIEMP EMP ON
                PAR.CODEMP = EMP.CODEMP
            LEFT JOIN AD_TGFGDT ON
                PRO.CODPROD = AD_TGFGDT.CODPROD
            LEFT JOIN AD_TBHLOC LOC ON
                PRO.CODPROD = LOC.CODPROD
            LEFT JOIN AD_TBHLIN LIN ON
                PRO.AD_NULINHA = LIN.NULINHA
                AND LIN.CODPARC = PAR.CODPARC
            WHERE






            GROUP BY
                AD_COF_CODPRODECOM,
                PRO.CODPROD,
                PRO.AD_COF_CODPRODLEG,
                PRO.DESCRPROD,
                PRO.CODVOL,
                PRO.AD_CODPRODFORN,
                PRO.REFFORN,
                PRO.REFERENCIA,
                DESCRGRUPOPROD,
                PRO.PESOLIQ,
                PRO.ORIGPROD,
                PRO.NCM,
                PRO.ORIGPROD,
                EST.ESTMIN,
                EST.ESTMAX,
                EST.CODLOCAL,
                PRO.MARCA,
                EST.ESTOQUE,
                EST.RESERVADO,
                LOCPRIN,
                PRO.ATIVO,
                EST.CODEMP,
                PAR.CODPARC,
                PAR.NOMEPARC,
                PRO.AD_NULINHA,
                LIN.DESCRICAO,
                CORFONTCONSPRECO,
                PRO.AD_COF_CODPRODECOM,
                PRO.ENDIMAGEM
            ORDER BY
                PRO.CODPROD
        "
    }
  }
    """.trimIndent()
