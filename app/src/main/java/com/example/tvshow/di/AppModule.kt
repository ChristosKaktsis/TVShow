package com.example.tvshow.di

import android.app.Application
import com.example.tvshow.database.getDatabase
import com.example.tvshow.repository.TVShowsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRepository(app:Application):TVShowsRepository{
        return TVShowsRepository(getDatabase(app))
    }
}