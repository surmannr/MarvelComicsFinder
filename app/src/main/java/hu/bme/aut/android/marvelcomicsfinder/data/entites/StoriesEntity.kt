package hu.bme.aut.android.marvelcomicsfinder.data.entites

data class StoriesEntity(
    val available: String,
    val collectionURI: String,
    val items: List<ItemXXXEntity>,
    val returned: String
)