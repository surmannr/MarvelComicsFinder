package hu.bme.aut.android.marvelcomicsfinder.domain.models

import hu.bme.aut.android.marvelcomicsfinder.data.entites.ItemXXXEntity

data class ItemXXX(
    val name: String,
    val resourceURI: String,
    val type: String
)

fun ItemXXXEntity.asItemXXX(): ItemXXX = ItemXXX(
    name = name,
    resourceURI = resourceURI,
    type = type
)

fun ItemXXX.asItemXXXEntity(): ItemXXXEntity = ItemXXXEntity(
    name = name,
    resourceURI = resourceURI,
    type = type
)