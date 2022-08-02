package br.com.cofermeta.toolbox.ui

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.cofermeta.toolbox.network.login.JsessionDataClass

@Composable
fun QueryScreen(context: Context?, navController: NavController, jsession: JsessionDataClass) {

    var query by rememberSaveable { mutableStateOf("12027") }

    Query(
        jsession = jsession,
        query = query,
        onQueryChange = { query = it },

        )
}

@Composable
fun Query(
    jsession: Jsession,
    query: String,
    onQueryChange: (String) -> Unit,
    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Consulta de Produtos",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            lineHeight = 38.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = query,
                onValueChange = onQueryChange,
                label = {
                    Text(
                        text = "Produto"
                    )
                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .width(250.dp)
            )
            Button(
                onClick = {
                    TODO()
                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier.align(Alignment.TopEnd)
                    .height(50.dp)
                    .width(50.dp)
            ) {
                Icon(Icons.Default.Search, contentDescription = "produto")
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = jsession.user)
    }
}
