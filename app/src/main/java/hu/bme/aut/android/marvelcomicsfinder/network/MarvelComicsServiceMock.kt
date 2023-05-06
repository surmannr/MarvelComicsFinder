package hu.bme.aut.android.marvelcomicsfinder.network

import hu.bme.aut.android.marvelcomicsfinder.domain.models.MarvelComics
import hu.bme.aut.android.marvelcomicsfinder.domain.models.PagedData
import retrofit2.Response

class MarvelComicsServiceMock : MarvelComicsService {
    override suspend fun getMarvelComics(
        titleStartWith: String?,
        startYear: String?,
        offset: String,
        limit: String
    ): Response<PagedData> {
        TODO("Not yet implemented")
    }

    override suspend fun getMarvelComicById(id: String): Response<PagedData> {
        TODO("Not yet implemented")
    }
}