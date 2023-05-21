package hu.bme.aut.android.marvelcomicsfinder.feature.favmarvelcomics.random

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.marvelcomicsfinder.domain.usecases.favouritemarvelcomics.FavouriteMarvelComicsUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomFavMarvelComicViewModel @Inject constructor(private val favMarvelComicsOpretions: FavouriteMarvelComicsUseCases) : ViewModel() {
    private val _state = MutableStateFlow(RandomFavMarvelComicState())
    val state = _state.asStateFlow()
    fun loadRandomMarvelComicFromDb() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                val marvelComics = favMarvelComicsOpretions.randomFavouriteMarvelComics().getOrThrow()

                _state.update { it.copy(
                    isLoading = false,
                    randomMarvelComic = marvelComics
                ) }

            } catch (e: Exception) {
                _state.update {  it.copy(
                    isLoading = false,
                    error = e
                ) }
            }
        }
    }
}