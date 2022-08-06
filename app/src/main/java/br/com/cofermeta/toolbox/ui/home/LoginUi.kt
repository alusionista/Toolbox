package br.com.cofermeta.toolbox.ui.home

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import br.com.cofermeta.toolbox.data.model.dataClass.Sankhya
import br.com.cofermeta.toolbox.data.model.Auth
import br.com.cofermeta.toolbox.data.values.defaultPasword
import br.com.cofermeta.toolbox.data.values.defaultUser
import br.com.cofermeta.toolbox.QueryActivity
import br.com.cofermeta.toolbox.ui.theme.white50p

@Composable
fun LoginScreen(context: Context?, navController: NavController, sankhya: Sankhya) {

    var user by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Login(
        context = context!!,
        navController = navController,
        user = user,
        password = password,
        sankhya = sankhya,
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
    sankhya: Sankhya,
    onUserChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(26.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Cofermeta Toolbox",
            textAlign = TextAlign.Left,
            fontSize = 25.sp,
            lineHeight = 38.sp,
            fontWeight = FontWeight.Bold,
            color = white50p,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = "Listagem de Produtos",
            textAlign = TextAlign.Left,
            fontSize = 40.sp,
            lineHeight = 38.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = user,
            onValueChange = onUserChange,
            placeholder = {
                Text(
                    text = "Usuário"
                )
            },
            shape = RoundedCornerShape(50.dp),
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = "usuário")
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
        TextField(
            value = password,
            onValueChange = onPasswordChange,
            placeholder = {
                Text(
                    text = "Senha"
                )
            },
            shape = RoundedCornerShape(50.dp),
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "senha")
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

        /*OutlinedTextField(
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
        )*/
        Spacer(modifier = Modifier.height(40.dp))
        Box {
            Button(
                onClick = {
                    val auth = Auth()
                    auth.verifyLogin(context, user, password, sankhya)
                    val statusMessage = sankhya.statusMessage.ifEmpty { "Login não realizado" }
                    if (sankhya.jsessionid.isEmpty()) Toast.makeText(
                        context,
                        statusMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                    else {

                        val queryIntent = Intent(context, QueryActivity::class.java).setFlags(
                            FLAG_ACTIVITY_NEW_TASK
                        )
                        startActivity(context, queryIntent, null)
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