package br.com.cofermeta.toolbox.ui.components

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
import br.com.cofermeta.toolbox.data.noProductSelected
import br.com.cofermeta.toolbox.data.onLoadingText
import br.com.cofermeta.toolbox.ui.ItemUI
import br.com.cofermeta.toolbox.viewmodels.QueryViewModel

@Composable
fun BodyContent(
    padding: PaddingValues,
    queryViewModel: QueryViewModel = viewModel()
) {
    val hasResult by queryViewModel.hasResult.observeAsState(false)
    val loading by queryViewModel.loading.observeAsState(true)

    if (hasResult) {

        val queryResult = queryViewModel.queryResult
        val numberOfRows =
            if (queryResult.numberOfRows < 100) queryResult.numberOfRows else 100

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
            ItemUI().ProductItem(queryResult)
        }
    } else if (!loading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = noProductSelected,
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
                text = onLoadingText,
                textAlign = TextAlign.Center
            )

            LinearProgressIndicator()
        }
    }

}


