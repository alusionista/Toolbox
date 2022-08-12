package br.com.cofermeta.toolbox.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import br.com.cofermeta.toolbox.model.DataFormating
import br.com.cofermeta.toolbox.data.defaultPadding
import br.com.cofermeta.toolbox.model.dataclasses.QueryResult
import br.com.cofermeta.toolbox.ui.components.DetailData
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.google.gson.JsonElement

class Item: DataFormating() {
    @Composable
    fun ProductItem(
        queryResult: QueryResult,
    ) {
        val rows = queryResult.rows
        val numberOfRows = queryResult.numberOfRows

        for (i in 0 until numberOfRows) {
            val showDialog = remember { mutableStateOf(false) }
            if (showDialog.value)
                ProductDialog(i, rows) { showDialog.value = it }

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
                Column(
                    modifier = Modifier
                        .padding(defaultPadding)
                ) {
                    ConstraintLayout(constraints) {
                        Box(
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .size(120.dp, 90.dp)
                                .layoutId("detailimage")
                        ) {
                            SubcomposeAsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(getEndimagem(i, rows))
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
                            modifier = Modifier.layoutId("detailcolumn")
                        ) {
                            Text(
                                text = getCodprod(i, rows),
                                fontSize = 22.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White,
                                letterSpacing = 0.sp,
                            )
                            Text(
                                text = getMarca(i, rows),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colors.secondaryVariant,
                                letterSpacing = 0.sp,
                            )
                            Text(
                                text = getVlrvenda(i, rows),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White,
                                letterSpacing = 0.sp,
                            )
                        }
                    }
                    Text(
                        modifier = Modifier.padding(top = 12.dp),
                        text = getDescrprod(i, rows),
                        fontSize = 16.sp,
                        letterSpacing = 0.sp,
                    )
                }
            }
        }
    }

    @Composable
    fun ProductDialog(
        i: Int,
        rows: JsonElement?,
        onShowDialog: (Boolean) -> Unit
    ) {

        Dialog(onDismissRequest = { onShowDialog(false) }) {
            Surface(
                modifier = Modifier.defaultMinSize(minHeight = 450.dp),
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colors.primaryVariant
            ) {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())

                ) {
                    Spacer(modifier = Modifier.height(defaultPadding))
                    Box(Modifier.height(32.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            ProvideTextStyle(value = MaterialTheme.typography.h5) {
                                CompositionLocalProvider(
                                    LocalContentAlpha provides ContentAlpha.high,
                                ) {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center,
                                        maxLines = 1,
                                        fontWeight = FontWeight.ExtraBold,
                                        text = getCodprod(i, rows),
                                        color = Color.White,
                                    )
                                }
                            }
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(end = 10.dp),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            CompositionLocalProvider(
                                LocalContentAlpha provides ContentAlpha.high,
                            ) {
                                IconButton(
                                    onClick = { onShowDialog(false) },
                                    enabled = true,
                                ) {
                                    Icon(
                                        imageVector = Icons.Rounded.Cancel,
                                        contentDescription = "voltar",
                                        tint = Color.White,
                                        modifier = Modifier
                                            .width(30.dp)
                                            .height(30.dp)
                                    )
                                }
                            }
                        }
                    }
                        Column (
                            modifier = Modifier.padding(defaultPadding)
                        ) {
                            SubcomposeAsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(getEndimagem(i, rows))
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
                                    DetailData(header = "Marca", row = getMarca(i, rows))
                                }
                                Column(modifier = Modifier.weight(1f)) {
                                    DetailData(header = "Preço", row = getVlrvenda(i, rows))
                                }
                            }

                            Spacer(modifier = Modifier.height(defaultPadding))

                            DetailData(header = "Descrição", row = getDescrprod(i, rows))

                            Spacer(modifier = Modifier.height(defaultPadding))

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.Start)
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    DetailData(header = "Custo Reposição", row = getCusrep(i, rows))
                                }
                                Column(modifier = Modifier.weight(1f)) {
                                    DetailData(header = "Custo Gerencial", row = getCusger(i, rows))
                                }
                                Column(modifier = Modifier.weight(1f)) {
                                    DetailData(header = "Custo Variável", row = getCusvariavel(i, rows))
                                }
                            }
                            Spacer(modifier = Modifier.height(defaultPadding))

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.Start)
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    DetailData(header = "Estoque mínimo", row = getEstmin(i, rows))
                                }
                                Column(modifier = Modifier.weight(1f)) {
                                    DetailData(header = "Estoque máximo", row = getEstmax(i, rows))
                                }
                            }
                            Spacer(modifier = Modifier.height(defaultPadding))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.Start)
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    DetailData(header = "Estoque", row = getEstoque(i, rows))
                                }
                                Column(modifier = Modifier.weight(1f)) {
                                    DetailData(header = "Disponível", row = getEstoque_disponivel(i, rows))
                                }
                                Column(modifier = Modifier.weight(1f)) {
                                    DetailData(header = "Reservado", row = getEstoque_reservado(i, rows))
                                }
                            }

                            Spacer(modifier = Modifier.height(defaultPadding))
                            DetailData(header = "Codprodleg", row = getCodprodleg(i, rows))

                            Spacer(modifier = Modifier.height(defaultPadding))
                            DetailData(header = "Codvol", row = getCodvol(i, rows))

                            Spacer(modifier = Modifier.height(defaultPadding))
                            DetailData(header = "Locprin", row = getLocprin(i, rows))

                            Spacer(modifier = Modifier.height(defaultPadding))
                            DetailData(header = "Codprodforn", row = getCodprodforn(i, rows))

                            Spacer(modifier = Modifier.height(defaultPadding))
                            DetailData(header = "Refforn", row = getRefforn(i, rows))

                            Spacer(modifier = Modifier.height(defaultPadding))
                            DetailData(header = "Referencia", row = getReferencia(i, rows))

                            Spacer(modifier = Modifier.height(defaultPadding))
                            DetailData(header = "Descrgrupoprod", row = getDescrgrupoprod(i, rows))

                            Spacer(modifier = Modifier.height(defaultPadding))
                            DetailData(header = "Pesoliq", row = getPesoliq(i, rows))

                            Spacer(modifier = Modifier.height(defaultPadding))
                            DetailData(header = "Origprod", row = getOrigprod(i, rows))

                            Spacer(modifier = Modifier.height(defaultPadding))
                            DetailData(header = "Ncm", row = getNcm(i, rows))

                            Spacer(modifier = Modifier.height(defaultPadding))
                            DetailData(header = "Codlocal", row = getCodlocal(i, rows))

                            Spacer(modifier = Modifier.height(defaultPadding))
                            DetailData(header = "Codparc_forn", row = getCodparc_forn(i, rows))

                            Spacer(modifier = Modifier.height(defaultPadding))
                            DetailData(header = "Fornecedor", row = getFornecedor(i, rows))

                            Spacer(modifier = Modifier.height(defaultPadding))
                            DetailData(header = "Nulinha", row = getNulinha(i, rows))

                            Spacer(modifier = Modifier.height(defaultPadding))
                            DetailData(header = "Descricao_linha", row = getDescricao_linha(i, rows))

                            Spacer(modifier = Modifier.height(defaultPadding))
                            DetailData(header = "Codprodecom", row = getCodprodecom(i, rows))

                        }
                }
            }
        }
    }
}