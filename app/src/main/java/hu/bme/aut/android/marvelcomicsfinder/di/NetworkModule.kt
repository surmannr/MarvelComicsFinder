package hu.bme.aut.android.marvelcomicsfinder.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.android.marvelcomicsfinder.network.MarvelComicsService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideBaseURL(): String {
        return "BASE_URL"
    }

    @Singleton
    @Provides
    fun provideMarvelComicsService(): MarvelComicsService {
        return MarvelComicsService()
    }
}