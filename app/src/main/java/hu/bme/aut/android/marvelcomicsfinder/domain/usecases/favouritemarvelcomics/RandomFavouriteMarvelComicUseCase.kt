package hu.bme.aut.android.marvelcomicsfinder.domain.usecases.favouritemarvelcomics

import hu.bme.aut.android.marvelcomicsfinder.data.repositories.FavouriteMarvelComicsRepository
import hu.bme.aut.android.marvelcomicsfinder.domain.models.MarvelComics
import hu.bme.aut.android.marvelcomicsfinder.domain.models.asFavouriteMarvelComicsEntity
import kotlinx.coroutines.flow.first
import java.io.IOException

class RandomFavouriteMarvelComicUseCase(private val marvelComicsRepository: FavouriteMarvelComicsRepository) {

    suspend operator fun invoke(): Result<MarvelComics> {
        return try {
            val marvelComics = marvelComicsRepository.getAllMarvelComics(null, null).first()
            Result.success(
                marvelComics.random().asFavouriteMarvelComicsEntity()
            )
        } catch (e: IOException) {
            Result.failure(e)
        }
    }
}