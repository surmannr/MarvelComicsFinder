package hu.bme.aut.android.marvelcomicsfinder.domain.usecases.favouritemarvelcomics

import hu.bme.aut.android.marvelcomicsfinder.data.repositories.FavouriteMarvelComicsRepository
import hu.bme.aut.android.marvelcomicsfinder.domain.models.MarvelComics
import hu.bme.aut.android.marvelcomicsfinder.domain.models.asFavouriteMarvelComicsEntity
import kotlinx.coroutines.flow.first
import java.io.IOException

class LoadFavouriteMarvelComicUseCase(private val marvelComicsRepository: FavouriteMarvelComicsRepository) {
    suspend operator fun invoke(id: String): Result<MarvelComics> {
        return try {
            Result.success(marvelComicsRepository.getMarvelComicById(id).first().asFavouriteMarvelComicsEntity())
        } catch (e: IOException) {
            Result.failure(e)
        }
    }
}