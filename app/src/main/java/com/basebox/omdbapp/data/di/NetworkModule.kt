package com.basebox.omdbapp.data.di

import com.basebox.omdbapp.data.remote.service.ApiService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val OMDB_BASE = "https://www.omdbapi.com/"

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

       return OkHttpClient.Builder()
           .addInterceptor(logging).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi, ok: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(OMDB_BASE)
            .client(ok)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    @Singleton
    fun provideOmdbApi(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)
}