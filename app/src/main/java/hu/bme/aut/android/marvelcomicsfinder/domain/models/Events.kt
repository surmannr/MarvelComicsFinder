package hu.bme.aut.android.marvelcomicsfinder.domain.models

import hu.bme.aut.android.marvelcomicsfinder.data.entites.EventsEntity

data class Events(
    val available: String,
    val collectionURI: String,
    val items: List<ItemXX>,
    val returned: String
)

fun EventsEntity.asEvents(): Events = Events(
    available = available,
    collectionURI = collectionURI,
    items = items.map { it -> it.asItemXX() },
    returned = returned
)

fun Events.asEventsEntity(): EventsEntity = EventsEntity(
    available = available,
    collectionURI = collectionURI,
    items = items.map { it -> it.asItemXXEntity() },
    returned = returned
)