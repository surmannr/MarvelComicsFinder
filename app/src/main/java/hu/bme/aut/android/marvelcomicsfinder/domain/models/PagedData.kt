package hu.bme.aut.android.marvelcomicsfinder.domain.models

data class PagedData(
    val count: String,
    val limit: String,
    val offset: String,
    val results: List<MarvelComics>,
    val total: String
)