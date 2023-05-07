package hu.bme.aut.android.marvelcomicsfinder.data.repositories

import hu.bme.aut.android.marvelcomicsfinder.data.dao.FavouriteMarvelComicsDao
import hu.bme.aut.android.marvelcomicsfinder.data.entites.FavouriteMarvelComicsEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavouriteMarvelComicsRepositoryImp @Inject constructor(private val favouriteMarvelComicsDao: FavouriteMarvelComicsDao) : FavouriteMarvelComicsRepository {
    override suspend fun insertMarvelComic(entity :FavouriteMarvelComicsEntity) {
        favouriteMarvelComicsDao.insertMarvelComic(entity)
    }

    override suspend fun deleteMarvelComic(id: String) {
        favouriteMarvelComicsDao.deleteMarvelComic(id)
    }

    override fun getAllMarvelComics(
        titleStartWith: String?,
        startYear: String?
    ): Flow<List<FavouriteMarvelComicsEntity>> = favouriteMarvelComicsDao.getAllMarvelComics(titleStartWith, startYear)

    override fun getMarvelComicById(id: String): Flow<FavouriteMarvelComicsEntity> = favouriteMarvelComicsDao.getMarvelComicById(id)
}