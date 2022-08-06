package br.com.cofermeta.toolbox.ui

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.cofermeta.toolbox.data.*
import br.com.cofermeta.toolbox.ui.componets.BasicTopBar
import br.com.cofermeta.toolbox.ui.componets.QueryDrawer
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest

@Composable
fun QueryScreen(
    context: Context,
    navController: NavController
) {

    var query by rememberSaveable { mutableStateOf("") }
    var hasResult by rememberSaveable { mutableStateOf(false) }

    QueryMainScreen(
        context = context,
        query = query,
        hasResult = hasResult,
        onHasResultChange = { hasResult = it },
        onQueryChange = { query = it },
        navController = navController
    )
    QueryDrawer(context)
}

@Composable
fun QueryMainScreen(
    context: Context,
    query: String,
    hasResult: Boolean,
    onHasResultChange: (Boolean) -> Unit,
    onQueryChange: (String) -> Unit,
    navController: NavController
) {
    BasicTopBar(title = "Listagem de Produtos")
    //BasicBottomBar(context, navController)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        TextField(
            value = query,
            onValueChange = onQueryChange,
            placeholder = {
                Text(
                    text = searchBarPlaceHolder
                )
            },
            shape = RoundedCornerShape(50.dp),
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = searchBarPlaceHolder)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            colors = TextFieldDefaults.textFieldColors(
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }

    Spacer(modifier = Modifier.height(20.dp))
    //QueryHeader()
    //QueryBody()
}


@Composable
fun QueryHeader(body: String) {
    Text(
        text = body,
        fontSize = 16.sp,
        modifier = Modifier
            .wrapContentWidth()
            .padding(all = 8.dp)
    )
}

@Composable
fun QueryBody(body: String) {
    Surface(
        color = Color.LightGray, modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp)
            .clip(RoundedCornerShape(12.dp))
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            Column {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    SubcomposeAsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imgUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier
                            .size(172.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                    Column() {
                        Column {
                            Text(text = "Código", fontSize = 14.sp)
                            Text(text = codigo, fontSize = 22.sp)
                        }
                        Column {
                            Text(text = "Modelo", fontSize = 14.sp)
                            Text(text = modelo, fontSize = 22.sp)
                        }
                        Column {
                            Text(text = "Referência", fontSize = 14.sp)
                            Text(text = referencia, fontSize = 22.sp)
                        }
                    }
                }
                Column {
                    Text(text = "Descrição", fontSize = 14.sp)
                    Text(text = descricao, fontSize = 22.sp)
                }
            }
        }
    }
}
