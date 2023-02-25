package com.githukudenis.composenavigation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.githukudenis.composenavigation.ui.theme.ComposeNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            ComposeNavigationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigator(navController = navController)
                }
            }
        }
    }
}


sealed class Destination(val route: String) {
    object Welcome: Destination(route = "welcome")
    object About: Destination(route = "about")
}


@Composable
fun AppNavigator(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Destination.Welcome.route) {
        composable(route = Destination.Welcome.route) {
            WelcomeScreen(onOpenName =  {
                navController.navigate(Destination.About.route + "/$it")
            })
        }

        composable(route = Destination.About.route + "/{name}") {
            Log.i("route", navController.currentBackStackEntry?.destination?.route ?: "")
            AboutScreen(name = it.arguments?.getString("name") ?: return@composable)
        }
    }
}

