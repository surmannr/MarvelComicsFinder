package hu.bme.aut.android.marvelcomicsfinder.feature.favmarvelcomics.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.marvelcomicsfinder.domain.models.MarvelComics
import hu.bme.aut.android.marvelcomicsfinder.domain.usecases.favouritemarvelcomics.FavouriteMarvelComicsUseCases
import hu.bme.aut.android.marvelcomicsfinder.ui.model.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavMarvelComicViewModel @Inject constructor(private val favMarvelComicsOpretions: FavouriteMarvelComicsUseCases, private val savedState: SavedStateHandle) : ViewModel() {
    private val _state = MutableStateFlow(FavMarvelComicState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        loadMarvelComicByIdFromDb()
    }
    private fun loadMarvelComicByIdFromDb() {
        val marvelComicId = checkNotNull<String>(savedState["id"])
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                val marvelComics = favMarvelComicsOpretions.loadFavouriteMarvelComic(marvelComicId)

                _state.update { it.copy(
                    isLoading = false,
                    marvelComic = marvelComics.getOrThrow()
                ) }

            } catch (e: Exception) {
                _state.update {  it.copy(
                    isLoading = false,
                    error = e
                ) }
            }
        }
    }

    fun removeFavourite(comic: MarvelComics) {
        viewModelScope.launch {
            try {
                favMarvelComicsOpretions.deleteFavouriteMarvelComics(comic.id)
                _uiEvent.send(UiEvent.Success("Sikeresen törölted: ${comic.title}"))
            } catch (e: Exception) {
                _uiEvent.send(UiEvent.Success("Hiba történt a törléskor: ${comic.title}"))
                print(e.printStackTrace())
            }

        }
    }
}