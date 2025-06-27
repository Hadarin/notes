package com.example.myapplication.di

import androidx.room.Room
import com.example.myapplication.data.NoteDatabase
import com.example.myapplication.data.NoteRepositoryImpl
import com.example.myapplication.presentation.navigation.AppRouter
import com.example.myapplication.presentation.screen.home.NoteViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

//Init DB
    single {
        Room.databaseBuilder(get(),
            NoteDatabase::class.java,
            "notes"
        ).build()
    }

//Init DAO
    single {
        get<NoteDatabase>().noteDao()
    }

//Init Repository
    factory {
        NoteRepositoryImpl(get())
    }

//Init ViewModel
    viewModel { NoteViewModel(repository = get()) }


    single { AppRouter() }

}
