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
import br.com.cofermeta.toolbox.data.model.ProductQuery
import br.com.cofermeta.toolbox.ui.QueryScreen
import br.com.cofermeta.toolbox.ui.theme.ToolboxTheme

class QueryActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var queryResult = ProductQuery()
        setContent { 
            ToolboxTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "query_ui" ) {
                        composable("query_ui") {
                            QueryScreen(context = applicationContext, navController = navController, sankhya = sankhya, queryResult = queryResult)
                        }
                    }
                }
            }
        }
    }
}