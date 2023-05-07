package hu.bme.aut.android.marvelcomicsfinder.domain.models

import hu.bme.aut.android.marvelcomicsfinder.data.entites.ItemXXEntity

data class ItemXX(
    val name: String,
    val resourceURI: String
)

fun ItemXXEntity.asItemXX(): ItemXX = ItemXX(
    name = name,
    resourceURI = resourceURI
)

fun ItemXX.asItemXXEntity(): ItemXXEntity = ItemXXEntity(
    name = name,
    resourceURI = resourceURI
)