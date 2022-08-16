package br.com.cofermeta.toolbox.data

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

const val base = "http://teste.cofermeta.com.br"
const val port = 8280
const val defaultUser = ""
const val defaultPassword = ""
const val defaultCodemp = ""
const val fakeJsessionid = "dX9a9e3Xm7NBzBiPCI6Soj6hoCDktoN48OU8f_LP"
const val imgPlaceHolder = "https://via.placeholder.com/400x300.png?text=Toolbox"

const val FILTROS = "Filtros"
const val SCANNER = "Scanner"
const val CONSULTA = "Consulta"
const val USUARIO = "Usuário"

const val loginService = "MobileLoginSP.login"
const val logoutService = "MobileLoginSP.logout"
const val loadRecords = "CRUDServiceProvider.loadRecords"
const val executeQuery = "DbExplorerSP.executeQuery"

const val threadSleep: Long = 300
val defaultPadding: Dp = 18.dp
val largePadding: Dp = 36.dp

const val queryUiTitle = "Listagem de Produtos"
const val connectionErrorMessage = "Não foi possível conectar-se.\nVerifique sua conexão Wi-fi."
const val httpRequestErroRMessage = """{"statusMessage":"Não foi possível conectar-se ao Sankhya."}"""
const val searchBarPlaceHolder = "Insira o código do produto"
const val loginEmpty = "Login não realizado"
const val noProductFound = "Nenhum produto encontrado"
const val noProductSelected = "Nenhum\nproduto\nselecionado"

val sampleQueryResponseBody =
    """
        {
            "serviceName": "DbExplorerSP.executeQuery",
            "status": "1",
            "pendingPrinting": "false",
            "transactionId": "576863FE816156A2674DE18B060F8718",
            "responseBody": {
                "fieldsMetadata": [
                    {
                        "name": "COD_PROD",
                        "description": "COD_PROD",
                        "order": 1,
                        "userType": "I"
                    },
                    {
                        "name": "CODIGO_LEGADO",
                        "description": "CODIGO_LEGADO",
                        "order": 2,
                        "userType": "S"
                    },
                    {
                        "name": "PRODUTO",
                        "description": "PRODUTO",
                        "order": 3,
                        "userType": "S"
                    },
                    {
                        "name": "UNIDADE_MEDIDA",
                        "description": "UNIDADE_MEDIDA",
                        "order": 4,
                        "userType": "S"
                    },
                    {
                        "name": "LOC_PRINCIPAL",
                        "description": "LOC_PRINCIPAL",
                        "order": 5,
                        "userType": "S"
                    },
                    {
                        "name": "PRECO",
                        "description": "PRECO",
                        "order": 6,
                        "userType": "F",
                        "precision": 2
                    },
                    {
                        "name": "CODIGO_FORNECEDOR",
                        "description": "CODIGO_FORNECEDOR",
                        "order": 7,
                        "userType": "S"
                    },
                    {
                        "name": "REFERENCIA_FORNECEDOR",
                        "description": "REFERENCIA_FORNECEDOR",
                        "order": 8,
                        "userType": "S"
                    },
                    {
                        "name": "CODIGO_DE_BARRAS",
                        "description": "CODIGO_DE_BARRAS",
                        "order": 9,
                        "userType": "S"
                    },
                    {
                        "name": "MARCA",
                        "description": "MARCA",
                        "order": 10,
                        "userType": "S"
                    },
                    {
                        "name": "REF_FORNECEDOR",
                        "description": "REF_FORNECEDOR",
                        "order": 11,
                        "userType": "S"
                    },
                    {
                        "name": "CATEGORIA",
                        "description": "CATEGORIA",
                        "order": 12,
                        "userType": "S"
                    },
                    {
                        "name": "PESO",
                        "description": "PESO",
                        "order": 13,
                        "userType": "F",
                        "precision": 2
                    },
                    {
                        "name": "ORIGEM",
                        "description": "ORIGEM",
                        "order": 14,
                        "userType": "I"
                    },
                    {
                        "name": "NCM",
                        "description": "NCM",
                        "order": 15,
                        "userType": "I"
                    },
                    {
                        "name": "ORIGEM_PRODUTO",
                        "description": "ORIGEM_PRODUTO",
                        "order": 16,
                        "userType": "S"
                    },
                    {
                        "name": "COD_LOCAL",
                        "description": "COD_LOCAL",
                        "order": 17,
                        "userType": "I"
                    },
                    {
                        "name": "EST_RESERVADO",
                        "description": "EST_RESERVADO",
                        "order": 18,
                        "userType": "F",
                        "precision": 2
                    },
                    {
                        "name": "SITUACAO_ESTOQUE",
                        "description": "SITUACAO_ESTOQUE",
                        "order": 19,
                        "userType": "S"
                    },
                    {
                        "name": "ESTOQUE",
                        "description": "ESTOQUE",
                        "order": 20,
                        "userType": "F",
                        "precision": 2
                    },
                    {
                        "name": "ESTOQUE_DISPONIVEL",
                        "description": "ESTOQUE_DISPONIVEL",
                        "order": 21,
                        "userType": "F",
                        "precision": 2
                    },
                    {
                        "name": "ESTOQUE_MINIMO",
                        "description": "ESTOQUE_MINIMO",
                        "order": 22,
                        "userType": "F",
                        "precision": 2
                    },
                    {
                        "name": "ESTOQUE_MAXIMO",
                        "description": "ESTOQUE_MAXIMO",
                        "order": 23,
                        "userType": "F",
                        "precision": 2
                    },
                    {
                        "name": "ATIVO",
                        "description": "ATIVO",
                        "order": 24,
                        "userType": "S"
                    },
                    {
                        "name": "CUSTO_GERENCIAL",
                        "description": "CUSTO_GERENCIAL",
                        "order": 25,
                        "userType": "F",
                        "precision": 2
                    },
                    {
                        "name": "CUSREP",
                        "description": "CUSREP",
                        "order": 26,
                        "userType": "F",
                        "precision": 2
                    },
                    {
                        "name": "CUSVAR",
                        "description": "CUSVAR",
                        "order": 27,
                        "userType": "F",
                        "precision": 2
                    },
                    {
                        "name": "COD_FORN",
                        "description": "COD_FORN",
                        "order": 28,
                        "userType": "I"
                    },
                    {
                        "name": "FORNECEDOR",
                        "description": "FORNECEDOR",
                        "order": 29,
                        "userType": "S"
                    },
                    {
                        "name": "COD_LINHA",
                        "description": "COD_LINHA",
                        "order": 30,
                        "userType": "I"
                    },
                    {
                        "name": "LINHA",
                        "description": "LINHA",
                        "order": 31,
                        "userType": "S"
                    },
                    {
                        "name": "CODPRODECOM",
                        "description": "CODPRODECOM",
                        "order": 32,
                        "userType": "I"
                    },
                    {
                        "name": "ENDIMAGEM",
                        "description": "ENDIMAGEM",
                        "order": 33,
                        "userType": "S"
                    }
                ],
                "rows": [
                    [
                        12027,
                        "FE044705",
                        "CH IMPACTO MANUAL 8P          09602 SATA",
                        "PC",
                        "4.01/45 A   PT3/01 D",
                        145.69,
                        "ST09602SJ",
                        "ST09602SJ                     ",
                        "7891645086152",
                        "APEX TOOL ",
                        "ST09602SJ                     ",
                        "JOGOS DE BITS                                     ",
                        1.05000,
                        2,
                        82054000,
                        "2",
                        200,
                        0.0,
                        "ESTOQUE NORMAL",
                        0.0,
                        0.0,
                        0.0,
                        0.0,
                        "SIM",
                        0.0,
                        0.0,
                        0.0,
                        2408,
                        "APEX TOOL GROUP INDUSTRIA E COMERCIO DE FERRAMENTAS LTDA",
                        225,
                        "SATA",
                        1044705,
                        "https://static3.tcdn.com.br/img/img_prod/469103/chave_de_impacto_manual_com_8_pecas_09602_sata_1044705_1_20170509150810.jpg                                                                             "
                    ],
                    [
                        12027,
                        "FE044705",
                        "CH IMPACTO MANUAL 8P          09602 SATA",
                        "PC",
                        "4.01/45 A   PT3/01 D",
                        145.69,
                        "ST09602SJ",
                        "ST09602SJ                     ",
                        "7891645086152",
                        "APEX TOOL ",
                        "ST09602SJ                     ",
                        "JOGOS DE BITS                                     ",
                        1.05000,
                        2,
                        82054000,
                        "2",
                        200,
                        0.0,
                        "ESTOQUE NORMAL",
                        0.0,
                        0.0,
                        0.0,
                        0.0,
                        "SIM",
                        0.0,
                        0.0,
                        0.0,
                        2408,
                        "APEX TOOL GROUP INDUSTRIA E COMERCIO DE FERRAMENTAS LTDA",
                        225,
                        "SATA",
                        1044705,
                        "https://static3.tcdn.com.br/img/img_prod/469103/chave_de_impacto_manual_com_8_pecas_09602_sata_1044705_1_20170509150810.jpg                                                                             "
                    ],
                    [
                        12027,
                        "FE044705",
                        "CH IMPACTO MANUAL 8P          09602 SATA",
                        "PC",
                        "4.01/45 A   PT3/01 D",
                        145.69,
                        "ST09602SJ",
                        "ST09602SJ                     ",
                        "7891645086152",
                        "APEX TOOL ",
                        "ST09602SJ                     ",
                        "JOGOS DE BITS                                     ",
                        1.05000,
                        2,
                        82054000,
                        "2",
                        200,
                        0.0,
                        "ESTOQUE NORMAL",
                        2.0,
                        2.0,
                        2.0,
                        5.0,
                        "SIM",
                        0.0,
                        0.0,
                        0.0,
                        2408,
                        "APEX TOOL GROUP INDUSTRIA E COMERCIO DE FERRAMENTAS LTDA",
                        225,
                        "SATA",
                        1044705,
                        "https://static3.tcdn.com.br/img/img_prod/469103/chave_de_impacto_manual_com_8_pecas_09602_sata_1044705_1_20170509150810.jpg                                                                             "
                    ],
                    [
                        12027,
                        "FE044705",
                        "CH IMPACTO MANUAL 8P          09602 SATA",
                        "PC",
                        "4.01/45 A   PT3/01 D",
                        145.69,
                        "ST09602SJ",
                        "ST09602SJ                     ",
                        "7891645086152",
                        "APEX TOOL ",
                        "ST09602SJ                     ",
                        "JOGOS DE BITS                                     ",
                        1.05000,
                        2,
                        82054000,
                        "2",
                        200,
                        0.0,
                        "ESTOQUE NORMAL",
                        5.0,
                        5.0,
                        2.0,
                        5.0,
                        "SIM",
                        0.0,
                        0.0,
                        0.0,
                        2408,
                        "APEX TOOL GROUP INDUSTRIA E COMERCIO DE FERRAMENTAS LTDA",
                        225,
                        "SATA",
                        1044705,
                        "https://static3.tcdn.com.br/img/img_prod/469103/chave_de_impacto_manual_com_8_pecas_09602_sata_1044705_1_20170509150810.jpg                                                                             "
                    ],
                    [
                        12027,
                        "FE044705",
                        "CH IMPACTO MANUAL 8P          09602 SATA",
                        "PC",
                        "4.01/45 A   PT3/01 D",
                        145.69,
                        "ST09602SJ",
                        "ST09602SJ                     ",
                        "7891645086152",
                        "APEX TOOL ",
                        "ST09602SJ                     ",
                        "JOGOS DE BITS                                     ",
                        1.05000,
                        2,
                        82054000,
                        "2",
                        200,
                        0.0,
                        "ESTOQUE NORMAL",
                        3.0,
                        3.0,
                        3.0,
                        5.0,
                        "SIM",
                        0.0,
                        0.0,
                        0.0,
                        2408,
                        "APEX TOOL GROUP INDUSTRIA E COMERCIO DE FERRAMENTAS LTDA",
                        225,
                        "SATA",
                        1044705,
                        "https://static3.tcdn.com.br/img/img_prod/469103/chave_de_impacto_manual_com_8_pecas_09602_sata_1044705_1_20170509150810.jpg                                                                             "
                    ],
                    [
                        12027,
                        "FE044705",
                        "CH IMPACTO MANUAL 8P          09602 SATA",
                        "PC",
                        "4.01/45 A   PT3/01 D",
                        145.69,
                        "ST09602SJ",
                        "ST09602SJ                     ",
                        "7891645086152",
                        "APEX TOOL ",
                        "ST09602SJ                     ",
                        "JOGOS DE BITS                                     ",
                        1.05000,
                        2,
                        82054000,
                        "2",
                        200,
                        0.0,
                        "ESTOQUE NORMAL",
                        3.0,
                        3.0,
                        3.0,
                        6.0,
                        "SIM",
                        0.0,
                        0.0,
                        0.0,
                        2408,
                        "APEX TOOL GROUP INDUSTRIA E COMERCIO DE FERRAMENTAS LTDA",
                        225,
                        "SATA",
                        1044705,
                        "https://static3.tcdn.com.br/img/img_prod/469103/chave_de_impacto_manual_com_8_pecas_09602_sata_1044705_1_20170509150810.jpg                                                                             "
                    ],
                    [
                        12027,
                        "FE044705",
                        "CH IMPACTO MANUAL 8P          09602 SATA",
                        "PC",
                        "4.01/45 A   PT3/01 D",
                        145.69,
                        "ST09602SJ",
                        "ST09602SJ                     ",
                        "7891645086152",
                        "APEX TOOL ",
                        "ST09602SJ                     ",
                        "JOGOS DE BITS                                     ",
                        1.05000,
                        2,
                        82054000,
                        "2",
                        200,
                        0.0,
                        "ESTOQUE NORMAL",
                        6.0,
                        6.0,
                        3.0,
                        6.0,
                        "SIM",
                        90.46,
                        80.94,
                        74.94,
                        2408,
                        "APEX TOOL GROUP INDUSTRIA E COMERCIO DE FERRAMENTAS LTDA",
                        225,
                        "SATA",
                        1044705,
                        "https://static3.tcdn.com.br/img/img_prod/469103/chave_de_impacto_manual_com_8_pecas_09602_sata_1044705_1_20170509150810.jpg                                                                             "
                    ]
                ],
                "burstLimit": false,
                "timeQuery": "49ms",
                "timeResultSet": "1ms"
            }
        }
    """.trimIndent()