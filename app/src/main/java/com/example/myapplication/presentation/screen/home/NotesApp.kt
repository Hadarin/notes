package com.example.myapplication.presentation.screen.home

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.presentation.navigation.AppRouter
import com.example.myapplication.presentation.navigation.NavigationEffect
import com.example.myapplication.presentation.navigation.Route
import com.example.myapplication.presentation.screen.home.components.EditNoteScreen
import com.example.myapplication.presentation.screen.home.components.HomeScreen
import com.example.myapplication.presentation.screen.home.components.TestScreenOne
import com.example.myapplication.presentation.screen.home.components.TestScreenThree
import com.example.myapplication.presentation.screen.home.components.TestScreenTwo
import org.koin.compose.koinInject

@Composable
fun NotesApp() {

    val navController = rememberNavController()
    val router: AppRouter = koinInject()

    //TODO check other compose effects
    LaunchedEffect(Unit) {
        //TODO collectAstStateWithLifeCycle
        router.navigationEffects.collect { effect ->
            when (effect) {
                NavigationEffect.Back -> navController.navigate(Route.Home)
                is NavigationEffect.EditNote -> navController.navigate(Route.EditNote(effect.noteId))
                NavigationEffect.TestOne -> navController.navigate(Route.TestOne)
                NavigationEffect.TestThree -> navController.navigate(Route.TestThree)
                NavigationEffect.TestTwo -> navController.navigate(Route.TestTwo)
            }
        }
    }

    MaterialTheme {
        NavHost(
            navController = navController,
            startDestination = Route.Home
        ) {
            composable<Route.Home> {
                HomeScreen()
            }
            composable<Route.EditNote> { backStackEntry ->
                val noteId = backStackEntry.arguments?.getInt("noteId") ?: 0
                EditNoteScreen(
                    noteId = noteId,
                )
            }
            composable<Route.TestOne> {
                TestScreenOne()
            }
            composable<Route.TestTwo> {
                TestScreenTwo()
            }
            composable<Route.TestThree> {
                TestScreenThree()
            }
        }
    }
}

