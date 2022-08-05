package br.com.cofermeta.toolbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.cofermeta.toolbox.data.model.dataClass.Sankhya
import br.com.cofermeta.toolbox.ui.home.LoginScreen
import br.com.cofermeta.toolbox.ui.theme.ToolboxTheme

val sankhya = Sankhya()
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToolboxTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "login_ui"
                    ) {
                        composable("login_ui") {
                            LoginScreen(
                                context = applicationContext,
                                navController = navController,
                                sankhya = sankhya
                            )
                        }
                    }
                }
            }
        }
    }
}