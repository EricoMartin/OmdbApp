package com.basebox.omdbapp.data.di

import android.content.Context
import androidx.room.Room
import com.basebox.omdbapp.data.local.OmdbDatabase
import com.basebox.omdbapp.data.local.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext ctx: Context): OmdbDatabase =
        Room.databaseBuilder(ctx, OmdbDatabase::class.java, "app_database")
            .fallbackToDestructiveMigration() // dev-friendly; provide migrations for prod
            .build()

    @Provides
    fun provideOmdbDao(db: OmdbDatabase): MovieDao = db.movieDao()
}