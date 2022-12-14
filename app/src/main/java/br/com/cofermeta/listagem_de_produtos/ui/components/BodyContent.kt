package br.com.cofermeta.listagem_de_produtos.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.cofermeta.listagem_de_produtos.data.NO_PRODUCT_SELECTED
import br.com.cofermeta.listagem_de_produtos.data.ON_LOADING_MESSAGE
import br.com.cofermeta.listagem_de_produtos.viewmodels.ListagemDeProdutosViewModel

@Composable
fun BodyContent(
    padding: PaddingValues,
    listagemDeProdutosViewModel: ListagemDeProdutosViewModel = viewModel()
) {
    val hasResult by listagemDeProdutosViewModel.hasResult.observeAsState(false)
    val loading by listagemDeProdutosViewModel.loading.observeAsState(true)

    if (hasResult) {
        val queryResult = listagemDeProdutosViewModel.queryResult
        val numberOfRows = if (queryResult.numberOfRows < 100) queryResult.numberOfRows else 100
        Column(
            modifier = Modifier
                .padding(paddingValues = padding)
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
        ) {
            if (numberOfRows > 1) {
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 22.dp, vertical = 10.dp),
                    text = "Mostrando $numberOfRows de ${queryResult.numberOfRows} resultados",
                    color = MaterialTheme.colors.secondaryVariant,
                    textAlign = TextAlign.Center
                )
            }
            ProductItem(queryResult)
        }
    } else if (!loading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = NO_PRODUCT_SELECTED,
                color = MaterialTheme.colors.secondaryVariant,
                textAlign = TextAlign.Center
            )
        }
    }
    if (loading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = ON_LOADING_MESSAGE,
                textAlign = TextAlign.Center
            )
            LinearProgressIndicator()
        }
    }

}


