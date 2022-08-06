package br.com.cofermeta.toolbox.ui.componets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.helper.widget.MotionPlaceholder

@Composable
fun SimpleTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    icon: ImageVector,
    isPassword: Boolean = false,
    ) {
    if (isPassword) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = label
                )
            },
            shape = RoundedCornerShape(50.dp),
            leadingIcon = {
                Icon(icon, contentDescription = label)
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),

            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),

            colors = TextFieldDefaults.textFieldColors(
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    } else {
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = label
                )
            },
            shape = RoundedCornerShape(50.dp),
            leadingIcon = {
                Icon(icon, contentDescription = label)
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),

            colors = TextFieldDefaults.textFieldColors(
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }

}