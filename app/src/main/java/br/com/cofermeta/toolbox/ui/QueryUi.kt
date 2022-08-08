package br.com.cofermeta.toolbox.ui

import android.content.Context
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavController
import br.com.cofermeta.toolbox.ui.components.BasicScaffold
import br.com.cofermeta.toolbox.ui.components.QueryDrawer

@Composable
fun QueryScreen(
    context: Context,
    navController: NavController
) {

    var query by rememberSaveable { mutableStateOf("") }
    var hasResult by rememberSaveable { mutableStateOf(false) }
    BasicScaffold(context, navController)
    QueryDrawer(context)
}

