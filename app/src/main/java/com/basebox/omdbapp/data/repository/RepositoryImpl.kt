package com.basebox.omdbapp.data.repository

import com.basebox.omdbapp.data.local.dao.MovieDao
import com.basebox.omdbapp.data.local.entity.MovieEntity
import com.basebox.omdbapp.data.mappers.toEntity
import com.basebox.omdbapp.data.remote.service.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val dao: MovieDao,
    private val apiKey: String
) : Repository {

    override fun cachedMovies() = dao.getAll()

    override suspend fun searchAndCache(query: String, page: Int): Result<Unit> = withContext(
        Dispatchers.IO) {
        return@withContext try {
            val resp = api.search(query = query, page = page, apiKey = apiKey)
            if (resp.response == "True" && !resp.search.isNullOrEmpty()) {
                val entities = resp.search.map { it.toEntity() }
                dao.clearAll()
                dao.insertAll(entities)
                Result.success(Unit)
            } else {
                Result.failure(Exception(resp.error ?: "No results"))
            }
        } catch (t: Throwable) {
            Result.failure(t)
        }
    }

    override suspend fun getMovieDetail(imdbId: String): Result<MovieEntity?> = withContext(Dispatchers.IO) {
        return@withContext try {
            // try DB first
            val cached = dao.getById(imdbId)
            if (cached != null) return@withContext Result.success(cached)

            // fallback — fetch details by id and map minimal fields to OmdbMovieEntity
            val detail = api.getById(imdbId, apiKey)
            if (detail.response == "True" && !detail.imdbID.isNullOrBlank()) {
                val entity = MovieEntity(
                    imdbID = detail.imdbID,
                    title = detail.title,
                    year = detail.year,
                    type = null,
                    poster = detail.poster
                )
                dao.insertAll(listOf(entity))
                Result.success(entity)
            } else {
                Result.failure(Exception(detail.error ?: "Not found"))
            }
        } catch (t: Throwable) {
            Result.failure(t)
        }
    }
}