package com.presentation.navigation

sealed class NavigationEffect {

    data object TestOne : NavigationEffect()
    data object TestTwo : NavigationEffect()
    data object TestThree : NavigationEffect()
    data object FireBase: NavigationEffect()
    data class EditNote(val noteId: Int) : NavigationEffect()
    data object Back : NavigationEffect()

}