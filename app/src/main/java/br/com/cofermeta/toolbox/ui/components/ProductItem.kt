package br.com.cofermeta.toolbox.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.cofermeta.toolbox.data.*
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest

@Composable
fun ProductItem(
    codigo: String = CODPROD,
    descricao: String = descrprod,
    marca: String = MARCA,
    preco: String = PRECO,
    imagem: String = ENDIMAGEM
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 18.dp, start = 18.dp, end = 18.dp, bottom = 0.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(MaterialTheme.colors.primaryVariant)

    ) {
        Column( modifier = Modifier
            .padding(18.dp)) {
        Row {
            Column {
                Box(modifier = Modifier
                    .padding(end = 16.dp)
                    .size(120.dp, 90.dp)){
                    SubcomposeAsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imagem)
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                    )
                }
            }
            Column {
                Text(
                    text = "#$codigo",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    letterSpacing = 0.sp,
                )
                Text(
                    text = marca,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    letterSpacing = 0.sp,
                )
                Text(
                    text = "R$${preco.replace(".", ",")}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    letterSpacing = 0.sp,
                )
            }

        }
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = descricao,
                fontSize = 16.sp,
                letterSpacing = 0.sp,
            )
        }
    }
}

/*
@Preview
@Composable
fun QueryDetailBoxPreview() {
    Surface(
        modifier = Modifier.size(412.dp,892.dp),
        color = MaterialTheme.colors.background
    ) {
        Box { QueryDetailBox() }
    }
}*/
