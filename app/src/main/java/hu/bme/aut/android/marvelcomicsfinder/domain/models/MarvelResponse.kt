package hu.bme.aut.android.marvelcomicsfinder.domain.models

data class MarvelResponse(
    val attributionHTML: String,
    val attributionText: String,
    val code: String,
    val copyright: String,
    val `data`: PagedData,
    val etag: String,
    val status: String
)