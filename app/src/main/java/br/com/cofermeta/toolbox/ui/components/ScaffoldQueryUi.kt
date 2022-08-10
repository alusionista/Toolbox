package br.com.cofermeta.toolbox.ui.components

import android.content.Context
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.cofermeta.toolbox.data.queryUiTitle
import br.com.cofermeta.toolbox.model.dataclasses.sankhya
import br.com.cofermeta.toolbox.viewmodels.QueryViewModel

@Composable
fun ScaffoldQueryUi(
    context: Context,
    navController: NavController,
    queryViewModel: QueryViewModel = viewModel()
) {
    val state = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val empresa by queryViewModel.empresa.observeAsState(sankhya.codemp)
    val codigo by queryViewModel.codigo.observeAsState("")
    val marca by queryViewModel.marca.observeAsState("")
    val local by queryViewModel.local.observeAsState("")
    val descricao by queryViewModel.descricao.observeAsState("")

    Scaffold(
        scaffoldState = state,
        topBar = { TopBar(queryUiTitle) },
        drawerContent = {
            QueryDrawer(
                context = context,
                scope = scope,
                state = state,
                queryViewModel = queryViewModel,
                empresa = empresa,
                codigo = codigo,
                marca = marca,
                local = local,
                descricao = descricao,
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