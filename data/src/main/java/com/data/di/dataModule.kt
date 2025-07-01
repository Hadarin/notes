package com.data.di

import androidx.room.Room
import com.data.database.NoteDatabase
import com.data.repository.NoteRepositoryImpl
import com.domain.repository.NoteRepository
import org.koin.dsl.module

val dataModule = module {

    //Database
    single {
        Room.databaseBuilder(
            get(),
            NoteDatabase::class.java,
            "notes"
        ).build()
    }

    //Dao
    single {
        get<NoteDatabase>().noteDao()
    }

    factory<NoteRepository> {
        NoteRepositoryImpl(get())
    }

}