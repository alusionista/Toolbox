package br.com.cofermeta.toolbox.ui.home

import android.content.Context
import android.view.KeyEvent.KEYCODE_ENTER
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.cofermeta.toolbox.data.model.dataClass.Sankhya
import br.com.cofermeta.toolbox.data.model.dataClass.ProductQuery
import br.com.cofermeta.toolbox.data.model.Query
import br.com.cofermeta.toolbox.data.values.searchBarPlaceHolder
import br.com.cofermeta.toolbox.ui.*
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest

@Composable
fun QueryScreen(
    context: Context,
    navController: NavController,
    sankhya: Sankhya,
    queryResult: ProductQuery
) {

    var query by rememberSaveable { mutableStateOf("") }
    var hasResult by rememberSaveable { mutableStateOf(false) }

    Query(
        context = context,
        sankhya = sankhya,
        query = query,
        hasResult = hasResult,
        onHasResultChange = { hasResult = it },
        onQueryChange = { query = it },
        queryResult = queryResult
    )
}

@Composable
fun Query(
    context: Context,
    sankhya: Sankhya,
    query: String,
    hasResult: Boolean,
    onHasResultChange: (Boolean) -> Unit,
    onQueryChange: (String) -> Unit,
    queryResult: ProductQuery
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Listagem de Produtos",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            lineHeight = 38.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
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
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),


/*                    keyboardActions = KeyboardActions(
                        onDone = { focusRequester.requestFocus() }
                    ),
                    modifier = Modifier.onKeyEvent {
                        if (it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER){
                            focusRequester.requestFocus()
                            true
                        }
                        false
                    }*/


            /*if (query.isNotEmpty()) Query().tryQuery(
                context,
                sankhya,
                queryResult,
                query
            )
            else Toast.makeText(context, "Nenum produto encontrado", Toast.LENGTH_SHORT)
                .show()*/


        )
    }

    Spacer(modifier = Modifier.height(20.dp))
    Text(
        text = queryResult.body
    )
    QueryHeader(queryResult.body)
//QueryBody(queryResult.body)
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
