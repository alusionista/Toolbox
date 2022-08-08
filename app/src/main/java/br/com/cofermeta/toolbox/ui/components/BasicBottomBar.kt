package br.com.cofermeta.toolbox.ui.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BasicBottomBar(context: Context) {
    val appBarHorizontalPadding = 4.dp
    val selectedIndex = remember { mutableStateOf(0) }
    val titleIconModifier = Modifier
        .fillMaxHeight()
        .width(72.dp - appBarHorizontalPadding)
    BottomAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Rounded.Search,"")
        },
            label = { Text(text = "Profile") },
            selected = (selectedIndex.value == 1),
            onClick = {
                Toast.makeText(context, "Profile", Toast.LENGTH_SHORT).show()
            })
        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Person,"")
        },
            label = { Text(text = "Profile") },
            selected = (selectedIndex.value == 2),
            onClick = {
                Toast.makeText(context, "Profile", Toast.LENGTH_SHORT).show()
            })
        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Person,"")
        },
            label = { Text(text = "Profile") },
            selected = (selectedIndex.value == 3),
            onClick = {
                Toast.makeText(context, "Profile", Toast.LENGTH_SHORT).show()
            })
        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Person,"")
        },
            label = { Text(text = "Profile") },
            selected = (selectedIndex.value == 4),
            onClick = {
                Toast.makeText(context, "Profile", Toast.LENGTH_SHORT).show()
            })

    }

}
