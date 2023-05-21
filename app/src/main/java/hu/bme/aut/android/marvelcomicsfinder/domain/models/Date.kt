package hu.bme.aut.android.marvelcomicsfinder.domain.models

import hu.bme.aut.android.marvelcomicsfinder.data.entites.DateEntity

data class Date(
    val date: String,
    val type: String
)

fun DateEntity.asDate(): Date = Date(
    date = date ?: "",
    type = type ?: "",
)

fun Date.asDateEntity(): DateEntity = DateEntity(
    date = date,
    type = type
)