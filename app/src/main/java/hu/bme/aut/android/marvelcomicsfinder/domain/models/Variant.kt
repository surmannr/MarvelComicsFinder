package hu.bme.aut.android.marvelcomicsfinder.domain.models

import hu.bme.aut.android.marvelcomicsfinder.data.entites.VariantEntity

data class Variant(
    val name: String,
    val resourceURI: String
)

fun VariantEntity.asVariant(): Variant = Variant(
    name = name ?: "",
    resourceURI = resourceURI ?: "",
)

fun Variant.asVariantEntity(): VariantEntity = VariantEntity(
    name = name,
    resourceURI = resourceURI
)