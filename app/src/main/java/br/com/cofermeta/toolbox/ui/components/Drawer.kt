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
import br.com.cofermeta.toolbox.data.*
import br.com.cofermeta.toolbox.sankhya
import br.com.cofermeta.toolbox.ui.theme.white50p
import br.com.cofermeta.toolbox.viewmodels.ListagemDeProdutosViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun Drawer(
    scope: CoroutineScope,
    state: ScaffoldState,
    listagemDeProdutosViewModel: ListagemDeProdutosViewModel = viewModel(),
) {
    val context = LocalContext.current
    val codemp by listagemDeProdutosViewModel.codemp.observeAsState(sankhya.codemp)
    val codprod by listagemDeProdutosViewModel.codprod.observeAsState("")
    val marca by listagemDeProdutosViewModel.marca.observeAsState("")
    val locprin by listagemDeProdutosViewModel.locprin.observeAsState("")
    val descrprod by listagemDeProdutosViewModel.descrprod.observeAsState("")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(DEFAULT_PADDING)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = COFERMETA_TOOLBOX,
            textAlign = TextAlign.Left,
            fontSize = 20.sp,
            lineHeight = 38.sp,
            fontWeight = FontWeight.Bold,
            color = white50p,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = LISTAGEM_DE_PRODUTOS,
            textAlign = TextAlign.Left,
            fontSize = 30.sp,
            lineHeight = 38.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(40.dp))

        TextFieldOutlined(COD_PRODUTO, codprod, true) { listagemDeProdutosViewModel.onCodigoChange(it) }

        TextFieldOutlined(EMPRESA, codemp, true) { listagemDeProdutosViewModel.onEmpresaChange(it) }

        TextFieldOutlined(MARCA, marca) { listagemDeProdutosViewModel.onMarcaChange(it) }

        TextFieldOutlined(LOCALIZACAO, locprin) { listagemDeProdutosViewModel.onLocalChange(it) }

        TextFieldOutlined(DESCRICAO, descrprod) { listagemDeProdutosViewModel.onDescricaoChange(it) }

        Spacer(modifier = Modifier.height(20.dp))

        SimpleButton(label = BUSCAR, onClick = {
            scope.launch {
                listagemDeProdutosViewModel.onLoadingChange(true)
                state.drawerState.close()
                listagemDeProdutosViewModel.productQuery(context)
            }
        }
        )
    }
}
