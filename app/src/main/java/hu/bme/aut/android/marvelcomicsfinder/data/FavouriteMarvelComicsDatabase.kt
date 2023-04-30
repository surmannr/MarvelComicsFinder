package hu.bme.aut.android.marvelcomicsfinder.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import hu.bme.aut.android.marvelcomicsfinder.data.converters.Converters
import hu.bme.aut.android.marvelcomicsfinder.data.dao.FavouriteMarvelComicsDao
import hu.bme.aut.android.marvelcomicsfinder.data.entites.FavouriteMarvelComicsEntity

@TypeConverters(Converters::class)
@Database(entities = [FavouriteMarvelComicsEntity::class], version = 2, exportSchema = false)
abstract class FavouriteMarvelComicsDatabase : RoomDatabase() {
    abstract val favouriteMarvelComicsDao: FavouriteMarvelComicsDao
}