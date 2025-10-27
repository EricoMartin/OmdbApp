package com.basebox.omdbapp.data.remote.service

import com.basebox.omdbapp.data.remote.MovieDetail
import com.basebox.omdbapp.data.remote.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/")
    suspend fun search(
        @Query("s") query: String,
        @Query("page") page: Int = 1,
        @Query("apikey") apiKey: String
    ): SearchResponse

    @GET("/")
    suspend fun getById(
        @Query("i") imdbId: String,
        @Query("apikey") apiKey: String
    ): MovieDetail

    @GET("/")
    suspend fun getByTitle(
        @Query("t") title: String,
        @Query("apikey") apiKey: String
    ): MovieDetail
}