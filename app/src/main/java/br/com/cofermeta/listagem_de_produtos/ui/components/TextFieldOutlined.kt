package br.com.cofermeta.listagem_de_produtos.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun TextFieldOutlined(
    label: String,
    value: String,
    isNumeric: Boolean = false,
    onValueChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    if (isNumeric) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            shape = RoundedCornerShape(50.dp),
            singleLine = true,
            label = { Text(text = label) },
            modifier = Modifier.fillMaxWidth(),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Cancel,
                    contentDescription = "apagar conteúdo do campo",
                    tint = MaterialTheme.colors.secondaryVariant,
                    modifier = Modifier
                        .clip(RoundedCornerShape(50.dp))
                        .clickable { onValueChange("") }
                )
            },
        )
    } else {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            shape = RoundedCornerShape(50.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            label = { Text(text = label) },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Cancel,
                    contentDescription = "apagar conteúdo do campo",
                    tint = MaterialTheme.colors.secondaryVariant,
                    modifier = Modifier
                        .clip(RoundedCornerShape(50.dp))
                        .clickable { onValueChange("") }
                )
            },
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
}