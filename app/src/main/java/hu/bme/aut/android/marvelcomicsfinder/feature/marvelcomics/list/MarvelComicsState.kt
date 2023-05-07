package hu.bme.aut.android.marvelcomicsfinder.feature.marvelcomics.list

import hu.bme.aut.android.marvelcomicsfinder.domain.models.MarvelComics
import hu.bme.aut.android.marvelcomicsfinder.domain.models.PagedData

data class MarvelComicsState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val isError: Boolean = error != null,
    val offset: String = "0",
    val limit: String = "20",
    val marvelComicsList: List<MarvelComics> = emptyList()
)