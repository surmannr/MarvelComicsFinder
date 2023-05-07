package hu.bme.aut.android.marvelcomicsfinder.domain.usecases.marvelcomics

import android.util.Log
import hu.bme.aut.android.marvelcomicsfinder.domain.models.MarvelResponse
import hu.bme.aut.android.marvelcomicsfinder.domain.models.PagedData
import hu.bme.aut.android.marvelcomicsfinder.domain.models.asFavouriteMarvelComicsEntity
import hu.bme.aut.android.marvelcomicsfinder.network.MarvelComicsService
import kotlinx.coroutines.flow.first
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class LoadMarvelComicsUseCase(private val marvelComicsService: MarvelComicsService) {

    suspend operator fun invoke(titleStartWith: String?, startYear: String?, limit: String, offset: String): PagedData? {
        return marvelComicsService.getMarvelComics(titleStartWith = titleStartWith, startYear = startYear, limit = limit, offset = offset).data
    }
}