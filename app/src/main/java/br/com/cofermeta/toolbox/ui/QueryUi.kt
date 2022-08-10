package br.com.cofermeta.toolbox.ui

import android.content.Context
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.cofermeta.toolbox.data.queryUiTitle
import br.com.cofermeta.toolbox.model.dataclasses.sankhya
import br.com.cofermeta.toolbox.ui.components.*
import br.com.cofermeta.toolbox.viewmodels.QueryViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun QueryScreen(
    context: Context,
    navController: NavController,
    queryViewModel: QueryViewModel = viewModel()
) {
    val state = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val codemp by queryViewModel.codemp.observeAsState(sankhya.codemp)
    val codprod by queryViewModel.codprod.observeAsState("")
    val marca by queryViewModel.marca.observeAsState("")
    val locprin by queryViewModel.locprin.observeAsState("")
    val descrprod by queryViewModel.descrprod.observeAsState("")

ScaffoldQueryUi(
    context = context,
    navController = navController,
    queryViewModel = queryViewModel,
    state = state,
    scope = scope,
    codemp = codemp,
    codprod = codprod,
    marca = marca,
    locprin = locprin,
    descrprod = descrprod
)
}

@Composable
fun ScaffoldQueryUi(
    context: Context,
    navController: NavController,
    state: ScaffoldState,
    scope: CoroutineScope,
    codemp: String,
    codprod: String,
    marca: String,
    locprin: String,
    descrprod: String,
    queryViewModel: QueryViewModel
) {

    Scaffold(
        scaffoldState = state,
        topBar = { TopBar(queryUiTitle) },
        drawerContent = {
            QueryDrawer(
                context = context,
                scope = scope,
                state = state,
                queryViewModel = queryViewModel,
                codemp = codemp,
                codprod = codprod,
                marca = marca,
                locprin = locprin,
                descrprod = descrprod,
            )
        },
        drawerBackgroundColor = MaterialTheme.colors.primaryVariant,
        drawerContentColor = MaterialTheme.colors.onSurface,
        content = { padding ->
            BodyContent(padding, navController, queryViewModel)
        },
        bottomBar = { BottomBar(context, navController, scope, state) },
    )
}