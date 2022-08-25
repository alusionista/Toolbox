package br.com.cofermeta.listagem_de_produtos.data

fun loginBody(user: String, password: String) =
    """
    {
    "serviceName": "$LOGIN_SERVICE",
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
        "serviceName":"$LOGOUT_SERVICE",
        "status":"1",
        "pendingPrinting":"false",
    }
    """.trimIndent()

fun loadRecordsBody(query: String) =
    """
{
  "serviceName": "$LOAD_RECORDS",
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
    "serviceName":"$EXECUTE_QUERY",
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
    "serviceName":"$EXECUTE_QUERY",
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

fun queryListagemDeProdutosBody(
    referencia: String = "",
    codprod: String = "",
    marca: String = "",
    locprin: String = "",
    descrprod: String = "",
    codemp: String = ""
): String {
    val whereList = ArrayList<String>()
    val stringBuilder = StringBuilder()

    val _referencia = "PRO.REFERENCIA = '$referencia' "
    val _codprod = "PRO.CODPROD = $codprod "
    val _marca = "PRO.MARCA LIKE '%$marca%' "
    val _locprin = "LOCPRIN LIKE '%$locprin'%' "
    val _descricao = "PRO.DESCRPROD LIKE '%$descrprod%' "
    val _codemp = "LOC.CODEMP = $codemp "
    val _estoque = "EST.CODEMP = $codemp "

    if (referencia.isNotEmpty()) whereList.add(_referencia)
    if (codprod.isNotEmpty()) whereList.add(_codprod)
    if (marca.isNotEmpty()) whereList.add(_marca)
    if (locprin.isNotEmpty()) whereList.add(_locprin)
    if (descrprod.isNotEmpty()) whereList.add(_descricao)
    if (codemp.isNotEmpty()) {
        whereList.add(_codemp)
        whereList.add(_estoque)
    }

    for (i in 0 until whereList.size) {
        stringBuilder.append(whereList[i])
        if (i == (whereList.size - 1)) stringBuilder.append("")
        else stringBuilder.append(" AND ")
    }

    return """
  {
    "serviceName":"$EXECUTE_QUERY",
    "requestBody": {
        "sql":"
            SELECT 
                CASE
                    WHEN PRO.CODPROD = NULL THEN 0
                    ELSE PRO.CODPROD
                END 'CODPROD',
                CASE
                    WHEN PRO.MARCA = NULL THEN ' '
                    ELSE PRO.MARCA
                END 'MARCA',
                CASE
                    WHEN MAX(EXC.VLRVENDA) = NULL THEN 0
                    ELSE MAX(EXC.VLRVENDA)
                END 'VLRVENDA',
                CASE
                    WHEN PRO.DESCRPROD = NULL THEN ' '
                    ELSE PRO.DESCRPROD
                END 'DESCRPROD',
                CASE
                    WHEN PRO.ENDIMAGEM = NULL THEN ' '
                    ELSE PRO.ENDIMAGEM
                END 'ENDIMAGEM',
                CASE
                    WHEN PRO.AD_COF_CODPRODLEG = NULL THEN ' '
                    ELSE PRO.AD_COF_CODPRODLEG
                END 'CODPRODLEG',
                CASE
                    WHEN PRO.CODVOL = NULL THEN ' '
                    ELSE PRO.CODVOL
                END 'CODVOL',
                CASE
                    WHEN LOCPRIN = NULL THEN ' '
                    ELSE LOCPRIN
                END 'LOCPRIN',
                CASE
                    WHEN PRO.AD_CODPRODFORN = NULL THEN ' '
                    ELSE PRO.AD_CODPRODFORN
                END 'CODPRODFORN',
                CASE
                    WHEN PRO.REFFORN = NULL THEN ' '
                    ELSE PRO.REFFORN
                END 'REFFORN',
                CASE
                    WHEN PRO.REFERENCIA = NULL THEN ' '
                    ELSE PRO.REFERENCIA
                END 'REFERENCIA',
                CASE
                    WHEN DESCRGRUPOPROD = NULL THEN ' '
                    ELSE DESCRGRUPOPROD
                END 'DESCRGRUPOPROD',
                CASE
                    WHEN PRO.PESOLIQ = NULL THEN 0
                    ELSE PRO.PESOLIQ
                END 'PESOLIQ',
                CASE
                    WHEN PRO.ORIGPROD = NULL THEN 0
                    ELSE PRO.ORIGPROD
                END 'ORIGPROD',
                CASE
                    WHEN PRO.NCM = NULL THEN 0
                    ELSE PRO.NCM
                END 'NCM',
                CASE
                    WHEN EST.CODLOCAL = NULL THEN ' '
                    ELSE EST.CODLOCAL
                END 'CODLOCAL',
                CASE
                    WHEN EST.RESERVADO = NULL THEN 0
                    ELSE EST.RESERVADO
                END 'ESTOQUE_RESERVADO',
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
                END 'ESTMIN',
                CASE
                    WHEN EST.ESTMAX = NULL THEN 0
                    ELSE EST.ESTMAX
                END 'ESTMAX',
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
                0) 'CUSGER',
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
                0) 'CUSVARIAVEL',
                CASE
                    WHEN PAR.CODPARC = NULL THEN 0
                    ELSE PAR.CODPARC
                END 'CODPARC_FORN',
                CASE
                    WHEN PAR.NOMEPARC = NULL THEN ' '
                    ELSE PAR.NOMEPARC
                END 'FORNECEDOR',
                CASE
                    WHEN PRO.AD_NULINHA = NULL THEN 0
                    ELSE PRO.AD_NULINHA
                END 'NULINHA',
                CASE
                    WHEN LIN.DESCRICAO = NULL THEN ' '
                    ELSE LIN.DESCRICAO
                END 'DESCRICAO_LINHA',
                CASE 
                    WHEN PRO.AD_COF_CODPRODECOM = NULL THEN ' '
                    ELSE PRO.AD_COF_CODPRODECOM
                END 'CODPRODECOM'
                
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
            $stringBuilder
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
}