package hu.bme.aut.android.marvelcomicsfinder.feature.marvelcomics.list

import hu.bme.aut.android.marvelcomicsfinder.domain.models.MarvelComics

sealed class MarvelComicsEvent {
    data class Filter(val title: String?, val year: String?): MarvelComicsEvent()
    data class NextPage(val title: String?, val year: String?): MarvelComicsEvent()
    data class PrevPage(val title: String?, val year: String?): MarvelComicsEvent()
    data class AddFavourite(val marvelComics: MarvelComics): MarvelComicsEvent()
}