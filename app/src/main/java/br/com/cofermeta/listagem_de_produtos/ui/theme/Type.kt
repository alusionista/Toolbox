package br.com.cofermeta.listagem_de_produtos.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import br.com.cofermeta.listagem_de_produtos.R

val Nunito = FontFamily(
    Font(R.font.nunito_sans)
)

// Set of Material typography styles to start with
val Typography = Typography(
    defaultFontFamily = Nunito,
    h1 = TextStyle(
        fontWeight = FontWeight.ExtraBold,
    ),
    h2 = TextStyle(
        fontWeight = FontWeight.ExtraBold,
    ),
    h3 = TextStyle(
        fontWeight = FontWeight.Bold,
    ),
    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = 2.sp
    ),
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
)