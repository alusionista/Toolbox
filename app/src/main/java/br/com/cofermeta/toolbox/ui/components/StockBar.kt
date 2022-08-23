package br.com.cofermeta.toolbox.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.cofermeta.toolbox.ui.theme.white50p

@Composable
fun StockBar(
    statName: String,
    estoqueMin: Float,
    estoqueMax: Float,
    barColor: Color,

) {
    val height: Dp = 28.dp
    val animDuration: Int = 1000
    val animDelay: Int = 100
    var animationPlayed by remember {mutableStateOf(false) }

    val curPercent = animateFloatAsState(
        targetValue = if (animationPlayed) {
            estoqueMin / estoqueMax
        } else 0f,
        animationSpec = tween(
            animDuration,
            animDelay
        )
    )

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colors.secondaryVariant)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(curPercent.value)
                .clip(RoundedCornerShape(10.dp))
                .background(barColor)
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = statName,
                fontWeight = FontWeight.Bold,
                color = white50p
            )
            Text(
                text = "${ curPercent.value * estoqueMax }",
                fontWeight = FontWeight.Bold,
                color = white50p
            )
        }
    }
}

@Preview
@Composable
fun StockAnimPreview() {
    StockBar(statName = "Estoque", estoqueMin = 0F, estoqueMax = 0F, barColor = Color.Magenta)
}