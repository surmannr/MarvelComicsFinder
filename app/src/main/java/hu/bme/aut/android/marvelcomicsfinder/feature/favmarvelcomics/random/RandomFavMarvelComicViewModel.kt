package hu.bme.aut.android.marvelcomicsfinder.feature.favmarvelcomics.random

import androidx.lifecycle.ViewModel
import hu.bme.aut.android.marvelcomicsfinder.domain.usecases.favouritemarvelcomics.FavouriteMarvelComicsUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RandomFavMarvelComicViewModel(private val favMarvelComicsOpretions: FavouriteMarvelComicsUseCases) : ViewModel() {
    private val _state = MutableStateFlow(RandomFavMarvelComicState())
    val state = _state.asStateFlow()

}