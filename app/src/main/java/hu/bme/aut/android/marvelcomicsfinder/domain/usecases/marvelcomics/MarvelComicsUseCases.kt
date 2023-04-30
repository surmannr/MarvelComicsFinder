package hu.bme.aut.android.marvelcomicsfinder.domain.usecases.marvelcomics

import hu.bme.aut.android.marvelcomicsfinder.network.MarvelComicsService

class MarvelComicsUseCases(private val marvelComicsService: MarvelComicsService) {
    val loadMarvelComic = LoadMarvelComicUseCase(marvelComicsService)
    val loadMarvelComics = LoadMarvelComicsUseCase(marvelComicsService)
}