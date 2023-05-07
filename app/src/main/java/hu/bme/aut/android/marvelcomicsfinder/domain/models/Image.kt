package hu.bme.aut.android.marvelcomicsfinder.domain.models

import hu.bme.aut.android.marvelcomicsfinder.data.entites.ImageEntity

data class Image(
    val extension: String,
    val path: String
)

fun ImageEntity.asImage(): Image = Image(
    extension = extension,
    path = path
)

fun Image.asImageEntity(): ImageEntity = ImageEntity(
    extension = extension,
    path = path
)