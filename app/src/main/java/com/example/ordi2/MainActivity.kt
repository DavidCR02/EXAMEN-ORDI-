package com.example.ordi2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ordi2.parcial2.ui.screens.DetallesPasajeroScreen
import com.example.ordi2.parcial2.ui.screens.VuelosScreen
import com.example.ordi2.ui.theme.Ordi2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ordi2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    
                    NavHost(
                        navController = navController,
                        startDestination = "vuelos"
                    ) {
                        composable("vuelos") {
                            VuelosScreen(navController = navController)
                        }
                        composable(
                            route = "detalles_pasajero/{destinoId}",
                            arguments = listOf(
                                navArgument("destinoId") {
                                    type = NavType.StringType
                                }
                            )
                        ) { backStackEntry ->
                            val destinoId = backStackEntry.arguments?.getString("destinoId") ?: ""
                            DetallesPasajeroScreen(
                                navController = navController,
                                destinoId = destinoId
                            )
                        }
                    }
                }
            }
        }
    }
}