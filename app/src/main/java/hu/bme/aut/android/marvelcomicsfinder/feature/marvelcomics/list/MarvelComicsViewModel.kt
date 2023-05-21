package hu.bme.aut.android.marvelcomicsfinder.feature.marvelcomics.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.marvelcomicsfinder.domain.usecases.favouritemarvelcomics.FavouriteMarvelComicsUseCases
import hu.bme.aut.android.marvelcomicsfinder.domain.usecases.marvelcomics.MarvelComicsUseCases
import hu.bme.aut.android.marvelcomicsfinder.ui.model.UiEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarvelComicsViewModel @Inject constructor(
        private val marvelComicsOperations: MarvelComicsUseCases,
        private val favMarvelComicOperation: FavouriteMarvelComicsUseCases
    ) : ViewModel() {

    private val _state = MutableStateFlow(MarvelComicsState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

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
                    marvelComicsList = marvelComics?.results ?: emptyList(),
                    maxPageCount = (marvelComics?.total?.toInt() ?: 0) /  (marvelComics?.limit?.toInt() ?: 1)
                ) }

            } catch (e: Exception) {
                _state.update {  it.copy(
                    isLoading = false,
                    error = e
                ) }
            }
        }
    }

    fun onEvent(event: MarvelComicsEvent) {
        when (event) {
            is MarvelComicsEvent.Filter -> {
                filterMarvelComicsFromApi(event.title, event.year)
            }
            is MarvelComicsEvent.NextPage -> {
                _state.update {
                    val offsetNumber = it.offset.toInt()
                    val limitNumber = it.limit.toInt()
                    if (it.pageCount != it.maxPageCount) {
                        it.copy(
                            offset = (offsetNumber + limitNumber).toString(),
                            pageCount = it.pageCount + 1,
                        )
                    } else {
                        it.copy()
                    }

                }
                filterMarvelComicsFromApi(event.title, event.year)
            }
            is MarvelComicsEvent.PrevPage -> {
                _state.update {
                    val offsetNumber = it.offset.toInt()
                    val limitNumber = it.limit.toInt()
                    if (it.pageCount == 1) {
                        it.copy()
                    } else {
                        it.copy(
                            offset = (offsetNumber - limitNumber).toString(),
                            pageCount = it.pageCount - 1,
                        )
                    }
                }
                filterMarvelComicsFromApi(event.title, event.year)
            }

            is MarvelComicsEvent.AddFavourite -> {
                viewModelScope.launch {
                    try {
                        favMarvelComicOperation.saveFavouriteMarvelComics(event.marvelComics)
                        _uiEvent.send(UiEvent.Success("Sikeresen a kedvencek közé adtad: ${event.marvelComics.title}"))
                    } catch (e: Exception) {
                        _uiEvent.send(UiEvent.Success("Hiba történt a kedvencekhez adáskor: ${event.marvelComics.title}"))
                        print(e.printStackTrace())
                    }

                }
            }
        }
    }

    private fun filterMarvelComicsFromApi(titleStartwith: String?, startYear: String?) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                val titleFilter = if (titleStartwith.isNullOrEmpty()) null else titleStartwith
                val startYearFilter = if (startYear.isNullOrEmpty()) null else startYear

                val marvelComics = marvelComicsOperations.loadMarvelComics(titleFilter, startYearFilter, _state.value.limit, _state.value.offset)

                _state.update { it.copy(
                    isLoading = false,
                    marvelComicsList = marvelComics?.results ?: emptyList(),
                    maxPageCount = (marvelComics?.total?.toInt() ?: 0) /  (marvelComics?.limit?.toInt() ?: 1)
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