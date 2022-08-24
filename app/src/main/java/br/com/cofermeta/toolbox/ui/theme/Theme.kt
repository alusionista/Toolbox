package br.com.cofermeta.toolbox.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = almostBlue,
    primaryVariant = softBlue,
    secondary = grayBlue,
    secondaryVariant = white50p,
    background = veryBlue,
    surface = veryBlue,
    error = notificationColor,
    onPrimary = veryBlue,
    onSecondary = Color.White,
)

@Composable
fun ToolboxTheme(content: @Composable () -> Unit) {

    MaterialTheme(
        colors = DarkColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content,
    )

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = veryBlue
    )

}
