package hu.bme.aut.android.marvelcomicsfinder.data.entites

data class CharactersEntity(
    val available: String,
    val collectionURI: String,
    val items: List<ItemEntity>,
    val returned: String
)