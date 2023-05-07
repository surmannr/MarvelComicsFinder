package hu.bme.aut.android.marvelcomicsfinder.domain.models

import hu.bme.aut.android.marvelcomicsfinder.data.entites.CollectionEntity

data class Collection(
    val name: String,
    val resourceURI: String
)

fun CollectionEntity.asCollection(): Collection = Collection(
    name = name,
    resourceURI = resourceURI
)

fun Collection.asCollectionEntity(): CollectionEntity = CollectionEntity(
    name = name,
    resourceURI = resourceURI
)