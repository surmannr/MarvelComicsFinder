package hu.bme.aut.android.marvelcomicsfinder.domain.models

import hu.bme.aut.android.marvelcomicsfinder.data.entites.CharactersEntity

data class Characters(
    val available: String,
    val collectionURI: String,
    val items: List<Item>,
    val returned: String
)

fun CharactersEntity.asCharacters(): Characters = Characters(
    available = available ?: "",
    collectionURI = collectionURI?: "",
    items = items.map { it -> it.asItem() },
    returned = returned ?: "",
)

fun Characters.asCharactersEntity(): CharactersEntity = CharactersEntity(
    available = available,
    collectionURI = collectionURI,
    items = items.map { it -> it.asItemEntity() },
    returned = returned
)