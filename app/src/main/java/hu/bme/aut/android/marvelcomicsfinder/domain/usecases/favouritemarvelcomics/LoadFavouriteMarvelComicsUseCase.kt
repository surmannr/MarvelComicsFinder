package hu.bme.aut.android.marvelcomicsfinder.domain.usecases.favouritemarvelcomics

import hu.bme.aut.android.marvelcomicsfinder.data.repositories.FavouriteMarvelComicsRepository
import hu.bme.aut.android.marvelcomicsfinder.domain.models.PagedData

class LoadFavouriteMarvelComicsUseCase(private val marvelComicsRepository: FavouriteMarvelComicsRepository) {

    suspend operator fun invoke(): Result<PagedData> {
        throw NotImplementedError()
    }
}