package hu.bme.aut.android.marvelcomicsfinder.domain.usecases.favouritemarvelcomics

import hu.bme.aut.android.marvelcomicsfinder.data.repositories.FavouriteMarvelComicsRepository
import hu.bme.aut.android.marvelcomicsfinder.domain.models.MarvelComics

class LoadFavouriteMarvelComicUseCase(private val marvelComicsRepository: FavouriteMarvelComicsRepository) {
    suspend operator fun invoke(): Result<MarvelComics> {
        throw NotImplementedError()
    }
}