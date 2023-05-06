package hu.bme.aut.android.marvelcomicsfinder.domain.models

import hu.bme.aut.android.marvelcomicsfinder.data.entites.CollectedIssueEntity

data class CollectedIssue(
    val name: String,
    val resourceURI: String
)

fun CollectedIssueEntity.asCollectedIssue(): CollectedIssue = CollectedIssue(
    name = name,
    resourceURI = resourceURI
)

fun CollectedIssue.asCollectedIssueEntity(): CollectedIssueEntity = CollectedIssueEntity(
    name = name,
    resourceURI = resourceURI
)