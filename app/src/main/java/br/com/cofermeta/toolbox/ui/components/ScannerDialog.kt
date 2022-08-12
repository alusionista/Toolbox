package br.com.cofermeta.toolbox.ui.components

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import br.com.cofermeta.toolbox.data.defaultPadding

@Composable
fun ScannerDialog(
    onShowDialog: (Boolean) -> Unit
) {
    Dialog(onDismissRequest = { onShowDialog(false) }) {
        Surface(
            modifier = Modifier.width(250.dp),
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colors.primaryVariant
        ) {
            Column() {
                Spacer(modifier = Modifier.height(defaultPadding))
                Box(Modifier.height(32.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        ProvideTextStyle(value = MaterialTheme.typography.h5) {
                            CompositionLocalProvider(
                                LocalContentAlpha provides ContentAlpha.high,
                            ) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center,
                                    maxLines = 1,
                                    fontWeight = FontWeight.ExtraBold,
                                    text = "Scanner",
                                    color = Color.White,
                                )
                            }
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(end = 10.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        CompositionLocalProvider(
                            LocalContentAlpha provides ContentAlpha.high,
                        ) {
                            IconButton(
                                onClick = { onShowDialog(false) },
                                enabled = true,
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Cancel,
                                    contentDescription = "voltar",
                                    tint = Color.White,
                                    modifier = Modifier
                                        .width(30.dp)
                                        .height(30.dp)
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(defaultPadding))
                Scanner(/*context,*/ {onShowDialog(it)})
                Spacer(modifier = Modifier.height(defaultPadding))

            }

        }
    }
}