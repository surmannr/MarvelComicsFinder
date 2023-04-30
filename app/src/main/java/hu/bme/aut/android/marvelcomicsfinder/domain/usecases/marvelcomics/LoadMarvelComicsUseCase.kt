package hu.bme.aut.android.marvelcomicsfinder.domain.usecases.marvelcomics

import hu.bme.aut.android.marvelcomicsfinder.domain.models.PagedData
import hu.bme.aut.android.marvelcomicsfinder.network.MarvelComicsService

class LoadMarvelComicsUseCase(private val marvelComicsService: MarvelComicsService) {

    suspend operator fun invoke(): Result<PagedData> {
        throw NotImplementedError()
    }
}