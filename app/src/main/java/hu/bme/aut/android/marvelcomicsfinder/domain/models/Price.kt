package hu.bme.aut.android.marvelcomicsfinder.domain.models

import hu.bme.aut.android.marvelcomicsfinder.data.entites.PriceEntity

data class Price(
    val price: String,
    val type: String
)

fun PriceEntity.asPrice(): Price = Price(
    price = price,
    type = type
)

fun Price.asPriceEntity(): PriceEntity = PriceEntity(
    price = price,
    type = type
)