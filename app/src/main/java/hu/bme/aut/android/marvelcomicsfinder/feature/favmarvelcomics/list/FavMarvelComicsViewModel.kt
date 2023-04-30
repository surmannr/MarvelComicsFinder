package hu.bme.aut.android.marvelcomicsfinder.feature.favmarvelcomics.list

import androidx.lifecycle.ViewModel
import hu.bme.aut.android.marvelcomicsfinder.domain.usecases.favouritemarvelcomics.FavouriteMarvelComicsUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavMarvelComicsViewModel(private val favMarvelComicsOpretions: FavouriteMarvelComicsUseCases) : ViewModel() {
    private val _state = MutableStateFlow(FavMarvelComicsState())
    val state = _state.asStateFlow()

}