package hu.bme.aut.android.marvelcomicsfinder.domain.models

import hu.bme.aut.android.marvelcomicsfinder.data.entites.StoriesEntity

data class Stories(
    val available: String,
    val collectionURI: String,
    val items: List<ItemXXX>,
    val returned: String
)

fun StoriesEntity.asStories(): Stories = Stories(
    available = available ?: "",
    collectionURI = collectionURI ?: "",
    items = items.map { it -> it.asItemXXX() },
    returned = returned ?: "",
)

fun Stories.asStoriesEntity(): StoriesEntity = StoriesEntity(
    available = available,
    collectionURI = collectionURI,
    items = items.map { it -> it.asItemXXXEntity() },
    returned = returned
)