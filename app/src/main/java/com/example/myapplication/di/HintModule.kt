package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.NoteDao
import com.example.myapplication.data.NoteDatabase
import com.example.myapplication.data.NoteRepository
import com.example.myapplication.data.NoteRepositoryImpl

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NoteDatabase {
        return Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            "notes"
        ).build()
    }

    @Provides
    fun provideNoteDao(db: NoteDatabase): NoteDao = db.noteDao()

    @Provides
    fun provideRepository(dao: NoteDao): NoteRepository {
        return NoteRepositoryImpl(dao)
    }
}