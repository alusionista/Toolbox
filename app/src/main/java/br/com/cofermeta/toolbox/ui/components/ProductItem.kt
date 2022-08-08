package br.com.cofermeta.toolbox.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.cofermeta.toolbox.data.CODPROD
import br.com.cofermeta.toolbox.data.ENDIMAGEM
import br.com.cofermeta.toolbox.data.PRODUTO
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest

@Composable
fun QueryDetailBox(
    codigo: String = CODPROD,
    descricao: String = PRODUTO,
    imagem: String = ENDIMAGEM
) {
    Box(
        modifier = Modifier
            .height(130.dp)
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(15.dp))
    ) {
        Row(Modifier.fillMaxSize()) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(ENDIMAGEM)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(16.dp)
                    .weight(0.3f)
            )
            Column(Modifier.fillMaxHeight().weight(0.7f)) {
                Text(
                    text = "Cód. $codigo",
                    fontSize = 22.sp,
                    color = Color.White
                )
                Text(
                    text = descricao,
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
fun QueryDetailBoxPreview() {
    Surface(
        modifier = Modifier.size(412.dp,892.dp),
        color = MaterialTheme.colors.background
    ) {
        Box { QueryDetailBox() }
    }
}