package com.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.data.dao.NoteDao
import com.data.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}