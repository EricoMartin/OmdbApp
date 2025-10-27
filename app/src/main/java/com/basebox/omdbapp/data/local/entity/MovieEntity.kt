package com.basebox.omdbapp.data.local.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "omdb_movies")
data class MovieEntity(
    @PrimaryKey val imdbID: String,
    val title: String?,
    val year: String?,
    val type: String?,
    val poster: String?,
    val cachedAt: Long = System.currentTimeMillis()
)
