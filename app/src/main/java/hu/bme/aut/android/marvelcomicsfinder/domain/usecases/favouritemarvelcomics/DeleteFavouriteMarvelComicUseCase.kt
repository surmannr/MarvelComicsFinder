package hu.bme.aut.android.marvelcomicsfinder.domain.usecases.favouritemarvelcomics

import hu.bme.aut.android.marvelcomicsfinder.data.repositories.FavouriteMarvelComicsRepository

class DeleteFavouriteMarvelComicUseCase(private val marvelComicsRepository: FavouriteMarvelComicsRepository) {
    suspend operator fun invoke(id: String) {
        marvelComicsRepository.deleteMarvelComic(id)
    }
}