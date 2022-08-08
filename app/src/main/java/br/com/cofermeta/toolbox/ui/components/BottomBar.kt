package br.com.cofermeta.toolbox.ui.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.cofermeta.toolbox.data.*
import br.com.cofermeta.toolbox.model.dataclasses.sankhya
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun BottomBar(
    context: Context,
    navController: NavController,
    scope: CoroutineScope,
    state: ScaffoldState
) {
    val selectedIndex = remember { mutableStateOf(0) }

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
            selected = (selectedIndex.value == 1),
            onClick = {
                scope.launch { if(state.drawerState.isClosed) state.drawerState.open() else state.drawerState.close() }
            },
            unselectedContentColor = MaterialTheme.colors.onSurface
        )
        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Rounded.QrCodeScanner, SCANNER)
        },
            label = { Text(text = SCANNER) },
            selected = (selectedIndex.value == 2),
            onClick = {
                Toast.makeText(context, SCANNER, Toast.LENGTH_SHORT).show()
                //scope.launch { if(it.isClosed) it.open() else it.close() }
            })
/*        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Person,CONSULTA)
        },
            label = { Text(text = CONSULTA) },
            selected = (selectedIndex.value == 3),
            onClick = {
                Toast.makeText(context, CONSULTA, Toast.LENGTH_SHORT).show()
            })*/
        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Person, USUARIO)
        },
            label = {
                Text(text = sankhya.firstName.ifEmpty { USUARIO })
                //Text(text = sankhya.firstName)
                    },
            selected = (selectedIndex.value == 4),
            onClick = {
            navController.navigate("login_ui")
            })

    }

}
