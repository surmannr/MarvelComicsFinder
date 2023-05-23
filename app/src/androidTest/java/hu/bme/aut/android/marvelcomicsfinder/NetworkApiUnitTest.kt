package hu.bme.aut.android.marvelcomicsfinder

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import hu.bme.aut.android.marvelcomicsfinder.network.MarvelComicsService
import hu.bme.aut.android.marvelcomicsfinder.network.MarvelComicsServiceMock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class NetworkApiUnitTest {

    private lateinit var service: MarvelComicsService;

    @Before
    fun setUp() {
        service = MarvelComicsServiceMock()
    }

    @Test
    @ExperimentalCoroutinesApi
    fun testLoadByIdFromService() = runTest  {
        // Arrange
        val id = "id1"
        val title = "M1 TITLE"
        val description = "M1 DESCRIPTION"
        val format = "Comic"
        val status = "Ok"

        // Act
        val response = service.getMarvelComicById(id)

        // Assert
        assertEquals(status, response.status)
        assertEquals(id, response.data.results[0].id)
        assertEquals(title, response.data.results[0].title)
        assertEquals(description, response.data.results[0].description)
        assertEquals(format, response.data.results[0].format)
        assertEquals(response.data.results.size, 1)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun testLoadListFromService() = runTest  {
        // Arrange
        val count = "2"
        val status = "Ok"

        // Act
        val response = service.getMarvelComics(null, null, "", "")

        // Assert
        assertEquals(status, response.status)
        assertEquals(response.data.results.size, count.toInt())
        assertEquals(response.data.total, count)
        assertEquals(response.data.count, count)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun testLoadListFilteredByTitleFromService() = runTest  {
        // Arrange
        val count = "1"
        val status = "Ok"
        val titleStartWith = "M1"
        val title = "M1 TITLE"

        // Act
        val response = service.getMarvelComics(titleStartWith, null, "", "")

        // Assert
        assertEquals(status, response.status)
        assertEquals(response.data.results.size, count.toInt())
        assertEquals(response.data.total, count)
        assertEquals(response.data.count, count)
        assertEquals(title, response.data.results[0].title)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun testLoadListFilteredByYearFromService() = runTest  {
        // Arrange
        val count = "1"
        val status = "Ok"
        val startYear = "2005"
        val title = "M2 TITLE"

        // Act
        val response = service.getMarvelComics(null, startYear, "", "")

        // Assert
        assertEquals(status, response.status)
        assertEquals(response.data.results.size, count.toInt())
        assertEquals(response.data.total, count)
        assertEquals(response.data.count, count)
        assertEquals(title, response.data.results[0].title)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun testLoadListFilteredByTitleAndYearFromService() = runTest  {
        // Arrange
        val count = "0"
        val status = "Ok"
        val startYear = "2005"
        val titleStartWith = "M1"

        // Act
        val response = service.getMarvelComics(titleStartWith, startYear, "", "")

        // Assert
        assertEquals(status, response.status)
        assertEquals(response.data.results.size, count.toInt())
        assertEquals(response.data.total, count)
        assertEquals(response.data.count, count)
        assertEquals(response.data.results.size, count.toInt())
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("hu.bme.aut.android.marvelcomicsfinder", appContext.packageName)
    }
}