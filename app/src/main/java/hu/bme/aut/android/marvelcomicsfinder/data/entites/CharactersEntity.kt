package hu.bme.aut.android.marvelcomicsfinder.data.entites

import androidx.room.Embedded

data class CharactersEntity(
    val available: String? = "",
    val collectionURI: String?= "",
    val items: List<ItemEntity> = emptyList(),
    val returned: String?= "",
)