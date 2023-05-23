package hu.bme.aut.android.marvelcomicsfinder.domain.models

import hu.bme.aut.android.marvelcomicsfinder.data.entites.CollectedIssueEntityList
import hu.bme.aut.android.marvelcomicsfinder.data.entites.CollectionEntityList
import hu.bme.aut.android.marvelcomicsfinder.data.entites.DateEntityList
import hu.bme.aut.android.marvelcomicsfinder.data.entites.FavouriteMarvelComicsEntity
import hu.bme.aut.android.marvelcomicsfinder.data.entites.ImageEntityList
import hu.bme.aut.android.marvelcomicsfinder.data.entites.PriceEntityList
import hu.bme.aut.android.marvelcomicsfinder.data.entites.TextObjectEntityList
import hu.bme.aut.android.marvelcomicsfinder.data.entites.UrlEntityList
import hu.bme.aut.android.marvelcomicsfinder.data.entites.VariantEntityList

data class MarvelComics(
    val characters: Characters,
    val collectedIssues: List<CollectedIssue>,
    val collections: List<Collection>,
    val creators: Creators,
    val dates: List<Date>,
    val description: String?,
    val diamondCode: String,
    val digitalId: String,
    val ean: String,
    val events: Events,
    val format: String,
    val id: String,
    val images: List<Image>,
    val isbn: String,
    val issn: String,
    val issueNumber: String,
    val modified: String,
    val pageCount: String,
    val prices: List<Price>,
    val resourceURI: String,
    val series: Series,
    val stories: Stories,
    val textObjects: List<TextObject>,
    val thumbnail: Thumbnail,
    val title: String,
    val upc: String,
    val urls: List<Url>,
    val variantDescription: String,
    val variants: List<Variant>
)

fun FavouriteMarvelComicsEntity.asFavouriteMarvelComicsEntity(): MarvelComics = MarvelComics(
    characters = characters!!.asCharacters(),
    collectedIssues = collectedIssues.list.map { it -> it.asCollectedIssue() },
    collections = collections.list.map { it -> it.asCollection() },
    creators = creators!!.asCreators(),
    dates = dates.list.map { it -> it.asDate() },
    description = description ?: "",
    diamondCode = diamondCode,
    digitalId = digitalId,
    ean = ean,
    events = events!!.asEvents(),
    format = format,
    id = id,
    images = images.list.map { it -> it.asImage() },
    isbn = isbn,
    issn = issn,
    issueNumber = issueNumber,
    modified = modified,
    pageCount = pageCount,
    prices = prices.list.map { it -> it.asPrice() },
    resourceURI = resourceURI,
    series = series!!.asSeries(),
    stories = stories!!.asStories(),
    textObjects = textObjects.list.map { it -> it.asTextObject() },
    thumbnail = thumbnail!!.asThumbnail(),
    title = title,
    upc = upc,
    urls = urls.list.map { it -> it.asUrl() },
    variantDescription = variantDescription,
    variants = variants.list.map { it -> it.asVariant() }
)

fun MarvelComics.asFavouriteMarvelComicsEntity(): FavouriteMarvelComicsEntity = FavouriteMarvelComicsEntity(
    characters = characters.asCharactersEntity(),
    collectedIssues = CollectedIssueEntityList(collectedIssues.map { it -> it.asCollectedIssueEntity() }),
    collections = CollectionEntityList(collections.map { it -> it.asCollectionEntity() }),
    creators = creators.asCreatorsEntity(),
    dates = DateEntityList(dates.map { it -> it.asDateEntity() }),
    description = description,
    diamondCode = diamondCode,
    digitalId = digitalId,
    ean = ean,
    events = events.asEventsEntity(),
    format = format,
    id = id,
    images = ImageEntityList(images.map { it -> it.asImageEntity() }),
    isbn = isbn,
    issn = issn,
    issueNumber = issueNumber,
    modified = modified,
    pageCount = pageCount,
    prices = PriceEntityList(prices.map { it -> it.asPriceEntity() }),
    resourceURI = resourceURI,
    series = series.asSeriesEntity(),
    stories = stories.asStoriesEntity(),
    textObjects = TextObjectEntityList(textObjects.map { it -> it.asTextObjectEntity() }),
    thumbnail = thumbnail.asThumbnailEntity(),
    title = title,
    upc = upc,
    urls = UrlEntityList(urls.map { it -> it.asUrlEntity() }),
    variantDescription = variantDescription,
    variants = VariantEntityList(variants.map { it -> it.asVariantEntity() })
)