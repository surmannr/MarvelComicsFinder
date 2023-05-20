package hu.bme.aut.android.marvelcomicsfinder.feature.marvelcomics.list

sealed class MarvelComicsEvent {
    data class Filter(val title: String?, val year: String?): MarvelComicsEvent()
    data class NextPage(val title: String?, val year: String?): MarvelComicsEvent()
    data class PrevPage(val title: String?, val year: String?): MarvelComicsEvent()
}