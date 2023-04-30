package hu.bme.aut.android.marvelcomicsfinder.domain.usecases.favouritemarvelcomics

import hu.bme.aut.android.marvelcomicsfinder.data.repositories.FavouriteMarvelComicsRepository
import hu.bme.aut.android.marvelcomicsfinder.domain.models.MarvelComics

class SaveFavouriteMarvelComicUseCase(private val marvelComicsRepository: FavouriteMarvelComicsRepository) {
    suspend operator fun invoke(marvelComics: MarvelComics) {
        throw NotImplementedError()
    }
}