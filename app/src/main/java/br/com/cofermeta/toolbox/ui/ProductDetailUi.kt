package br.com.cofermeta.toolbox.ui

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.cofermeta.toolbox.data.defaultPadding
import br.com.cofermeta.toolbox.ui.components.BottomBar
import br.com.cofermeta.toolbox.ui.components.TopBar
import br.com.cofermeta.toolbox.viewmodels.QueryViewModel
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest

@Composable
fun ProductDetailScreen(
    context: Context,
    navController: NavController,
    queryViewModel: QueryViewModel = viewModel()
) {
    val rows = queryViewModel.queryResult.rows
    val selectedItem = queryViewModel.selectedItem.value
    val codprod = selectedItem?.let { rows?.asJsonArray?.get(it)?.asJsonArray?.get(0).toString() }
    val marca = selectedItem?.let { rows?.asJsonArray?.get(it)?.asJsonArray?.get(1).toString() }
    val vlrvenda = selectedItem?.let { rows?.asJsonArray?.get(it)?.asJsonArray?.get(2).toString() }
    val descrprod = selectedItem?.let { rows?.asJsonArray?.get(it)?.asJsonArray?.get(3).toString() }
    val endimagem = selectedItem?.let { rows?.asJsonArray?.get(it)?.asJsonArray?.get(4).toString() }

    TopBar("#$codprod") { navController.popBackStack() }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (marca != null && vlrvenda != null && descrprod != null) {
            ProductDetailWrapper(marca, vlrvenda, descrprod)
        }
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier.fillMaxSize()
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(endimagem)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(240.dp, 170.dp)
                    .offset(y = 70.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
        }
    }
    //BottomBar(context, navController, scope, state)
}

    @Composable
    fun ProductDetailWrapper(
        marca: String,
        vlrvenda: String,
        descrprod: String,
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = defaultPadding + 240.dp / 2f,
                    start = defaultPadding,
                    end = defaultPadding,
                    bottom = defaultPadding
                )
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colors.primaryVariant)
            //.align(Alignment.BottomCenter)
        ) {
            Column(modifier = Modifier.padding(top = 240.dp / 2f)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .align(Alignment.Start)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        ProductDetailDataItem(header = "Marca", row = marca)
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        ProductDetailDataItem(header = "Preço", row = vlrvenda)
                    }
                }
                Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                    ProductDetailDescriptionItem(header = "Descrição", row = descrprod)
                }
            }
        }
    }

    @Composable
    fun ProductDetailDataItem(header: String, row: String) {
        Column {
            Text(
                text = header,
                fontSize = 14.sp,
                color = MaterialTheme.colors.onSurface
            )
            Text(
                text = row,
                fontSize = 22.sp,
                color = MaterialTheme.colors.onSurface
            )
        }
    }

    @Composable
    fun ProductDetailDescriptionItem(header: String, row: String) {
        Column {
            Text(
                text = header,
                fontSize = 14.sp,
                color = MaterialTheme.colors.onSurface
            )
            Text(
                text = row,
                fontSize = 24.sp,
                color = MaterialTheme.colors.onSurface
            )
        }
    }