package br.com.cofermeta.toolkit

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import br.com.cofermeta.toolkit.ui.Login
import br.com.cofermeta.toolkit.ui.LoginScreen
import br.com.cofermeta.toolkit.ui.Query
import br.com.cofermeta.toolkit.ui.theme.ToolkitTheme


class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToolkitTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoginScreen(applicationContext)
                }
            }
        }
    }
}



