package hu.bme.aut.android.marvelcomicsfinder.domain.models

import hu.bme.aut.android.marvelcomicsfinder.data.entites.UrlEntity

data class Url(
    val type: String,
    val url: String
)

fun UrlEntity.asUrl(): Url = Url(
    type = type ?: "",
    url = url ?: "",
)

fun Url.asUrlEntity(): UrlEntity = UrlEntity(
    type = type,
    url = url
)