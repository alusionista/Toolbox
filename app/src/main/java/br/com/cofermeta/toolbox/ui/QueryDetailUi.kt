package br.com.cofermeta.toolbox.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.widget.Placeholder
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest

@Composable
fun QueryDetailScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface)
            .padding(bottom = 16.dp)
    ){
        QueryDetailTopSection()
        QueryDetailStateWrapper()
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier.fillMaxSize()
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imgUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .offset(y = 20.dp)
            )
        }
    }
}

@Composable
fun QueryDetailTopSection() {
    
}

@Composable
fun QueryDetailStateWrapper() {
    
}