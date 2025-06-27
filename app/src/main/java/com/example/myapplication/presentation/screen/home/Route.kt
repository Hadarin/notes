package com.example.myapplication.presentation.screen.home

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    object Home : Route

    @Serializable
    object TestOne : Route

    @Serializable
    object TestTwo : Route

    @Serializable
    object TestThree : Route

    @Serializable
    data class EditNote(val noteId: Int) : Route
}