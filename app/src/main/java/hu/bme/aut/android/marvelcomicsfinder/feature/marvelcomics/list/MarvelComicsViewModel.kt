package hu.bme.aut.android.marvelcomicsfinder.feature.marvelcomics.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.marvelcomicsfinder.domain.usecases.marvelcomics.MarvelComicsUseCases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarvelComicsViewModel @Inject constructor(private val marvelComicsOperations: MarvelComicsUseCases) : ViewModel() {
    private val _state = MutableStateFlow(MarvelComicsState())
    val state = _state.asStateFlow()

    init {
        loadMarvelComicsFromApi()
    }

    private fun loadMarvelComicsFromApi() {

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                val marvelComics = marvelComicsOperations.loadMarvelComics(null, null, _state.value.limit, _state.value.offset)

                _state.update { it.copy(
                    isLoading = false,
                    marvelComicsList = marvelComics?.results ?: emptyList()
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