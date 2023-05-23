package hu.bme.aut.android.marvelcomicsfinder.data.entites

import androidx.room.Embedded

data class EventsEntity(
    val available: String?= "",
    val collectionURI: String?= "",
    val items: List<ItemXXEntity> = emptyList(),
    val returned: String?= "",
)