package hu.bme.aut.android.marvelcomicsfinder.domain.usecases.marvelcomics

import hu.bme.aut.android.marvelcomicsfinder.domain.models.MarvelComics
import hu.bme.aut.android.marvelcomicsfinder.network.MarvelComicsService

class LoadMarvelComicUseCase(private val marvelComicsService: MarvelComicsService) {

    suspend operator fun invoke(): Result<MarvelComics> {
        throw NotImplementedError()
    }
}