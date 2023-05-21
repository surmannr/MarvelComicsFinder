package hu.bme.aut.android.marvelcomicsfinder.feature.marvelcomics.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.marvelcomicsfinder.domain.models.MarvelComics
import hu.bme.aut.android.marvelcomicsfinder.domain.usecases.favouritemarvelcomics.FavouriteMarvelComicsUseCases
import hu.bme.aut.android.marvelcomicsfinder.domain.usecases.marvelcomics.MarvelComicsUseCases
import hu.bme.aut.android.marvelcomicsfinder.ui.model.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarvelComicViewModel @Inject constructor(private val marvelComicsOperations: MarvelComicsUseCases, private val favMarvelComicOperation: FavouriteMarvelComicsUseCases, private val savedState: SavedStateHandle) : ViewModel() {
    private val _state = MutableStateFlow(MarvelComicState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        loadMarvelComicByIdFromApi()
    }
    private fun loadMarvelComicByIdFromApi() {
        val marvelComicId = checkNotNull<String>(savedState["id"])
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                val marvelComics = marvelComicsOperations.loadMarvelComic(marvelComicId)
                val isInFav = favMarvelComicOperation.loadFavouriteMarvelComic(marvelComicId).getOrNull()
                _state.update { it.copy(
                    isLoading = false,
                    isInFavouriteList = isInFav != null,
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

    fun addFavourite(comic: MarvelComics) {
        viewModelScope.launch {
            try {
                favMarvelComicOperation.saveFavouriteMarvelComics(comic)
                _uiEvent.send(UiEvent.Success("Sikeresen a kedvencek közé adtad: ${comic.title}"))
            } catch (e: Exception) {
                _uiEvent.send(UiEvent.Success("Hiba történt a kedvencekhez adáskor: ${comic.title}"))
                print(e.printStackTrace())
            }

        }
    }

    fun removeFavourite(comic: MarvelComics) {
        viewModelScope.launch {
            try {
                favMarvelComicOperation.deleteFavouriteMarvelComics(comic.id)
                _uiEvent.send(UiEvent.Success("Sikeresen törölted: ${comic.title}"))
            } catch (e: Exception) {
                _uiEvent.send(UiEvent.Success("Hiba történt a törléskor: ${comic.title}"))
                print(e.printStackTrace())
            }

        }
    }
}