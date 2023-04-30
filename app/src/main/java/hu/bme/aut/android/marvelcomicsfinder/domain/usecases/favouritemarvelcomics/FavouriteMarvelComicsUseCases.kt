package hu.bme.aut.android.marvelcomicsfinder.domain.usecases.favouritemarvelcomics

import hu.bme.aut.android.marvelcomicsfinder.data.repositories.FavouriteMarvelComicsRepository

class FavouriteMarvelComicsUseCases(private val marvelComicsRepository: FavouriteMarvelComicsRepository) {
    val loadFavouriteMarvelComic = LoadFavouriteMarvelComicUseCase(marvelComicsRepository)
    val loadFavouriteMarvelComics = LoadFavouriteMarvelComicsUseCase(marvelComicsRepository)
    val saveFavouriteMarvelComics = SaveFavouriteMarvelComicUseCase(marvelComicsRepository)
    val deleteFavouriteMarvelComics = DeleteFavouriteMarvelComicUseCase(marvelComicsRepository)
}