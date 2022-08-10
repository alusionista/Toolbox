package br.com.cofermeta.toolbox.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.cofermeta.toolbox.data.noProductSelected
import br.com.cofermeta.toolbox.viewmodels.QueryViewModel

@Composable
fun BodyContent(
    padding: PaddingValues,
    navController: NavController,
    queryViewModel: QueryViewModel
) {
    val hasResult by queryViewModel.hasResult.observeAsState(false)

    if (hasResult) {
        val numberOfHeaders = queryViewModel.queryResult.numberOfHeaders
        val fieldsMetadata = queryViewModel.queryResult.fieldsMetadata
        val numberOfRows =
            if (queryViewModel.queryResult.numberOfRows < 100) queryViewModel.queryResult.numberOfRows else 100
        val rows = queryViewModel.queryResult.rows


        Column(
            modifier = Modifier
                .padding(paddingValues = padding)
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
        ) {
            if (numberOfRows > 1){
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 22.dp, vertical = 10.dp),
                    text = "Mostrando $numberOfRows de ${queryViewModel.queryResult.numberOfRows} resultados",
                    color = MaterialTheme.colors.secondaryVariant,
                    textAlign = TextAlign.Center
                )
            }
            for (i in 0 until numberOfRows) {
                val codprod = rows?.asJsonArray?.get(i)?.asJsonArray?.get(0).toString()
                val marca = rows?.asJsonArray?.get(i)?.asJsonArray?.get(1).toString()
                val vlrvenda = rows?.asJsonArray?.get(i)?.asJsonArray?.get(2).toString()
                val descrprod = rows?.asJsonArray?.get(i)?.asJsonArray?.get(3).toString()
                val endimagem = rows?.asJsonArray?.get(i)?.asJsonArray?.get(4).toString()
                Row {
                    ProductItem(
                        codprod = codprod,
                        marca = marca,
                        vlrvenda = vlrvenda,
                        descrprod = descrprod,
                        endimagem = endimagem,
                        index = i,
                        navController = navController
                    )
                }
            }
        }
    } else {
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
}


