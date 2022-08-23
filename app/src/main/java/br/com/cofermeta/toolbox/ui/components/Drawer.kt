package br.com.cofermeta.toolbox.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.cofermeta.toolbox.data.DEFAULT_PADDING
import br.com.cofermeta.toolbox.sankhya
import br.com.cofermeta.toolbox.ui.theme.white50p
import br.com.cofermeta.toolbox.viewmodels.QueryViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun Drawer(
    scope: CoroutineScope,
    state: ScaffoldState,
    queryViewModel: QueryViewModel = viewModel(),
) {
    val context = LocalContext.current
    val codemp by queryViewModel.codemp.observeAsState(sankhya.codemp)
    val codprod by queryViewModel.codprod.observeAsState("")
    val marca by queryViewModel.marca.observeAsState("")
    val locprin by queryViewModel.locprin.observeAsState("")
    val descrprod by queryViewModel.descrprod.observeAsState("")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(DEFAULT_PADDING)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Cofermeta Toolbox",
            textAlign = TextAlign.Left,
            fontSize = 20.sp,
            lineHeight = 38.sp,
            fontWeight = FontWeight.Bold,
            color = white50p,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Listagem de Produtos",
            textAlign = TextAlign.Left,
            fontSize = 30.sp,
            lineHeight = 38.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(40.dp))

        TextFieldOutlined("Código do produto", codprod, true) { queryViewModel.onCodigoChange(it) }

        TextFieldOutlined("Empresa", codemp, true) { queryViewModel.onEmpresaChange(it) }

        TextFieldOutlined("Marca", marca) { queryViewModel.onMarcaChange(it) }

        TextFieldOutlined("Localização", locprin) { queryViewModel.onLocalChange(it) }

        TextFieldOutlined("Descrição", descrprod) { queryViewModel.onDescricaoChange(it) }

        Spacer(modifier = Modifier.height(20.dp))

        SimpleButton(label = "Buscar", onClick = {
            scope.launch {
                queryViewModel.onLoadingChange(true)
                state.drawerState.close()
                queryViewModel.productQuery(context)
            }
        }
        )
    }
}
