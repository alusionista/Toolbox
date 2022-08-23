package br.com.cofermeta.toolbox.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.cofermeta.toolbox.data.*
import br.com.cofermeta.toolbox.sankhya
import br.com.cofermeta.toolbox.viewmodels.QueryViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun BottomBar(
    navController: NavController,
    scope: CoroutineScope,
    state: ScaffoldState,
    queryViewModel: QueryViewModel = viewModel()
) {
    suspend fun openOrCloseDrawer() = if (state.drawerState.isClosed) state.drawerState.open() else state.drawerState.close()
    val showDialog by queryViewModel.showScanner.observeAsState(false)
    if (showDialog) ScannerDialog()

    BottomAppBar(
        elevation = 1.dp,
        backgroundColor = Color.Transparent,
        contentColor = MaterialTheme.colors.onSurface,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Rounded.FilterList, FILTROS) },
            label = { Text(text = FILTROS) },
            selected = false,
            unselectedContentColor = MaterialTheme.colors.onSurface,
            onClick = { scope.launch { openOrCloseDrawer() } }
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Rounded.QrCodeScanner, SCANNER) },
            label = { Text(text = SCANNER) },
            selected = false,
            unselectedContentColor = MaterialTheme.colors.onSurface,
            onClick = { queryViewModel.onShowScannerChange(true) })

        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.Person, USUARIO) },
            label = { Text(text = sankhya.firstName.ifEmpty { USUARIO }) },
            selected = false,
            unselectedContentColor = MaterialTheme.colors.onSurface,
            onClick = { navController.navigate("login_ui") })

    }

}
