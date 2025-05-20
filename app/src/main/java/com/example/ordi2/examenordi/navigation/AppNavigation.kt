package com.example.ordi2.examenordi.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ordi2.examenordi.ui.screens.*

sealed class Screen(val route: String) {
    object Initial : Screen("initial")
    object GameOver : Screen("game_over")
    object PokemonList : Screen("pokemon_list")
    object PokemonDetail : Screen("pokemon_detail/{pokemonName}") {
        fun createRoute(pokemonName: String) = "pokemon_detail/$pokemonName"
    }
}

@Composable
fun AppNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Initial.route
    ) {
        composable(Screen.Initial.route) {
            InitialScreen(navController = navController)
        }

        composable(Screen.GameOver.route) {
            GameOverScreen(navController = navController)
        }

        composable(Screen.PokemonList.route) {
            PokemonListScreen(navController = navController)
        }

        composable(
            route = Screen.PokemonDetail.route,
            arguments = listOf(navArgument("pokemonName") { type = NavType.StringType })
        ) { backStackEntry ->
            val pokemonName = backStackEntry.arguments?.getString("pokemonName") ?: ""
            PokemonDetailScreen(navController = navController, pokemonName = pokemonName)
        }
    }
}