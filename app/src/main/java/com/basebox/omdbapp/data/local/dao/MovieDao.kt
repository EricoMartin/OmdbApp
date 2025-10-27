package com.basebox.omdbapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.basebox.omdbapp.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
        @Query("SELECT * FROM omdb_movies")
        fun getAll(): Flow<List<MovieEntity>>

        @Query("SELECT * FROM omdb_movies WHERE imdbID = :id LIMIT 1")
        suspend fun getById(id: String): MovieEntity?

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertAll(movies: List<MovieEntity>)

        @Query("DELETE FROM omdb_movies")
        suspend fun clearAll()
}