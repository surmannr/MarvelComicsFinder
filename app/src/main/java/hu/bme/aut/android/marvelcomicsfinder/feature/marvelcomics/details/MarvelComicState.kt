package hu.bme.aut.android.marvelcomicsfinder.feature.marvelcomics.details

import hu.bme.aut.android.marvelcomicsfinder.domain.models.MarvelComics

data class MarvelComicState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val isError: Boolean = error != null,
    val marvelComic: MarvelComics? = null,
    val isInFavouriteList: Boolean = false,
)