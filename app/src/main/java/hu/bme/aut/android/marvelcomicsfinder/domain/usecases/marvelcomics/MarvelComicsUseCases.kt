package hu.bme.aut.android.marvelcomicsfinder.domain.usecases.marvelcomics

import hu.bme.aut.android.marvelcomicsfinder.network.MarvelComicsService
import javax.inject.Inject

class MarvelComicsUseCases @Inject constructor(private val marvelComicsService: MarvelComicsService) {
    val loadMarvelComic = LoadMarvelComicUseCase(marvelComicsService)
    val loadMarvelComics = LoadMarvelComicsUseCase(marvelComicsService)
}