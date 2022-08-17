package br.com.cofermeta.toolbox

import android.content.Context
import android.hardware.camera2.CameraManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.cofermeta.toolbox.model.dataclasses.Sankhya
import br.com.cofermeta.toolbox.ui.LoginScreen
import br.com.cofermeta.toolbox.ui.QueryScreen
import br.com.cofermeta.toolbox.ui.theme.ToolboxTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToolboxTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainNavHost()
                }
            }
        }
    }
}

val sankhya = Sankhya()

@Composable
fun MainNavHost () {
    val navController = rememberNavController()
    val startDestination = if (sankhya.user.isNotEmpty()) "query_ui" else "login_ui"
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("login_ui") { LoginScreen( navController ) }
        composable("query_ui") { QueryScreen( navController) }
    }

}
