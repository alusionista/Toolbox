package br.com.cofermeta.listagem_de_produtos.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.cofermeta.listagem_de_produtos.sankhya

@Composable
fun ListagemDeProdutosNavHost () {
    val navController = rememberNavController()
    val startDestination = if (sankhya.user.isNotEmpty()) "query_ui" else "login_ui"
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("login_ui") { LoginUI(navController) }
        composable("query_ui") { ListagemDeProdutosUI(navController) }
    }

}