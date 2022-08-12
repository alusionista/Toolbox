package br.com.cofermeta.toolbox.ui.components

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.cofermeta.toolbox.data.defaultPadding
import br.com.cofermeta.toolbox.data.threadSleep
import br.com.cofermeta.toolbox.model.dataclasses.sankhya
import br.com.cofermeta.toolbox.ui.theme.white50p
import br.com.cofermeta.toolbox.viewmodels.QueryViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun QueryDrawer(
    context: Context,
    scope: CoroutineScope,
    state: ScaffoldState,
    queryViewModel: QueryViewModel,
    codemp: String,
    codprod: String,
    marca: String,
    locprin: String,
    descrprod: String,
) {

    DrawerUi(
        context = context,
        scope = scope,
        state = state,
        empresa = codemp,
        codigo = codprod,
        marca = marca,
        localizacao = locprin,
        descricao = descrprod,
        queryViewModel = queryViewModel,
        onEmpresaChange = { queryViewModel.onEmpresaChange(it) },
        onCodigoChange = { queryViewModel.onCodigoChange(it) },
        onMarcaChange = { queryViewModel.onMarcaChange(it) },
        onLocalChange = { queryViewModel.onLocalChange(it) },
        onDescricaoChange = { queryViewModel.onDescricaoChange(it) },
    )
}

@Composable
fun DrawerUi(
    context: Context,
    scope: CoroutineScope,
    state: ScaffoldState,
    empresa: String,
    codigo: String,
    marca: String,
    localizacao: String,
    descricao: String,
    queryViewModel: QueryViewModel,
    onEmpresaChange: (String) -> Unit,
    onCodigoChange: (String) -> Unit,
    onMarcaChange: (String) -> Unit,
    onLocalChange: (String) -> Unit,
    onDescricaoChange: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(defaultPadding)
            //.verticalScroll(rememberScrollState())

    ) {
        Text(
            text = "Cofermeta Toolbox",
            textAlign = TextAlign.Left,
            fontSize = 20.sp,
            lineHeight = 38.sp,
            fontWeight = FontWeight.Bold,
            color = white50p,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = "Listagem de Produtos",
            textAlign = TextAlign.Left,
            fontSize = 30.sp,
            lineHeight = 38.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(40.dp))

        DrawerTextField("Código do produto", codigo, onCodigoChange)
        DrawerTextField("Empresa", empresa, onEmpresaChange)
        DrawerTextField("Marca", marca, onMarcaChange)
        DrawerTextField("Localização", localizacao, onLocalChange)
        DrawerTextField("Descrição", descricao, onDescricaoChange)

        Spacer(modifier = Modifier.height(20.dp))

        SimpleButton(label = "Buscar", onClick = {
            queryViewModel.productQuery(
                context = context,
                scope = scope,
                state = state,
                empresa = empresa,
                codigo = codigo,
                marca = marca,
                localizacao = localizacao,
                descricao = descricao,
            )
        }
        )

    }
}
