package hu.bme.aut.android.marvelcomicsfinder.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hu.bme.aut.android.marvelcomicsfinder.data.entites.FavouriteMarvelComicsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteMarvelComicsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMarvelComic()

    @Query("DELETE FROM favourite_marvelcomics WHERE id = :id")
    suspend fun deleteMarvelComic(id: String)

    @Query(
        "SELECT * FROM favourite_marvelcomics" +
        " WHERE (:titleStartWith IS NULL OR title LIKE :titleStartWith || '%')" +
        " AND (:startYear IS NULL OR title LIKE :startYear )")
    fun getAllMarvelComics(titleStartWith: String?, startYear: String?): Flow<List<FavouriteMarvelComicsEntity>>

    @Query("SELECT * FROM favourite_marvelcomics WHERE id = :id")
    fun getMarvelComicById(id: String): Flow<FavouriteMarvelComicsEntity>
}