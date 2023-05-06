package hu.bme.aut.android.marvelcomicsfinder.domain.models

import hu.bme.aut.android.marvelcomicsfinder.data.entites.ItemEntity

data class Item(
    val name: String,
    val resourceURI: String,
    val role: String
)

fun ItemEntity.asItem(): Item = Item(
    name = name,
    resourceURI = resourceURI,
    role = role
)

fun Item.asItemEntity(): ItemEntity = ItemEntity(
    name = name,
    resourceURI = resourceURI,
    role = role
)