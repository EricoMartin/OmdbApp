package com.basebox.omdbapp.data.repository

import com.basebox.omdbapp.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun cachedMovies(): Flow<List<MovieEntity>>
    suspend fun searchAndCache(query: String, page: Int = 1): Result<Unit>
    suspend fun getMovieDetail(imdbId: String): Result<MovieEntity?>
}