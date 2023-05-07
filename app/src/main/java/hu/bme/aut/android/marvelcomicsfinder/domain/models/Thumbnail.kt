package hu.bme.aut.android.marvelcomicsfinder.domain.models

import hu.bme.aut.android.marvelcomicsfinder.data.entites.ThumbnailEntity

data class Thumbnail(
    val extension: String,
    val path: String
)

fun ThumbnailEntity.asThumbnail(): Thumbnail = Thumbnail(
    extension = extension,
    path = path
)

fun Thumbnail.asThumbnailEntity(): ThumbnailEntity = ThumbnailEntity(
    extension = extension,
    path = path
)