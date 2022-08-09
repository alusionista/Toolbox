package br.com.cofermeta.toolbox.ui

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.cofermeta.toolbox.R
import br.com.cofermeta.toolbox.data.*
import br.com.cofermeta.toolbox.ui.components.ProductDetailDataItem
import br.com.cofermeta.toolbox.ui.components.ProductDetailDescriptionItem
import br.com.cofermeta.toolbox.ui.theme.ToolboxTheme
import br.com.cofermeta.toolbox.ui.theme.grayBlue
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest

@Composable
fun ProductDetailScreen(
    // SO TA DEFININDO O NAVCONTROLLER PADRAO PRO PREVIEW FUNCIONAR!
    // POR FAVOR FORNECA O NAVCONTROLLER CERTO SENAO VAI DAR PAU! Eu acho.
    navController: NavController = rememberNavController(),
) {
    Scaffold(
        bottomBar = {
            BottomNavigation(backgroundColor = Color.Transparent, elevation = 0.dp) {
                val iconSize = 25.dp
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.size(iconSize))},
                    label = { Text(text = "Consulta", fontSize = 12.sp) },
                    selected = false,
                    onClick = { TODO() }
                )
                BottomNavigationItem(
                    icon = { Icon(painterResource(id = R.drawable.ic_qr_code_scanner), contentDescription = null, modifier = Modifier.size(iconSize)) },
                    label = { Text(text = "Scanner", fontSize = 12.sp) },
                    selected = false,
                    onClick = { TODO() }
                )
                BottomNavigationItem(
                    icon = { Icon(painterResource(id = R.drawable.ic_user_badge), contentDescription = null, modifier = Modifier.size(iconSize)) },
                    label = { Text(text = "Usuário", fontSize = 12.sp) },
                    selected = false,
                    onClick = { TODO() }
                )
            }
        }
    ) { scaffoldBottomPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ProductDetailTopSection(
                navController = navController,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.2f)
                    .align(Alignment.TopCenter)
            )
            ProductDetailWrapper(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = 16.dp + 240.dp / 2f,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = scaffoldBottomPadding.calculateBottomPadding() + 16.dp
                    )
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colors.primaryVariant)
                    .align(Alignment.BottomCenter)
            )
            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier.fillMaxSize()
            ) {
                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(ENDIMAGEM)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(240.dp, 170.dp)
                        .offset(y = 70.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
            }
        }
    }

}

@Composable
fun ProductDetailTopSection(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.TopStart,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            tint = MaterialTheme.colors.onSurface,
            modifier = Modifier
                .size(36.dp)
                .offset(16.dp, 16.dp)
                .clickable {
                    navController.navigate("pokemon_list_screen")
                }
        )
    }
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "#$CODPROD",
            fontSize = 28.sp,
            color = MaterialTheme.colors.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .offset(y = 12.dp)
                .align(Alignment.TopCenter)
        )
    }
}

@Composable
fun ProductDetailWrapper(
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier) {
        Column(modifier = Modifier.padding(top = 240.dp / 2f)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .align(Alignment.Start)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    ProductDetailDataItem(header = "Código", row = CODPROD)
                }
                Column(modifier = Modifier.weight(1f)) {
                    ProductDetailDataItem(header = "Código", row = CODPROD)
                }
            }
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                ProductDetailDescriptionItem(header = "Descrição", row = PRODUTO)
            }
        }
    }
}

@Preview
@Composable
fun ProductDetailPreview() {
    ToolboxTheme { ProductDetailScreen() }
}