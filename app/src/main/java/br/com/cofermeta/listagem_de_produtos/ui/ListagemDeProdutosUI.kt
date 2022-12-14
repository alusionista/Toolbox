package br.com.cofermeta.listagem_de_produtos.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import br.com.cofermeta.listagem_de_produtos.data.LISTAGEM_DE_PRODUTOS
import br.com.cofermeta.listagem_de_produtos.ui.components.*
import kotlinx.coroutines.CoroutineScope

@Composable
fun ListagemDeProdutosUI(
    navController: NavController,
) {
    val state = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = state,
        topBar = { TopBar(LISTAGEM_DE_PRODUTOS) },
        drawerContent = { Drawer(scope, state) },
        drawerBackgroundColor = MaterialTheme.colors.primaryVariant,
        drawerContentColor = MaterialTheme.colors.onSurface,
        content = { padding -> BodyContent(padding) },
        bottomBar = { BottomBar(navController, scope, state) },
    )
}