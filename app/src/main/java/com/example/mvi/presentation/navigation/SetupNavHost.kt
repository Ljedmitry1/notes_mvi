package com.example.mvi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mvi.presentation.screen.main.MainScreen

sealed class Screens(val route: String){
    object MainScreenType: Screens(route = "main_screen")
}

@Composable
fun SetupNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.MainScreenType.route){
        composable(route = Screens.MainScreenType.route){
            MainScreen(navController)
        }
    }
}