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
ScaffoldQueryUi(context = context, navController = navController)
}
