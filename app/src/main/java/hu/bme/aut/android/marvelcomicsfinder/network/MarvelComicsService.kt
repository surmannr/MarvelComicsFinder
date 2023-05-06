package hu.bme.aut.android.marvelcomicsfinder.network

import hu.bme.aut.android.marvelcomicsfinder.domain.models.MarvelComics
import hu.bme.aut.android.marvelcomicsfinder.domain.models.PagedData
import retrofit2.Response

interface MarvelComicsService  {
    suspend fun getMarvelComics(titleStartWith: String?, startYear: String?, offset: String, limit: String): Response<PagedData>
    suspend fun getMarvelComicById(id: String): Response<MarvelComics>
}