package hu.bme.aut.android.marvelcomicsfinder.domain.models

import hu.bme.aut.android.marvelcomicsfinder.data.entites.CreatorsEntity

data class Creators(
    val available: String,
    val collectionURI: String,
    val items: List<Item>,
    val returned: String
)

fun CreatorsEntity.asCreators(): Creators = Creators(
    available = available ?: "",
    collectionURI = collectionURI ?: "",
    items = items.map { it -> it.asItem() },
    returned = returned ?: "",
)

fun Creators.asCreatorsEntity(): CreatorsEntity = CreatorsEntity(
    available = available,
    collectionURI = collectionURI,
    items = items.map { it -> it.asItemEntity() },
    returned = returned
)