package hu.bme.aut.android.marvelcomicsfinder.feature.favmarvelcomics.details

import hu.bme.aut.android.marvelcomicsfinder.domain.models.MarvelComics

class FavMarvelComicState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val isError: Boolean = error != null,
    val marvelComic: MarvelComics? = null
)