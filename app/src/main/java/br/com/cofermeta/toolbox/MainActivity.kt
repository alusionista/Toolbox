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
import br.com.cofermeta.toolbox.data.model.Jsession
import br.com.cofermeta.toolbox.ui.LoginScreen
import br.com.cofermeta.toolbox.ui.QueryScreen
import br.com.cofermeta.toolbox.ui.theme.ToolboxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val jsession = Jsession()
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
}
            
            /*
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "login_ui"
                    ) {
                        composable("login_ui") {
                            LoginScreen(
                                context = applicationContext,
                                navController = navController,
                                jsession = jsession
                            )
                        }
                        composable("query_ui") {
                            QueryScreen(
                                context = applicationContext,
                                navController = navController,
                                jsession = jsession
                            )
                        }

*/

                    }
                }
            }
        }
    }
}