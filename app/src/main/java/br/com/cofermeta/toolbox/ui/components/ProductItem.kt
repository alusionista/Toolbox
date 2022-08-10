package br.com.cofermeta.toolbox.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.cofermeta.toolbox.data.defaultPadding
import br.com.cofermeta.toolbox.data.imgPlaceHolder
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest

@Composable
fun ProductItem(
    codprod: String,
    marca: String,
    vlrvenda: String,
    descrprod: String,
    endimagem: String,
) {
    val newCodprod = "#$codprod"
    val newEndimagem = if (endimagem.contains("http")) endimagem.replace("\"", "").trim() else imgPlaceHolder
    val newMarca = if (vlrvenda.contains("null")) "Sem marca" else marca.replace("\"", "").trim()
    val newVlrvenda = if (vlrvenda.contains("null")) "Sem preço" else "R$${vlrvenda.replace(".", ",")}"
    val newDescrprod = descrprod.replace("\"", "").trim()

    val showDialog =  remember { mutableStateOf(false) }

    if(showDialog.value)
        ProductDialog(
            endimagem = newEndimagem,
            codprod = newCodprod,
            marca = newMarca,
            vlrvenda = newVlrvenda,
            descrprod = newDescrprod,
            onShowDialog = {
                showDialog.value = it
            })

    val constraints = ConstraintSet {
        val detailColumn = createRefFor("detailcolumn")
        val detailImage = createRefFor("detailimage")

        constrain(detailImage) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)
        }

        constrain(detailColumn) {
            start.linkTo(detailImage.end)
            top.linkTo(detailImage.top)
            bottom.linkTo(detailImage.bottom)
            height = Dimension.fillToConstraints
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 18.dp, start = 18.dp, end = 18.dp, bottom = 0.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(MaterialTheme.colors.primaryVariant)
            .clickable {
                showDialog.value = true
            }
    ) {
        Column(modifier = Modifier
            .padding(defaultPadding)
        ) {
            ConstraintLayout(constraints) {
                Box(modifier = Modifier
                    .padding(end = 16.dp)
                    .size(120.dp, 90.dp)
                    .layoutId("detailimage")
                ) {
                        SubcomposeAsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(newEndimagem)
                                .crossfade(true)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                        )
                }
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .layoutId("detailcolumn")
                ) {
                    Text(
                        text = newCodprod,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        letterSpacing = 0.sp,
                    )
                    Text(
                        text = newMarca,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colors.secondaryVariant,
                        letterSpacing = 0.sp,
                    )
                    Text(
                        text = newVlrvenda,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        letterSpacing = 0.sp,
                    )
                }
            }
            Text(
                modifier = Modifier.padding(top = 12.dp),
                text = newDescrprod,
                fontSize = 16.sp,
                letterSpacing = 0.sp,
            )
        }
    }
}
@Composable
fun MyDialog() {
    var showDialog by remember { mutableStateOf(false) }
    Text("Click me",
        Modifier.clickable { showDialog = true }
    )
    if(showDialog) {
        Dialog({ showDialog = false }) {
            Text("I am the content of the dialog")
        }
    }
}
