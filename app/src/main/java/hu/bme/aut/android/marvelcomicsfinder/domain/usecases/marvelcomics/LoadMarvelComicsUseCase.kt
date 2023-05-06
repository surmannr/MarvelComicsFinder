package hu.bme.aut.android.marvelcomicsfinder.domain.usecases.marvelcomics

import hu.bme.aut.android.marvelcomicsfinder.domain.models.PagedData
import hu.bme.aut.android.marvelcomicsfinder.domain.models.asFavouriteMarvelComicsEntity
import hu.bme.aut.android.marvelcomicsfinder.network.MarvelComicsService
import kotlinx.coroutines.flow.first
import java.io.IOException

class LoadMarvelComicsUseCase(private val marvelComicsService: MarvelComicsService) {

    suspend operator fun invoke(titleStartWith: String?, startYear: String?, limit: String, offset: String): Result<PagedData?> {
        return try {
            val marvelComics = marvelComicsService.getMarvelComics(
                titleStartWith = titleStartWith, startYear = startYear, limit = limit, offset = offset).body()
            Result.success(marvelComics)
        } catch (e: IOException) {
            Result.failure(e)
        }
    }
}