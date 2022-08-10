package br.com.cofermeta.toolbox.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.cofermeta.toolbox.data.imgPlaceHolder
import br.com.cofermeta.toolbox.viewmodels.QueryViewModel
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest

@Composable
fun ProductItem(
    codprod: String,
    marca: String,
    vlrvenda: String,
    descrprod: String,
    endimagem: String,
    index: Int,
    navController: NavController,
    queryViewModel: QueryViewModel = viewModel()
) {
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
                queryViewModel.onSelectItem(index)
                Thread.sleep(1000)
                navController.navigate("product_detail_ui")
            }

    ) {
        Column(modifier = Modifier
            .padding(18.dp)) {
            ConstraintLayout(constraints) {
                val endimagem_ = if (endimagem.contains("http")) endimagem else imgPlaceHolder
                val marca_ = if (vlrvenda.contains("null")) "Sem marca" else marca.replace("\"", "").trim()
                val vlrvenda_ = if (vlrvenda.contains("null")) "Sem preço" else "R$${vlrvenda.replace(".", ",")}"

                Box(modifier = Modifier
                    .padding(end = 16.dp)
                    .size(120.dp, 90.dp)
                    .layoutId("detailimage")
                ) {
                        SubcomposeAsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(endimagem_.replace("\"", "").trim())
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
                        text = "#$codprod",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        letterSpacing = 0.sp,
                    )
                    Text(
                        text = marca_,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colors.secondaryVariant,
                        letterSpacing = 0.sp,
                    )
                    Text(
                        text = vlrvenda_,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        letterSpacing = 0.sp,
                    )
                }
            }
            Text(
                modifier = Modifier
                    .padding(top = 10.dp),
                text = descrprod.replace("\"", "").trim(),
                fontSize = 16.sp,
                letterSpacing = 0.sp,
            )
        }
    }
}
