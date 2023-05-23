package hu.bme.aut.android.marvelcomicsfinder

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import hu.bme.aut.android.marvelcomicsfinder.data.FavouriteMarvelComicsDatabase
import hu.bme.aut.android.marvelcomicsfinder.data.dao.FavouriteMarvelComicsDao
import hu.bme.aut.android.marvelcomicsfinder.data.repositories.FavouriteMarvelComicsRepository
import hu.bme.aut.android.marvelcomicsfinder.data.repositories.FavouriteMarvelComicsRepositoryImp
import hu.bme.aut.android.marvelcomicsfinder.domain.models.Characters
import hu.bme.aut.android.marvelcomicsfinder.domain.models.Creators
import hu.bme.aut.android.marvelcomicsfinder.domain.models.Events
import hu.bme.aut.android.marvelcomicsfinder.domain.models.MarvelComics
import hu.bme.aut.android.marvelcomicsfinder.domain.models.Series
import hu.bme.aut.android.marvelcomicsfinder.domain.models.Stories
import hu.bme.aut.android.marvelcomicsfinder.domain.models.Thumbnail
import hu.bme.aut.android.marvelcomicsfinder.domain.models.asFavouriteMarvelComicsEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DatabaseUnitTest {
    private lateinit var database: FavouriteMarvelComicsDatabase
    private lateinit var dao: FavouriteMarvelComicsDao
    private lateinit var repository: FavouriteMarvelComicsRepository
    @Before
    fun setupDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            FavouriteMarvelComicsDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.favouriteMarvelComicsDao

        repository = FavouriteMarvelComicsRepositoryImp(dao)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun testLoadEmptyListFromDatabase() = runTest  {
        // Arrange

        // Act
        val result = repository.getAllMarvelComics(null, null).first()

        // Assert
        Assert.assertEquals(result.size, 0)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun testLoadListFromDatabase() = runTest  {
        // Arrange
        val list: List<MarvelComics> = mutableListOf(m1, m2)
        dao.insertMarvelComic(m1.asFavouriteMarvelComicsEntity())
        dao.insertMarvelComic(m2.asFavouriteMarvelComicsEntity())

        // Act
        val result = repository.getAllMarvelComics(null, null).first()

        // Assert
        Assert.assertEquals(result.size, list.size)
        Assert.assertEquals(result.contains(m1.asFavouriteMarvelComicsEntity()), list.contains(m1))
        Assert.assertEquals(result.contains(m2.asFavouriteMarvelComicsEntity()), list.contains(m2))
    }

    @Test
    @ExperimentalCoroutinesApi
    fun testLoadByIdFromDatabase() = runTest  {
        // Arrange
        val list: List<MarvelComics> = mutableListOf(m1, m2)
        dao.insertMarvelComic(m1.asFavouriteMarvelComicsEntity())
        dao.insertMarvelComic(m2.asFavouriteMarvelComicsEntity())
        val id = "id1"

        // Act
        val result = repository.getMarvelComicById(id).first()

        // Assert
        Assert.assertEquals(result.id, id)
        Assert.assertEquals(result.title, m1.title)
        Assert.assertEquals(result.description, m1.description)
        Assert.assertEquals(result.format, m1.format)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun testInsertToDatabase() = runTest  {
        // Arrange
        val id = m1.id

        // Act
        repository.insertMarvelComic(m1.asFavouriteMarvelComicsEntity())
        val result = repository.getMarvelComicById(id).first()

        // Assert
        Assert.assertEquals(result.id, id)
        Assert.assertEquals(result.title, m1.title)
        Assert.assertEquals(result.description, m1.description)
        Assert.assertEquals(result.format, m1.format)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun testDeleteFromDatabase() = runTest  {
        // Arrange
        val id = m1.id
        dao.insertMarvelComic(m1.asFavouriteMarvelComicsEntity())

        // Act
        repository.deleteMarvelComic(id)
        val result = repository.getMarvelComicById(id).firstOrNull()

        // Assert
        Assert.assertNull(result)
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    private val m1: MarvelComics = MarvelComics(
        characters = Characters("", "", emptyList(), ""),
        collectedIssues = emptyList(),
        collections = emptyList(),
        creators = Creators("", "", emptyList(), ""),
        dates = emptyList(),
        description = "M1 DESCRIPTION",
        diamondCode = "",
        digitalId = "",
        ean = "",
        events = Events("", "", emptyList(), ""),
        format = "Comic",
        id = "id1",
        images = emptyList(),
        isbn = "",
        issn = "",
        issueNumber = "",
        modified = "2000",
        pageCount = "",
        prices = emptyList(),
        resourceURI = "",
        series = Series("", ""),
        stories = Stories("", "", emptyList(), ""),
        textObjects = emptyList(),
        thumbnail = Thumbnail("", ""),
        title = "M1 TITLE",
        upc = "",
        urls = emptyList(),
        variantDescription = "",
        variants = emptyList()
    )

    private val m2: MarvelComics = MarvelComics(
        characters = Characters("", "", emptyList(), ""),
        collectedIssues = emptyList(),
        collections = emptyList(),
        creators = Creators("", "", emptyList(), ""),
        dates = emptyList(),
        description = "M2 DESCRIPTION",
        diamondCode = "",
        digitalId = "",
        ean = "",
        events = Events("", "", emptyList(), ""),
        format = "Series",
        id = "id2",
        images = emptyList(),
        isbn = "",
        issn = "",
        issueNumber = "",
        modified = "2005",
        pageCount = "",
        prices = emptyList(),
        resourceURI = "",
        series = Series("", ""),
        stories = Stories("", "", emptyList(), ""),
        textObjects = emptyList(),
        thumbnail = Thumbnail("", ""),
        title = "M2 TITLE",
        upc = "",
        urls = emptyList(),
        variantDescription = "",
        variants = emptyList()
    )
}