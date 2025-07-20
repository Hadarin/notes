package com.example.myapplication.sandbox

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//6/23/205
// Flow, Channel, StateFlow, SharedFlow
// suspend func, CoroutineContext, Job, CoroutineScope
// launch, async


//Kotlin by delegates feature

//Task: Create HomeScreen composable func, must have HomeViewModel
//HomeScreen subscribes on HomeViewModelState (data class HomeState)
//HomeScreen uses public ViewModel's func processIntent (sealed interface HomeIntent)


//DI Koin

//6/24/205
//Create second screen for each item of the list of notes (navigation tutor)
//View model with states without compose remember
//Implement Hilt instead of Koin

//6/27/2025
//Serialization under the hood
//Parselable under the hood
//Use case in domain layer for Repository

// separate classes data-domain-presentation
// presentation should know about domain,
// data should know about domain,
// domain shouldn't know anyone,

//8/1/2025 - manifest tags, create service in manifest


//7/72025 - Worker, API requests from android app (Gson, retrofit, okhttp) and show the data on screen

//7/7/2025 - try the socket connection, don't forget to use Flow and Result for rest data, try clock alarm (I need a work when app is closed)
//7/8/2025 - Get geolocation on the smartphone (google console, get key from geo-location-api and use it for google api
//7/9/2025 - firebase, push notification via button click
/*
7/10/2025
1. Try to send attributes from firebase inside the push notification
2. OnMessageReceived - try to prepare the bundle from the attribute data using PersonInfo data class as smthg like dto
(Serializable or Parcelable)
3. Then, in the main activity try to get the person info from bundle and display it on the screen
 */

data class PersonInfo (
        val name : String,
        val age : Int,
        val latitude : Float,
        val amount : Double,
        val isEnabled : Boolean,
        val id : Long
        )

//7/14//2025 -
// CoroutineContext,
// Job, CoroutineScope,
// Coroutines itself,
// Flow, SharedFlow,
// Channel,
// StateFlow,
// Coroutine Dispatchers