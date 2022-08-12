package br.com.cofermeta.toolbox

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.cofermeta.toolbox.model.dataclasses.sankhya
import br.com.cofermeta.toolbox.ui.LoginScreen
import br.com.cofermeta.toolbox.ui.QueryScreen
import br.com.cofermeta.toolbox.ui.theme.ToolboxTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToolboxTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val startDestination = if (sankhya.user.isNotEmpty()) "query_ui" else "login_ui"

                    NavHost(
                        navController = navController,
                        startDestination = startDestination
                    ) {
                        composable("login_ui") {
                            LoginScreen(
                                context = applicationContext,
                                navController = navController,
                            )
                        }
                        composable("query_ui") {
                            QueryScreen(
                                context = applicationContext,
                                navController = navController,
                            )
                        }
                    }
                }
            }
        }
    }
}
