package br.com.cofermeta.toolbox.ui

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.cofermeta.toolbox.data.defaultPasword
import br.com.cofermeta.toolbox.data.defaultUser
import br.com.cofermeta.toolbox.ui.componets.SimpleButton
import br.com.cofermeta.toolbox.ui.componets.SimpleTextField
import br.com.cofermeta.toolbox.ui.theme.white50p
import br.com.cofermeta.toolbox.viewmodels.LoginViewModel

@Composable
fun LoginScreen(context: Context?, navController: NavController, loginViewModel: LoginViewModel = viewModel()) {

    val user by loginViewModel.user.observeAsState(defaultUser)
    val password by loginViewModel.password.observeAsState(defaultPasword)

    Login(
        context = context!!,
        navController = navController,
        loginViewModel = loginViewModel,
        user = user,
        password = password,
        onUserChange = { loginViewModel.onUserChange(it) },
        onPasswordChange = { loginViewModel.onPasswordChange(it) }
    )
}

@Composable
fun Login(
    context: Context,
    navController: NavController,
    loginViewModel: LoginViewModel,
    user: String,
    password: String,
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
        SimpleTextField("Usuário", user, onUserChange, Icons.Default.Person)
        SimpleTextField("Senha", password, onPasswordChange, Icons.Default.Lock, true)
        Spacer(modifier = Modifier.height(40.dp))

        SimpleButton(
            label = "Login",
            onClick = {
                loginViewModel.login(context, navController)
            }
        )
    }
}