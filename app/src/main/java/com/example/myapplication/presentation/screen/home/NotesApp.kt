package com.example.myapplication.presentation.screen.home

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.presentation.screen.home.components.*
import com.example.myapplication.presentation.screen.home.components.HomeScreen

@Composable
fun NotesApp() {

    val navController = rememberNavController()

    MaterialTheme {
        NavHost(
            navController = navController,
            startDestination = Route.Home
        ) {
            composable<Route.Home> {
                HomeScreen(navController = navController)
            }
            composable<Route.EditNote> { backStackEntry ->
                val noteId = backStackEntry.arguments?.getInt("noteId") ?: 0
                EditNoteScreen(
                    noteId = noteId,
                    navController = navController
                )
            }
            composable<Route.TestOne> {
                TestScreenOne(navController = navController)
            }
            composable<Route.TestTwo> {
                TestScreenTwo(navController = navController)
            }
            composable<Route.TestThree> {
                TestScreenThree(navController = navController)
            }
        }
    }
}

