package hu.bme.aut.android.marvelcomicsfinder.data.models

data class Data(
    val count: String,
    val limit: String,
    val offset: String,
    val results: List<Result>,
    val total: String
)