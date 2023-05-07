package hu.bme.aut.android.marvelcomicsfinder.network

import hu.bme.aut.android.marvelcomicsfinder.domain.models.MarvelResponse
import retrofit2.Call

interface MarvelComicsService  {
    suspend fun getMarvelComics(titleStartWith: String?, startYear: String?, offset: String, limit: String): MarvelResponse
    suspend fun getMarvelComicById(id: String): MarvelResponse
}