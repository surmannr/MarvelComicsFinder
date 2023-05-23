package hu.bme.aut.android.marvelcomicsfinder.data.entites

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_marvelcomics")
data class FavouriteMarvelComicsEntity(
    @PrimaryKey val id: String = "",
    @Embedded(prefix = "characters_favcomicsentity") val characters: CharactersEntity? = null,
    @Embedded(prefix = "collectedissue_favcomicsentity") val collectedIssues: CollectedIssueEntityList = CollectedIssueEntityList(
        emptyList()
    ),
    @Embedded(prefix = "collection_favcomicsentity") val collections: CollectionEntityList = CollectionEntityList(
        emptyList()
    ),
    @Embedded(prefix = "creators_favcomicsentity") val creators: CreatorsEntity? = null,
    @Embedded(prefix = "date_favcomicsentity") val dates: DateEntityList = DateEntityList(emptyList()),
    val description: String? = "",
    val diamondCode: String= "",
    val digitalId: String= "",
    val ean: String= "",
    @Embedded(prefix = "events_favcomicsentity") val events: EventsEntity? = null,
    val format: String= "",
    @Embedded(prefix = "image_favcomicsentity") val images: ImageEntityList = ImageEntityList(
        emptyList()
    ),
    val isbn: String= "",
    val issn: String= "",
    val issueNumber: String= "",
    val modified: String= "",
    val pageCount: String= "",
    @Embedded(prefix = "price_favcomicsentity") val prices: PriceEntityList = PriceEntityList(
        emptyList()
    ),
    val resourceURI: String= "",
    @Embedded(prefix = "series_favcomicsentity") val series: SeriesEntity? = null,
    @Embedded(prefix = "stories_favcomicsentity") val stories: StoriesEntity? = null,
    @Embedded(prefix = "textobject_favcomicsentity") val textObjects: TextObjectEntityList = TextObjectEntityList(
        emptyList()
    ),
    @Embedded(prefix = "thumbnail_favcomicsentity") val thumbnail: ThumbnailEntity? = null,
    val title: String= "",
    val upc: String= "",
    @Embedded(prefix = "url_favcomicsentity") val urls: UrlEntityList = UrlEntityList(emptyList()),
    val variantDescription: String= "",
    @Embedded(prefix = "variant_favcomicsentity") val variants: VariantEntityList = VariantEntityList(
        emptyList()
    ),
)

data class CollectedIssueEntityList(
    var list: List<CollectedIssueEntity> = emptyList(),
)

data class CollectionEntityList(
    var list: List<CollectionEntity> = emptyList(),
)

data class DateEntityList(
    var list: List<DateEntity> = emptyList(),
)

data class ImageEntityList(
    var list: List<ImageEntity> = emptyList(),
)

data class PriceEntityList(
    var list: List<PriceEntity> = emptyList(),
)

data class TextObjectEntityList(
    var list: List<TextObjectEntity> = emptyList(),
)

data class UrlEntityList(
    var list: List<UrlEntity> = emptyList(),
)

data class VariantEntityList(
    var list: List<VariantEntity> = emptyList(),
)