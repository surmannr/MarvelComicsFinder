package hu.bme.aut.android.marvelcomicsfinder.data.entites

import androidx.room.Embedded

data class StoriesEntity(
    val available: String?= "",
    val collectionURI: String?= "",
    val items: List<ItemXXXEntity> = emptyList(),
    val returned: String?= "",
)