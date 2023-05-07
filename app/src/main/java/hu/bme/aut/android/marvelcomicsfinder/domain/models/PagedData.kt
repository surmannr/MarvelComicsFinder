package hu.bme.aut.android.marvelcomicsfinder.domain.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PagedData(
    @SerializedName("count")
    @Expose
    val count: String,
    @SerializedName("limit")
    @Expose
    val limit: String,
    @SerializedName("offset")
    @Expose
    val offset: String,
    @SerializedName("results")
    @Expose
    val results: List<MarvelComics>,
    @SerializedName("total")
    @Expose
    val total: String
)