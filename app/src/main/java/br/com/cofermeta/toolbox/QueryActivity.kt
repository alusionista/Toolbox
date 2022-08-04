package br.com.cofermeta.toolbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.cofermeta.toolbox.network.login.JsessionDataClass
import br.com.cofermeta.toolbox.ui.QueryScreen
import br.com.cofermeta.toolbox.ui.theme.ToolboxTheme

class QueryActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { 
            ToolboxTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "query_ui" ) {
                    composable("query_ui") {
                    QueryScreen(context = applicationContext, navController = navController, jsession = JsessionDataClass())
                    }
                }
            }
        }
    }
}