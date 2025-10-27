package com.basebox.omdbapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.basebox.omdbapp.data.local.dao.MovieDao
import com.basebox.omdbapp.data.local.entity.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class OmdbDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}