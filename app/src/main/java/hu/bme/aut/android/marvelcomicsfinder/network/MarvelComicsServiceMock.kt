package hu.bme.aut.android.marvelcomicsfinder.network

import hu.bme.aut.android.marvelcomicsfinder.domain.models.MarvelResponse
import retrofit2.Call

class MarvelComicsServiceMock : MarvelComicsService {
    override suspend fun getMarvelComics(
        titleStartWith: String?,
        startYear: String?,
        offset: String,
        limit: String
    ): MarvelResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getMarvelComicById(id: String): MarvelResponse {
        TODO("Not yet implemented")
    }
}