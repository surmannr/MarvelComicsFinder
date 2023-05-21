package hu.bme.aut.android.marvelcomicsfinder.network

import hu.bme.aut.android.marvelcomicsfinder.domain.models.Characters
import hu.bme.aut.android.marvelcomicsfinder.domain.models.Creators
import hu.bme.aut.android.marvelcomicsfinder.domain.models.Events
import hu.bme.aut.android.marvelcomicsfinder.domain.models.MarvelComics
import hu.bme.aut.android.marvelcomicsfinder.domain.models.MarvelResponse
import hu.bme.aut.android.marvelcomicsfinder.domain.models.PagedData
import hu.bme.aut.android.marvelcomicsfinder.domain.models.Series
import hu.bme.aut.android.marvelcomicsfinder.domain.models.Stories
import hu.bme.aut.android.marvelcomicsfinder.domain.models.Thumbnail
import retrofit2.Call

class MarvelComicsServiceMock() : MarvelComicsService {

    override suspend fun getMarvelComics(
        titleStartWith: String?,
        startYear: String?,
        offset: String,
        limit: String
    ): MarvelResponse {
        val filteredList = list
            .filter {
                    x -> if (titleStartWith != null) x.title.startsWith(titleStartWith) else true
            }.filter {
                    x -> if (startYear != null) x.modified.startsWith(startYear) else true
            }
        return MarvelResponse(
            attributionHTML = "",
            attributionText = "",
            code = "",
            copyright = "",
            etag = "",
            status = "Ok",
            data = PagedData(
                count = filteredList.size.toString(),
                limit = "10",
                total = filteredList.size.toString(),
                offset = "0",
                results = filteredList
            )
        )
    }

    override suspend fun getMarvelComicById(id: String): MarvelResponse {
        return MarvelResponse(
            attributionHTML = "",
            attributionText = "",
            code = "",
            copyright = "",
            etag = "",
            status = "Ok",
            data = PagedData(
                count = "1",
                limit = "10",
                total = "1",
                offset = "0",
                results = list.filter { x -> x.id == id }
            )
        )
    }

    private lateinit var list: List<MarvelComics>

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

    init {
        list = mutableListOf(m1, m2)
    }
}