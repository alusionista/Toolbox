package br.com.cofermeta.toolbox.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.cofermeta.toolbox.data.model.Jsession

import br.com.cofermeta.toolbox.network.login.SankhyaAuth

@Composable
fun LoginScreen(context: Context?, navController: NavController, jsession: Jsession) {

    var user by rememberSaveable { mutableStateOf("integracao") }
    var password by rememberSaveable { mutableStateOf("654321") }

    Login(
        context = context!!,
        navController = navController,
        user = user,
        password = password,
        jsession = jsession,
        onUserChange = { user = it },
        onPasswordChange = { password = it }
    )
}

@Composable
fun Login(
    context: Context,
    navController: NavController,
    user: String,
    password: String,
    jsession: Jsession,
    onUserChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Cofermeta Toolbox",
            textAlign = TextAlign.Left,
            fontSize = 40.sp,
            lineHeight = 38.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = user,
            onValueChange = onUserChange,
            label = {
                Text(
                    text = "Usuário"
                )
            },
            shape = RoundedCornerShape(50.dp),
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = "usuário")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = {
                Text(
                    text = "Senha"
                )
            },
            shape = RoundedCornerShape(50.dp),
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "senha")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(40.dp))
        Box {
            Button(
                onClick = {
                    SankhyaAuth().verifyLogin(user, password, jsession)
                    var i = 0
                    do {
                        if (jsession.id.isNotEmpty() || jsession.statusMessage.isNotEmpty()) break
                        Thread.sleep(500)
                        i++
                    } while (i < 10)
                    val statusMessage = jsession.statusMessage.ifEmpty { "Login não realizado" }
                    if (jsession.id.isEmpty()) Toast.makeText(
                        context,
                        statusMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                    else {
                        navController.navigate("query_ui")
                    }
                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = "Login", fontSize = 16.sp
                )
            }
        }
    }
}