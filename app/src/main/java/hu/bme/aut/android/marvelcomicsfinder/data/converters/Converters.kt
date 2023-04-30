package hu.bme.aut.android.marvelcomicsfinder.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import hu.bme.aut.android.marvelcomicsfinder.data.entites.CharactersEntity
import hu.bme.aut.android.marvelcomicsfinder.data.entites.CollectedIssueEntity
import hu.bme.aut.android.marvelcomicsfinder.data.entites.CollectionEntity
import hu.bme.aut.android.marvelcomicsfinder.data.entites.CreatorsEntity
import hu.bme.aut.android.marvelcomicsfinder.data.entites.DateEntity
import hu.bme.aut.android.marvelcomicsfinder.data.entites.EventsEntity
import hu.bme.aut.android.marvelcomicsfinder.data.entites.ImageEntity
import hu.bme.aut.android.marvelcomicsfinder.data.entites.ItemEntity
import hu.bme.aut.android.marvelcomicsfinder.data.entites.ItemXXEntity
import hu.bme.aut.android.marvelcomicsfinder.data.entites.ItemXXXEntity
import hu.bme.aut.android.marvelcomicsfinder.data.entites.PriceEntity
import hu.bme.aut.android.marvelcomicsfinder.data.entites.SeriesEntity
import hu.bme.aut.android.marvelcomicsfinder.data.entites.StoriesEntity
import hu.bme.aut.android.marvelcomicsfinder.data.entites.TextObjectEntity
import hu.bme.aut.android.marvelcomicsfinder.data.entites.ThumbnailEntity
import hu.bme.aut.android.marvelcomicsfinder.data.entites.UrlEntity
import hu.bme.aut.android.marvelcomicsfinder.data.entites.VariantEntity
import java.lang.reflect.Type


class Converters {

    // List<ItemEntity>
    @TypeConverter
    fun fromItemEntity(element: List<ItemEntity>): String {
        return Gson().toJson(element)
    }
    @TypeConverter
    fun toItemEntity(elementJson: String): List<ItemEntity> {
        val listType: Type = object : TypeToken<List<ItemEntity>?>() {}.type
        return Gson().fromJson(elementJson,listType)
    }

    // List<ItemXXEntity>
    @TypeConverter
    fun fromItemXXEntity(element: List<ItemXXEntity>): String {
        return Gson().toJson(element)
    }
    @TypeConverter
    fun toItemXXEntity(elementJson: String): List<ItemXXEntity> {
        val listType: Type = object : TypeToken<List<ItemXXEntity>?>() {}.type
        return Gson().fromJson(elementJson,listType)
    }

    // CharactersEntity
    @TypeConverter
    fun fromCharactersEntity(element: CharactersEntity): String {
        return Gson().toJson(element)
    }
    @TypeConverter
    fun toCharactersEntity(elementJson: String): CharactersEntity {
        val listType: Type = object : TypeToken<CharactersEntity?>() {}.type
        return Gson().fromJson(elementJson,listType)
    }

    // List<CollectedIssueEntity>
    @TypeConverter
    fun fromCollectedIssueEntity(element: List<CollectedIssueEntity>): String {
        return Gson().toJson(element)
    }
    @TypeConverter
    fun toCollectedIssueEntity(elementJson: String): List<CollectedIssueEntity> {
        val listType: Type = object : TypeToken<List<CollectedIssueEntity>?>() {}.type
        return Gson().fromJson(elementJson,listType)
    }

    // List<CollectionEntity>
    @TypeConverter
    fun fromCollectionEntity(element: List<CollectionEntity>): String {
        return Gson().toJson(element)
    }
    @TypeConverter
    fun toCollectionEntity(elementJson: String): List<CollectionEntity> {
        val listType: Type = object : TypeToken<List<CollectionEntity>?>() {}.type
        return Gson().fromJson(elementJson,listType)
    }

    // CreatorsEntity
    @TypeConverter
    fun fromCreatorsEntity(element: CreatorsEntity): String {
        return Gson().toJson(element)
    }
    @TypeConverter
    fun toCreatorsEntity(elementJson: String): CreatorsEntity {
        val listType: Type = object : TypeToken<CreatorsEntity?>() {}.type
        return Gson().fromJson(elementJson,listType)
    }

    // List<DateEntity>
    @TypeConverter
    fun fromDateEntity(element: List<DateEntity>): String {
        return Gson().toJson(element)
    }
    @TypeConverter
    fun toDateEntity(elementJson: String): List<DateEntity> {
        val listType: Type = object : TypeToken<List<DateEntity>?>() {}.type
        return Gson().fromJson(elementJson,listType)
    }

    // EventsEntity
    @TypeConverter
    fun fromEventsEntity(element: EventsEntity): String {
        return Gson().toJson(element)
    }
    @TypeConverter
    fun toEventsEntity(elementJson: String): EventsEntity {
        val listType: Type = object : TypeToken<EventsEntity?>() {}.type
        return Gson().fromJson(elementJson,listType)
    }

    // List<ImageEntity>
    @TypeConverter
    fun fromImageEntity(element: List<ImageEntity>): String {
        return Gson().toJson(element)
    }
    @TypeConverter
    fun toImageEntity(elementJson: String): List<ImageEntity> {
        val listType: Type = object : TypeToken<List<ImageEntity>?>() {}.type
        return Gson().fromJson(elementJson,listType)
    }

    // List<PriceEntity>
    @TypeConverter
    fun fromPriceEntity(element: List<PriceEntity>): String {
        return Gson().toJson(element)
    }
    @TypeConverter
    fun toPriceEntity(elementJson: String): List<PriceEntity> {
        val listType: Type = object : TypeToken<List<PriceEntity>?>() {}.type
        return Gson().fromJson(elementJson,listType)
    }

    // SeriesEntity
    @TypeConverter
    fun fromSeriesEntity(element: SeriesEntity): String {
        return Gson().toJson(element)
    }
    @TypeConverter
    fun toSeriesEntity(elementJson: String): SeriesEntity {
        val listType: Type = object : TypeToken<SeriesEntity?>() {}.type
        return Gson().fromJson(elementJson,listType)
    }

    // StoriesEntity
    @TypeConverter
    fun fromStoriesEntity(element: StoriesEntity): String {
        return Gson().toJson(element)
    }
    @TypeConverter
    fun toStoriesEntity(elementJson: String): StoriesEntity {
        val listType: Type = object : TypeToken<StoriesEntity?>() {}.type
        return Gson().fromJson(elementJson,listType)
    }

    // List<TextObjectEntity>
    @TypeConverter
    fun fromTextObjectEntity(element: List<TextObjectEntity>): String {
        return Gson().toJson(element)
    }
    @TypeConverter
    fun toTextObjectEntity(elementJson: String): List<TextObjectEntity> {
        val listType: Type = object : TypeToken<List<TextObjectEntity>?>() {}.type
        return Gson().fromJson(elementJson,listType)
    }

    // ThumbnailEntity
    @TypeConverter
    fun fromThumbnailEntity(element: ThumbnailEntity): String {
        return Gson().toJson(element)
    }
    @TypeConverter
    fun toThumbnailEntity(elementJson: String): ThumbnailEntity {
        val listType: Type = object : TypeToken<ThumbnailEntity?>() {}.type
        return Gson().fromJson(elementJson,listType)
    }

    // List<UrlEntity>
    @TypeConverter
    fun fromUrlEntity(element: List<UrlEntity>): String {
        return Gson().toJson(element)
    }
    @TypeConverter
    fun toUrlEntity(elementJson: String): List<UrlEntity> {
        val listType: Type = object : TypeToken<List<UrlEntity>?>() {}.type
        return Gson().fromJson(elementJson,listType)
    }

    // List<VariantEntity>
    @TypeConverter
    fun fromVariantEntity(element: List<VariantEntity>): String {
        return Gson().toJson(element)
    }
    @TypeConverter
    fun toVariantEntity(elementJson: String): List<VariantEntity> {
        val listType: Type = object : TypeToken<List<VariantEntity>?>() {}.type
        return Gson().fromJson(elementJson,listType)
    }

    // List<ItemXXXEntity>
    @TypeConverter
    fun fromItemXXXEntity(element: List<ItemXXXEntity>): String {
        return Gson().toJson(element)
    }
    @TypeConverter
    fun toItemXXXEntity(elementJson: String): List<ItemXXXEntity> {
        val listType: Type = object : TypeToken<List<ItemXXXEntity>?>() {}.type
        return Gson().fromJson(elementJson,listType)
    }
}