package hu.bme.aut.android.marvelcomicsfinder.feature.favmarvelcomics.list

import hu.bme.aut.android.marvelcomicsfinder.data.entites.FavouriteMarvelComicsEntity
import hu.bme.aut.android.marvelcomicsfinder.domain.models.MarvelComics

data class FavMarvelComicsState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val isError: Boolean = error != null,
    val offset: String = "0",
    val limit: String = "20",
    val pageCount: Int = 1,
    val maxPageCount: Int = 1,
    val marvelComicsList: List<MarvelComics> = emptyList()
)