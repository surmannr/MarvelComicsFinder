package hu.bme.aut.android.marvelcomicsfinder.domain.models

import hu.bme.aut.android.marvelcomicsfinder.data.entites.SeriesEntity

data class Series(
    val name: String,
    val resourceURI: String
)

fun SeriesEntity.asSeries(): Series = Series(
    name = name ?: "",
    resourceURI = resourceURI ?: "",
)

fun Series.asSeriesEntity(): SeriesEntity = SeriesEntity(
    name = name,
    resourceURI = resourceURI
)