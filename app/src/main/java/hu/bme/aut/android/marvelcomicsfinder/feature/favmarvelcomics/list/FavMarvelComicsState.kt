package hu.bme.aut.android.marvelcomicsfinder.feature.favmarvelcomics.list

import hu.bme.aut.android.marvelcomicsfinder.domain.models.PagedData

data class FavMarvelComicsState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val isError: Boolean = error != null,
    val marvelComicsList: PagedData = PagedData(count = "0", offset = "0", results = emptyList(), limit = "0", total = "0")
)