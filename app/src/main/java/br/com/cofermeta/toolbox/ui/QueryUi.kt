package br.com.cofermeta.toolbox.ui

import android.content.Context
import android.widget.Toast
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
import br.com.cofermeta.toolbox.data.model.Jsession
import br.com.cofermeta.toolbox.data.model.ProductQuery
import br.com.cofermeta.toolbox.network.SankhyaAuth
import br.com.cofermeta.toolbox.network.SankhyaQuery

@Composable
fun QueryScreen(context: Context, navController: NavController, jsession: Jsession) {

    var query by rememberSaveable { mutableStateOf("") }
    var hasResult by rememberSaveable { mutableStateOf(false) }

    Query(
        context = context,
        jsession = jsession,
        query = query,
        hasResult = hasResult,
        onHasResultChange = { hasResult = it },
        onQueryChange = { query = it },
    )
}

@Composable
fun Query(
    context: Context,
    jsession: Jsession,
    query: String,
    hasResult: Boolean,
    onHasResultChange: (Boolean) -> Unit,
    onQueryChange: (String) -> Unit,
) {
    var queryResult = ProductQuery()
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
        Row(modifier = Modifier.height(56.dp)) {
            Column(
                Modifier.weight(5f)
            ) {
                OutlinedTextField(
                    value = query,
                    onValueChange = onQueryChange,
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(end = 8.dp)
                )
            }
            Column(
                Modifier.weight(1f)
            ) {
                Button(
                    onClick = {
                        SankhyaQuery().tryQuery(context, jsession, queryResult)
                        //onHasResultChange = true
                    },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()

                ) {
                    Icon(Icons.Default.Search, contentDescription = "consulta")
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        if(hasResult){
            Text(
                text = """
            ${jsession.user}
            ${queryResult.body}
        """.trimIndent()
            )
        }
    }
}
