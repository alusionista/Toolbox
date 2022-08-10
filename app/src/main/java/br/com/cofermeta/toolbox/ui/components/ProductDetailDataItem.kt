package br.com.cofermeta.toolbox.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import br.com.cofermeta.toolbox.data.CODPROD
import br.com.cofermeta.toolbox.ui.theme.ToolboxTheme

@Composable
fun ProductDetailDataItem(header: String, row: String) {
    Column {
        Text(
            text = header,
            fontSize = 14.sp,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colors.secondaryVariant,
            letterSpacing = 0.sp,
        )
        Text(
            text = row,
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White,
            letterSpacing = 0.sp,
        )
    }
}
