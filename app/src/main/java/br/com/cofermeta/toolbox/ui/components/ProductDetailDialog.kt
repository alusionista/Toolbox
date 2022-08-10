package br.com.cofermeta.toolbox.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import br.com.cofermeta.toolbox.data.defaultPadding
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest

@Composable
fun ProductDetailDialog(
    marca: String,
    vlrvenda: String,
    descrprod: String,
    endimagem: String,
) {
    Column {
        Spacer(modifier = Modifier.height(defaultPadding))
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(endimagem)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                //.size(240.dp, 180.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp))
        )
        Spacer(modifier = Modifier.height(defaultPadding))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                ProductDetailDataItem(header = "Marca", row = marca)
            }
            Column(modifier = Modifier.weight(1f)) {
                ProductDetailDataItem(header = "Preço", row = vlrvenda)
            }
        }
        ProductDetailDescriptionItem(header = "Descrição", row = descrprod)
        ProductDetailDescriptionItem(header = "Descrição", row = descrprod)
        ProductDetailDescriptionItem(header = "Descrição", row = descrprod)
        ProductDetailDescriptionItem(header = "Descrição", row = descrprod)
        ProductDetailDescriptionItem(header = "Descrição", row = descrprod)
        ProductDetailDescriptionItem(header = "Descrição", row = descrprod)
        ProductDetailDescriptionItem(header = "Descrição", row = descrprod)
    }
}
