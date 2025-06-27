package com.example.myapplication.presentation.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class AppRouter {

    private val _navigationEffects = MutableSharedFlow<NavigationEffect>(replay = 1)
    val navigationEffects = _navigationEffects.asSharedFlow()

    fun navigateTo(event: NavigationEffect) {
        _navigationEffects.tryEmit(event)
    }
}