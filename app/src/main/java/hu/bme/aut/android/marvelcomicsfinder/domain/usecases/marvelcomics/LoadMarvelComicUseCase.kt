package hu.bme.aut.android.marvelcomicsfinder.domain.usecases.marvelcomics

import android.util.Log
import hu.bme.aut.android.marvelcomicsfinder.domain.models.MarvelResponse
import hu.bme.aut.android.marvelcomicsfinder.domain.models.PagedData
import hu.bme.aut.android.marvelcomicsfinder.network.MarvelComicsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class LoadMarvelComicUseCase(private val marvelComicsService: MarvelComicsService) {

    suspend operator fun invoke(id: String): PagedData? {
        return marvelComicsService.getMarvelComicById(id).data
    }
}