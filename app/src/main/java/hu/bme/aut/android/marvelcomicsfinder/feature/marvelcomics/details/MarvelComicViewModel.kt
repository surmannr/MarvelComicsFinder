package hu.bme.aut.android.marvelcomicsfinder.feature.marvelcomics.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.marvelcomicsfinder.domain.usecases.marvelcomics.MarvelComicsUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarvelComicViewModel @Inject constructor(private val marvelComicsOperations: MarvelComicsUseCases, private val savedState: SavedStateHandle) : ViewModel() {
    private val _state = MutableStateFlow(MarvelComicState())
    val state = _state.asStateFlow()

    init {
        loadMarvelComicByIdFromApi()
    }
    private fun loadMarvelComicByIdFromApi() {
        val marvelComicId = checkNotNull<String>(savedState["id"])
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                val marvelComics = marvelComicsOperations.loadMarvelComic(marvelComicId)

                _state.update { it.copy(
                    isLoading = false,
                    marvelComic = marvelComics?.results?.get(0)
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