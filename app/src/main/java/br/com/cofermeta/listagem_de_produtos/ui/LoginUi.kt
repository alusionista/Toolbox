package br.com.cofermeta.listagem_de_produtos.ui

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.cofermeta.listagem_de_produtos.data.*
import br.com.cofermeta.listagem_de_produtos.ui.components.LoadingLoginDialog
import br.com.cofermeta.listagem_de_produtos.ui.components.SimpleButton
import br.com.cofermeta.listagem_de_produtos.ui.components.TextFieldFilled
import br.com.cofermeta.listagem_de_produtos.ui.theme.white50p
import br.com.cofermeta.listagem_de_produtos.viewmodels.LoginViewModel

@Composable
fun LoginUI(
    navController: NavController,
    loginViewModel: LoginViewModel = viewModel()
) {

    val context = LocalContext.current
    val user by loginViewModel.user.observeAsState(INTEGRACAO)
    val password by loginViewModel.password.observeAsState(INTEGRACAO_PASSWORD)

    Login(
        context = context,
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
            text = COFERMETA_TOOLBOX,
            textAlign = TextAlign.Left,
            fontSize = 25.sp,
            lineHeight = 38.sp,
            fontWeight = FontWeight.Bold,
            color = white50p,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = LISTAGEM_DE_PRODUTOS,
            textAlign = TextAlign.Left,
            fontSize = 40.sp,
            lineHeight = 38.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(40.dp))

        TextFieldFilled(USUARIO, user, onUserChange, Icons.Default.Person)

        Spacer(modifier = Modifier.height(10.dp))

        TextFieldFilled(SENHA, password, onPasswordChange, Icons.Default.Lock, true)

        Spacer(modifier = Modifier.height(40.dp))

        SimpleButton(LOGIN) { loginViewModel.login(context, navController, user, password) }

        LoadingLoginDialog()
    }
}