package com.example.ordi2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.ordi2.practica_ordi.data.SessionManager
import com.example.ordi2.practica_ordi.navigation.AppNavigation
import com.example.ordi2.ui.theme.Ordi2Theme
import kotlinx.coroutines.launch
import com.example.ordi2.practica_ordi.ui.screens.StudentViewModel

class MainActivity : ComponentActivity() {
    private lateinit var sessionManager: SessionManager
    private val studentViewModel: StudentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionManager = SessionManager(this)

        setContent {
            Ordi2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // PRÁCTICA ORDI (ACTUAL)
                    val navController = rememberNavController()
                    val isLoggedIn by sessionManager.isLoggedIn.collectAsState(initial = false)
                    val students by studentViewModel.students.collectAsState()

                    AppNavigation(
                        navController = navController,
                        isLoggedIn = isLoggedIn,
                        onLoginSuccess = {
                            lifecycleScope.launch {
                                sessionManager.saveLoginState(true)
                            }
                        },
                        onLogout = {
                            lifecycleScope.launch {
                                sessionManager.saveLoginState(false)
                            }
                        },
                        students = students
                    )

                    // PROYECTO PARCIAL2 (COMENTADO)
                    /*
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
                    */
                }
            }
        }
    }
}