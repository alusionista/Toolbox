package br.com.cofermeta.toolbox.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
            color = MaterialTheme.colors.onSurface
        )
        Text(
            text = row,
            fontSize = 22.sp,
            color = MaterialTheme.colors.onSurface
        )
    }
}

@Preview
@Composable
fun ProductDetailDataItemPreview() {
    ToolboxTheme { ProductDetailDataItem(header = "Código", row = CODPROD) }
}