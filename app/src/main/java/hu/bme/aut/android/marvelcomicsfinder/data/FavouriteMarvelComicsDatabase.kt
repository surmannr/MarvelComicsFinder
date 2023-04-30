package hu.bme.aut.android.marvelcomicsfinder.data

import hu.bme.aut.android.marvelcomicsfinder.data.dao.FavouriteMarvelComicsDao

abstract class FavouriteMarvelComicsDatabase {
    abstract val favouriteMarvelComicsDao: FavouriteMarvelComicsDao
}