package com.basebox.omdbapp.data.di

import com.basebox.omdbapp.data.local.dao.MovieDao
import com.basebox.omdbapp.data.remote.service.ApiService
import com.basebox.omdbapp.data.repository.Repository
import com.basebox.omdbapp.data.repository.RepositoryImpl
import com.basebox.omdbapp.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideRepository(
        api: ApiService,
        dao: MovieDao
    ): Repository {
        val apiKey = BuildConfig.OMDB_API_KEY
        println("API Key: $apiKey")
        return RepositoryImpl(api, dao, apiKey)
    }
}