package hu.bme.aut.android.marvelcomicsfinder.data.repositories

import hu.bme.aut.android.marvelcomicsfinder.data.entites.FavouriteMarvelComicsEntity
import kotlinx.coroutines.flow.Flow

interface FavouriteMarvelComicsRepository {
    suspend fun insertMarvelComic(entity :FavouriteMarvelComicsEntity)
    suspend fun deleteMarvelComic(id: String)
    fun getAllMarvelComics(titleStartWith: String?, startYear: String?): Flow<List<FavouriteMarvelComicsEntity>>
    fun getMarvelComicById(id: String): Flow<FavouriteMarvelComicsEntity>
}