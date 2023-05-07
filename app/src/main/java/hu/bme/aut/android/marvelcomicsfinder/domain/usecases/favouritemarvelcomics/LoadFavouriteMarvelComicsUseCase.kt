package hu.bme.aut.android.marvelcomicsfinder.domain.usecases.favouritemarvelcomics

import hu.bme.aut.android.marvelcomicsfinder.data.repositories.FavouriteMarvelComicsRepository
import hu.bme.aut.android.marvelcomicsfinder.domain.models.PagedData
import hu.bme.aut.android.marvelcomicsfinder.domain.models.asFavouriteMarvelComicsEntity
import kotlinx.coroutines.flow.first
import java.io.IOException

class LoadFavouriteMarvelComicsUseCase(private val marvelComicsRepository: FavouriteMarvelComicsRepository) {

    suspend operator fun invoke(titleStartWith: String?, startYear: String?, limit: String, offset: String,pageNumber: Int): Result<PagedData> {
        return try {
            val marvelComics = marvelComicsRepository.getAllMarvelComics(titleStartWith, startYear).first()
            Result.success(PagedData(
                count = limit,
                limit = limit,
                offset = offset,
                total = marvelComics.count().toString(),
                results = marvelComics.map { it.asFavouriteMarvelComicsEntity() }.drop(pageNumber * offset.toInt()).take(limit.toInt())
            ))
        } catch (e: IOException) {
            Result.failure(e)
        }
    }
}