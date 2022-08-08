package br.com.cofermeta.toolbox.ui.components

import android.content.Context
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import br.com.cofermeta.toolbox.data.queryUiTitle

@Composable
fun BasicScaffold(context: Context, navController: NavController) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    Scaffold(
        topBar = { BasicTopBar(queryUiTitle) },
        content = { padding -> QueryBodyContent(padding) },
        bottomBar = { BasicBottomBar(context = context) },
/*        snackbarHost = {
            BasicTextField(value = "", onValueChange = )
        }*/
    )
}
