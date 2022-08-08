package br.com.cofermeta.toolbox.ui.componets
/*
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)
val BottomNavItems = listOf(
    BottomNavItem(
        label = "Home",
        icon = Icons.Default.Search,
        route = "home"
    ),
    BottomNavItem(
        label = "Category",
        icon = Icons.Default.Search,
        route = "category"
    ),

    BottomNavItem(
        label = "Favourite",
        icon = Icons.Default.Search,
        route = "favourite"
    ),
    BottomNavItem(
        label = "Settings",
        icon = Icons.Default.Search,
        route = "setting"
    )
)

@Composable
fun BasicBottomBar(context: Context, navController: NavController) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .height(65.dp),
                cutoutShape = CircleShape,
                backgroundColor = MaterialTheme.colors.surface,
                elevation = 10.dp
            ) {
                Toast.makeText(context, "Bom dia", Toast.LENGTH_SHORT).show()
            }
        }, content = { padding ->
            NavHostContainer(navController = navController as NavHostController, padding = padding)
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                onClick = {
                    Toast.makeText(context, "button clicked",Toast.LENGTH_LONG).show()
                },
                contentColor = MaterialTheme.colors.surface
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    )

}

@Composable
fun NavHostContainer(
    context: Context
    navController: NavHostController,
    padding: PaddingValues
) {

    NavHost(
        navController = navController,

        // set the start destination as home
        startDestination = "home",
        modifier = Modifier.padding(paddingValues = padding),

        builder = {

            // route : Home
            composable("home") {
                Toast.makeText(context, "oi", Toast.LENGTH_SHORT).show()
            }

            // route : furniture
            composable("category") {
                Toast.makeText(context, "oi", Toast.LENGTH_SHORT).show()
            }


            // route : search
            composable("favourite") {
                Toast.makeText(context, "oi", Toast.LENGTH_SHORT).show()
            }

            // route : profile
            composable("setting") {
                Toast.makeText(context, "oi", Toast.LENGTH_SHORT).show()
            }
        })

}

@Composable
fun BottomNavigationBar(navController: NavHostController) {

    BottomNavigation(

        // set background color
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp
    ) {

        // observe the backstack
        val navBackStackEntry  by navController.currentBackStackEntryAsState()

        // observe current route to change the icon
        // color,label color when navigated
        val currentRoute = navBackStackEntry?.destination?.route

        // Bottom nav items we declared
        BottomNavItems.forEach { navItem ->

            // Place the bottom nav items
            BottomNavigationItem(

                unselectedContentColor = MaterialTheme.colors.onSurface,
                selectedContentColor = MaterialTheme.colors.primary,
                // it currentRoute is equal then its selected route
                selected = currentRoute == navItem.route,

                // navigate on click
                onClick = {
                    navController.navigate(navItem.route){
                        popUpTo(
                            navController.graph.startDestinationId
                        )
                        launchSingleTop = true
                    }
                },

                // Icon of navItem
                icon = {
                    Icon(
                        painter = androidx.compose.material.icons.Icons(Icons.Default.Search),
                        contentDescription = navItem.label,
                        modifier = Modifier
                            .size(20.dp)
                    )
                },

                alwaysShowLabel = false
            )
        }
    }*/
