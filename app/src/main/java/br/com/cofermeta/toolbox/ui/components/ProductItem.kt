package br.com.cofermeta.toolbox.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import br.com.cofermeta.toolbox.data.DEFAULT_PADDING
import br.com.cofermeta.toolbox.model.DataFormatting
import br.com.cofermeta.toolbox.model.dataclasses.QueryResult
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.google.gson.JsonElement

@Composable
fun ProductItem(
    queryResult: QueryResult,
) {
    val data = DataFormatting()
    val rows = queryResult.rows
    val numberOfRows = if (queryResult.numberOfRows < 100) queryResult.numberOfRows else 100

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
                    .padding(DEFAULT_PADDING)
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
                                .data(data.getEndimagem(i, rows))
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
                            text = data.getCodprod(i, rows),
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White,
                            letterSpacing = 0.sp,
                        )
                        Text(
                            text = data.getMarca(i, rows),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colors.secondaryVariant,
                            letterSpacing = 0.sp,
                        )
                        Text(
                            text = data.getVlrvenda(i, rows),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White,
                            letterSpacing = 0.sp,
                        )
                    }
                }
                Text(
                    modifier = Modifier.padding(top = 12.dp),
                    text = data.getDescrprod(i, rows),
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
    rows: JsonElement,
    onShowDialog: (Boolean) -> Unit
) {
    val data = DataFormatting()

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
                Spacer(modifier = Modifier.height(DEFAULT_PADDING))
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
                                    text = data.getCodprod(i, rows),
                                    color = Color.White,
                                )
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 10.dp),
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
                Column(
                    modifier = Modifier.padding(DEFAULT_PADDING)
                ) {
                    SubcomposeAsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(data.getEndimagem(i, rows))
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            //.size(240.dp, 180.dp)
                            .fillMaxSize()
                            .clip(RoundedCornerShape(10.dp))
                    )
                    Spacer(modifier = Modifier.height(DEFAULT_PADDING))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Start)
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            DetailData(header = "Marca", row = data.getMarca(i, rows))
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            DetailData(header = "Preço", row = data.getVlrvenda(i, rows))
                        }
                    }

                    Spacer(modifier = Modifier.height(DEFAULT_PADDING))
                    DetailData(header = "Categoria", row = data.getDescrgrupoprod(i, rows))

                    Spacer(modifier = Modifier.height(DEFAULT_PADDING))
                    DetailData(
                        header = "Linha ${data.getNulinha(i, rows)}",
                        row = data.getDescricao_linha(i, rows)
                    )

                    Spacer(modifier = Modifier.height(DEFAULT_PADDING))
                    DetailData(header = "Descrição", row = data.getDescrprod(i, rows))

                    Spacer(modifier = Modifier.height(DEFAULT_PADDING))
                    if (data.getCusrep(i, rows).contains("$") ||
                        data.getCusger(i, rows).contains("$") ||
                        data.getCusvariavel(i, rows).contains("$")
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Start)
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                DetailData(
                                    header = "Custo Reposição",
                                    row = data.getCusrep(i, rows)
                                )
                            }
                            Column(modifier = Modifier.weight(1f)) {
                                DetailData(
                                    header = "Custo Gerencial",
                                    row = data.getCusger(i, rows)
                                )
                            }
                            Column(modifier = Modifier.weight(1f)) {
                                DetailData(
                                    header = "Custo Variável",
                                    row = data.getCusvariavel(i, rows)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(DEFAULT_PADDING))
                    }

                    if (data.getEstoque_disponivel(i, rows) > 0F) {
                        Text(
                            text = "Estoque",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colors.secondaryVariant,
                            letterSpacing = 0.sp,
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.End)
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "mín. ${data.getEstmin(i, rows)}",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = MaterialTheme.colors.secondaryVariant,
                                    letterSpacing = 0.sp,
                                )
                            }
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.End
                            ) {
                                Text(
                                    text = "máx. ${data.getEstmax(i, rows)}",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = MaterialTheme.colors.secondaryVariant,
                                    letterSpacing = 0.sp,
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        StockBar(
                            statName = "Disponível",
                            estoqueMin = data.getEstoque_disponivel(i, rows),
                            estoqueMax = data.getEstmax(i, rows),
                            barColor = Color(0xFF12664F)
                        )
                    } else {
                        DetailData(header = "Estoque", row = "Indisponível")
                    }

                    Spacer(modifier = Modifier.height(DEFAULT_PADDING))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Start)
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            DetailData(header = "Peso", row = data.getPesoliq(i, rows))
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            DetailData(header = "Unidade", row = data.getCodvol(i, rows))
                        }


                    }
                    Spacer(modifier = Modifier.height(DEFAULT_PADDING))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Start)
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            DetailData(header = "NCM", row = data.getNcm(i, rows))
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            DetailData(header = "Origem", row = data.getOrigprod(i, rows))
                        }

                    }
                    Spacer(modifier = Modifier.height(DEFAULT_PADDING))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Start)
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            DetailData(header = "Local Principal", row = data.getLocprin(i, rows))
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            DetailData(header = "Cód. Local", row = data.getCodlocal(i, rows))
                        }

                    }
                    Spacer(modifier = Modifier.height(DEFAULT_PADDING))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Start)
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            DetailData(
                                header = "Cód. Fornecedor",
                                row = data.getCodprodforn(i, rows)
                            )
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            DetailData(
                                header = "Cód. Prod Legado",
                                row = data.getCodprodleg(i, rows)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(DEFAULT_PADDING))
                    DetailData(
                        header = "Fornecedor #${data.getCodparc_forn(i, rows)}",
                        row = data.getFornecedor(i, rows)
                    )

                    Spacer(modifier = Modifier.height(DEFAULT_PADDING))
                    DetailData(header = "Cód. de barras", row = data.getReferencia(i, rows))

                    Spacer(modifier = Modifier.height(DEFAULT_PADDING))
                    DetailData(
                        header = "Cód. Prod. e-commerce",
                        row = data.getCodprodecom(i, rows)
                    )

                }
            }
        }
    }
}

