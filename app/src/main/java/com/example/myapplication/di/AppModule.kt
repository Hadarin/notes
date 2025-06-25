package com.example.myapplication.di

import androidx.room.Room
import com.example.myapplication.data.NoteDatabase
import com.example.myapplication.data.NoteRepository
import com.example.myapplication.data.NoteRepositoryImpl
import com.example.myapplication.ui.screen.home.NoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
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

}