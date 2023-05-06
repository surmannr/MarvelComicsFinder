package hu.bme.aut.android.marvelcomicsfinder.domain.usecases.marvelcomics

import hu.bme.aut.android.marvelcomicsfinder.domain.models.MarvelComics
import hu.bme.aut.android.marvelcomicsfinder.domain.models.PagedData
import hu.bme.aut.android.marvelcomicsfinder.network.MarvelComicsService
import java.io.IOException

class LoadMarvelComicUseCase(private val marvelComicsService: MarvelComicsService) {

    suspend operator fun invoke(id: String): Result<PagedData?> {
        return try {
            val marvelComics = marvelComicsService.getMarvelComicById(id).body()
            Result.success(marvelComics)
        } catch (e: IOException) {
            Result.failure(e)
        }
    }
}