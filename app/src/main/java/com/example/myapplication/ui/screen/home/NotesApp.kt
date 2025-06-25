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
            startDestination = "home"
        ) {
            composable("home") {
                HomeScreen(navController = navController)
            }
            composable(
                "edit_note/{noteId}",
                arguments = listOf(navArgument("noteId") { type = NavType.IntType })
            ) { backStackEntry ->
                val noteId = backStackEntry.arguments?.getInt("noteId") ?: 0
                EditNoteScreen(
                    noteId = noteId,
                    navController = navController
                )
            }
            composable("testOne") {
                TestScreenOne(navController = navController)
            }
            composable("testTwo") {
                TestScreenTwo(navController = navController)
            }
            composable("testThree") {
                TestScreenThree(navController = navController)
            }
        }
    }
}

