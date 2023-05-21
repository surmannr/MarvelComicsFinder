package hu.bme.aut.android.marvelcomicsfinder.feature.favmarvelcomics.list

import hu.bme.aut.android.marvelcomicsfinder.domain.models.MarvelComics

sealed class FavMarvelComicsEvent {
    data class Filter(val title: String?, val year: String?): FavMarvelComicsEvent()
    data class NextPage(val title: String?, val year: String?): FavMarvelComicsEvent()
    data class PrevPage(val title: String?, val year: String?): FavMarvelComicsEvent()
    data class DeleteFavourite(val marvelComics: MarvelComics): FavMarvelComicsEvent()
}