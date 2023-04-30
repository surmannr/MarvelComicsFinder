package hu.bme.aut.android.marvelcomicsfinder.domain.models

data class Events(
    val available: String,
    val collectionURI: String,
    val items: List<ItemXX>,
    val returned: String
)