package hu.bme.aut.android.marvelcomicsfinder.data.repositories

import hu.bme.aut.android.marvelcomicsfinder.data.dao.FavouriteMarvelComicsDao
import javax.inject.Inject

class FavouriteMarvelComicsRepositoryImp @Inject constructor(private val favouriteMarvelComicsDao: FavouriteMarvelComicsDao) : FavouriteMarvelComicsRepository {
}