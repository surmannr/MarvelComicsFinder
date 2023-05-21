package hu.bme.aut.android.marvelcomicsfinder.domain.models

import hu.bme.aut.android.marvelcomicsfinder.data.entites.TextObjectEntity

data class TextObject(
    val language: String,
    val text: String,
    val type: String
)

fun TextObjectEntity.asTextObject(): TextObject = TextObject(
    text = text ?: "",
    type = type ?: "",
    language = language ?: "",
)

fun TextObject.asTextObjectEntity(): TextObjectEntity = TextObjectEntity(
    text = text,
    type = type,
    language = language
)