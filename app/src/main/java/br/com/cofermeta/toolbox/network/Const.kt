package br.com.cofermeta.toolbox.network

const val base = "http://teste.cofermeta.com.br"
const val port = 8280
const val defaultUser = "integracao"
const val defaultPasword = "654321"

const val loginService = "MobileLoginSP.login"
const val logoutService = "MobileLoginSP.logout"
const val loadRecords = "CRUDServiceProvider.loadRecords"
const val executeQuery = "DbExplorerSP.executeQuery"

const val threadSleep: Long = 50

const val connectionErrorMessage = "Não foi possível conectar-se.\nVerifique sua conexão Wi-fi."