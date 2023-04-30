package hu.bme.aut.android.marvelcomicsfinder.feature.favmarvelcomics.details

import androidx.lifecycle.ViewModel
import hu.bme.aut.android.marvelcomicsfinder.domain.usecases.favouritemarvelcomics.FavouriteMarvelComicsUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavMarvelComicViewModel(private val favMarvelComicsOpretions: FavouriteMarvelComicsUseCases) : ViewModel() {
    private val _state = MutableStateFlow(FavMarvelComicState())
    val state = _state.asStateFlow()

}