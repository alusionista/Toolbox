package br.com.cofermeta.toolbox.ui

import android.content.Context
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.NavController
import br.com.cofermeta.toolbox.data.queryUiTitle
import br.com.cofermeta.toolbox.ui.components.*

@Composable
fun QueryScreen(
    context: Context,
    navController: NavController
) {
    val state = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = state,
        topBar = { TopBar(queryUiTitle) },
        drawerContent = { QueryDrawer(context, scope, state) },
        drawerBackgroundColor = MaterialTheme.colors.primaryVariant,
        drawerContentColor = MaterialTheme.colors.onSurface,
        content = { padding -> BodyContent(context, padding) },
        bottomBar = { BottomBar(context, navController, scope, state)},
        )
}
