package hu.bme.aut.android.marvelcomicsfinder.feature.favmarvelcomics.random

import hu.bme.aut.android.marvelcomicsfinder.domain.models.MarvelComics

class RandomFavMarvelComicState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val isError: Boolean = error != null,
    val randomMarvelComic: MarvelComics? = null
)