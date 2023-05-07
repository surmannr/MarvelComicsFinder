package hu.bme.aut.android.marvelcomicsfinder.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.android.marvelcomicsfinder.data.repositories.FavouriteMarvelComicsRepository
import hu.bme.aut.android.marvelcomicsfinder.domain.usecases.favouritemarvelcomics.FavouriteMarvelComicsUseCases
import hu.bme.aut.android.marvelcomicsfinder.domain.usecases.marvelcomics.MarvelComicsUseCases
import hu.bme.aut.android.marvelcomicsfinder.network.MarvelComicsService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Singleton
    @Provides
    fun provideFavouriteMarvelComicsUseCases(marvelComicsRepository: FavouriteMarvelComicsRepository): FavouriteMarvelComicsUseCases {
        return FavouriteMarvelComicsUseCases(marvelComicsRepository)
    }

    @Singleton
    @Provides
    fun provideMarvelComicsUseCases(marvelComicsService: MarvelComicsService): MarvelComicsUseCases {
        return MarvelComicsUseCases(marvelComicsService)
    }
}