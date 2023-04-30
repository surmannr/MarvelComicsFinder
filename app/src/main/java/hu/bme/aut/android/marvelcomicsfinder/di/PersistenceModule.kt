package hu.bme.aut.android.marvelcomicsfinder.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.android.marvelcomicsfinder.data.FavouriteMarvelComicsDatabase
import hu.bme.aut.android.marvelcomicsfinder.data.repositories.FavouriteMarvelComicsRepository
import hu.bme.aut.android.marvelcomicsfinder.data.repositories.FavouriteMarvelComicsRepositoryImp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Singleton
    @Provides
    fun bindMarvelComicsRepository(
        marvelComicsDb: FavouriteMarvelComicsDatabase
    ): FavouriteMarvelComicsRepository {
        return FavouriteMarvelComicsRepositoryImp(marvelComicsDb.favouriteMarvelComicsDao)
    }


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): FavouriteMarvelComicsDatabase {
        return Room.databaseBuilder(
            appContext,
            FavouriteMarvelComicsDatabase::class.java,
            "favourite_marvelcomics"
        ).build()
    }
}