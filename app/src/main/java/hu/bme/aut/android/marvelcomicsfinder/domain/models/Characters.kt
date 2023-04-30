package hu.bme.aut.android.marvelcomicsfinder.domain.models

data class Characters(
    val available: String,
    val collectionURI: String,
    val items: List<Item>,
    val returned: String
)