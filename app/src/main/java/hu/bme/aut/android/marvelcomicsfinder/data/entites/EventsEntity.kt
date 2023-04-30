package hu.bme.aut.android.marvelcomicsfinder.data.entites

data class EventsEntity(
    val available: String,
    val collectionURI: String,
    val items: List<ItemXXEntity>,
    val returned: String
)