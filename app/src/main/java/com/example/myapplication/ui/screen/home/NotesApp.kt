package com.example.myapplication.ui.screen.home

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.ui.screen.home.components.*
import com.example.myapplication.ui.screen.home.components.HomeScreen

@Composable
fun NotesApp() {
    val navController = rememberNavController()

    MaterialTheme {
        NavHost(
            navController = navController,
            startDestination = Router.Home.route
        ) {
            composable(Router.Home.route) {
                HomeScreen(navController = navController)
            }
            composable(
                Router.EditNote.route,
                arguments = listOf(navArgument("noteId") { type = NavType.IntType })
            ) { backStackEntry ->
                val noteId = backStackEntry.arguments?.getInt("noteId") ?: 0
                EditNoteScreen(
                    noteId = noteId,
                    navController = navController
                )
            }
            composable(Router.TestOne.route) {
                TestScreenOne(navController = navController)
            }
            composable(Router.TestTwo.route) {
                TestScreenTwo(navController = navController)
            }
            composable(Router.TestThree.route) {
                TestScreenThree(navController = navController)
            }
        }
    }
}

