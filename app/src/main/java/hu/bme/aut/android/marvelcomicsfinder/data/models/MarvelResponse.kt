package hu.bme.aut.android.marvelcomicsfinder.data.models

data class MarvelResponse(
    val attributionHTML: String,
    val attributionText: String,
    val code: String,
    val copyright: String,
    val `data`: Data,
    val etag: String,
    val status: String
)