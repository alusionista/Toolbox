package br.com.cofermeta.toolbox.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
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

private val LightColorPalette = lightColors(
    primary = Color.White,
    primaryVariant = veryBlue,
    secondary = almostBlue,
    background = lighterBlue,
    surface = lighterBlue,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

@Composable
fun ToolboxTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    MaterialTheme(
        colors = if (darkTheme) DarkColorPalette else DarkColorPalette,//LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content,
    )

    val systemUiController = rememberSystemUiController()

    if (darkTheme) {
        systemUiController.setSystemBarsColor(
            color = veryBlue
        )
    } else {
        systemUiController.setSystemBarsColor(
            color = Color.White
        )
    }

}
