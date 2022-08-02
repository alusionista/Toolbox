package br.com.cofermeta.toolbox

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.cofermeta.toolbox.network.login.JsessionDataClass
import br.com.cofermeta.toolbox.ui.LoginScreen
import br.com.cofermeta.toolbox.ui.QueryScreen
import br.com.cofermeta.toolbox.ui.theme.ToolboxTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToolboxTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "login_ui"
                ) {
                    composable("login_ui") {
                        LoginScreen(context = applicationContext, navController = navController)
                    }
                    composable("query_ui") {
                        QueryScreen(context = applicationContext, navController = navController, jsession = JsessionDataClass())
                    }
                }
            }
        }
    }
}