package hu.bme.aut.android.marvelcomicsfinder.data.models

data class Characters(
    val available: String,
    val collectionURI: String,
    val items: List<Item>,
    val returned: String
)