package hu.bme.aut.android.marvelcomicsfinder.network

import hu.bme.aut.android.marvelcomicsfinder.domain.models.MarvelResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelComicsServiceImpl : MarvelComicsService{
    @GET("v1/public/comics")
    override suspend fun getMarvelComics(
        @Query("titleStartsWith") titleStartWith: String?,
        @Query("startYear") startYear: String?,
        @Query("offset") offset: String,
        @Query("limit") limit: String
    ): MarvelResponse

    @GET("v1/public/comics/{comicId}")
    override suspend fun getMarvelComicById(@Path("comicId") comicId: String): MarvelResponse

}