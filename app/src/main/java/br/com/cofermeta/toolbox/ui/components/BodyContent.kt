package br.com.cofermeta.toolbox.ui.components

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BodyContent(
    context: Context,
    padding: PaddingValues
) {

    Column(
        modifier = Modifier
            .padding(paddingValues = padding)
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            //.scrollable(rememberScrollState(), orientation = Orientation.Vertical)

    ) {
        Row {
            ProductItem()
        }
        Row {
            ProductItem()
        }
        Row {
            ProductItem()
        }
        Row {
            ProductItem()
        }
    }
    /*
                Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(defaultPadding)
                    .height(55.dp)
            ) {
                OutlinedTextField(
                    value = value,
                    onValueChange = onValueChange,
                    shape = RoundedCornerShape(50.dp),
                    label = {
                        Text(
                            text = "Código do produto"
                        )
                    },
                    modifier = Modifier.weight(10f)
                        .fillMaxSize()
                        .padding(end = 14.dp),
                    singleLine = true,
                    )
                Box (modifier = Modifier
                    .weight(2f)
                    .fillMaxSize()
                ) {
                    Button(
                        modifier = Modifier.fillMaxSize(),
                        onClick = { Toast.makeText(context, "Bom dia", Toast.LENGTH_SHORT).show() },
                        shape = RoundedCornerShape(50.dp),
                    ) {
                        Icon(imageVector = Icons.Rounded.Search, searchBarPlaceHolder)
                    }
                }
            }
    */
}
