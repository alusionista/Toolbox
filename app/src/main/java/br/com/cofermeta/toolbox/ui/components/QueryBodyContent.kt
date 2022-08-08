package br.com.cofermeta.toolbox.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun QueryBodyContent(padding: PaddingValues) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(paddingValues = padding)
            .fillMaxSize()
    ) {
        Text(text = "Body Content")
    }
}
