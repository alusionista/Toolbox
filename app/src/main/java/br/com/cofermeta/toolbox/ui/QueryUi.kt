package br.com.cofermeta.toolbox.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import br.com.cofermeta.toolbox.data.queryUiTitle
import br.com.cofermeta.toolbox.ui.components.BodyContent
import br.com.cofermeta.toolbox.ui.components.BottomBar
import br.com.cofermeta.toolbox.ui.components.Drawer
import br.com.cofermeta.toolbox.ui.components.TopBar
import kotlinx.coroutines.CoroutineScope

@Composable
fun QueryScreen(
    navController: NavController,
) {
    val state = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    ScaffoldQueryUi(
        navController = navController,
        state = state,
        scope = scope,
    )
}

@Composable
fun ScaffoldQueryUi(
    navController: NavController,
    state: ScaffoldState,
    scope: CoroutineScope,
) {

    Scaffold(
        scaffoldState = state,
        topBar = { TopBar(queryUiTitle) },
        drawerContent = { Drawer(scope, state) },
        drawerBackgroundColor = MaterialTheme.colors.primaryVariant,
        drawerContentColor = MaterialTheme.colors.onSurface,
        content = { padding -> BodyContent(padding) },
        bottomBar = { BottomBar(navController, scope, state) },
    )
}