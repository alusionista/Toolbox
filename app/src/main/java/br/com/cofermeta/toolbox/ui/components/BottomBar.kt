package br.com.cofermeta.toolbox.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.cofermeta.toolbox.data.*
import br.com.cofermeta.toolbox.sankhya
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun BottomBar(
    navController: NavController,
    scope: CoroutineScope,
    state: ScaffoldState
) {

    val showDialog = remember { mutableStateOf(false) }

    if (showDialog.value) ScannerDialog( onShowDialog = { showDialog.value = it })

    BottomAppBar(
        elevation = 0.dp,
        backgroundColor = Color.Transparent,
        contentColor = MaterialTheme.colors.onSurface,
        modifier = Modifier.fillMaxWidth()
    ) {
        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Rounded.FilterList, FILTROS)
        },
            label = { Text(text = FILTROS) },
            selected = false,
            unselectedContentColor = MaterialTheme.colors.onSurface,
            onClick = {
                scope.launch { if(state.drawerState.isClosed) state.drawerState.open() else state.drawerState.close() }
            }
        )
        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Rounded.QrCodeScanner, SCANNER)
        },
            label = { Text(text = SCANNER) },
            selected = false,
            unselectedContentColor = MaterialTheme.colors.onSurface,
                    onClick = {
                        showDialog.value = true
            })
        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Person, USUARIO)
        },
            label = {
                Text(text = sankhya.firstName.ifEmpty { USUARIO })
                    },
            selected = false,
            unselectedContentColor = MaterialTheme.colors.onSurface,
            onClick = {
            navController.navigate("login_ui")
            })

    }

}
