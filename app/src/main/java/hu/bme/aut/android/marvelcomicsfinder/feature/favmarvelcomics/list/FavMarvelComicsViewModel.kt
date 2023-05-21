package hu.bme.aut.android.marvelcomicsfinder.feature.favmarvelcomics.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
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
class FavMarvelComicsViewModel @Inject constructor(private val favMarvelComicsOpretions: FavouriteMarvelComicsUseCases) : ViewModel() {
    private val _state = MutableStateFlow(FavMarvelComicsState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        loadMarvelComicsFromDb(null, null)
    }

    private fun loadMarvelComicsFromDb(titleStartwith: String?, startYear: String?) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                val titleFilter = if (titleStartwith.isNullOrEmpty()) null else titleStartwith
                val startYearFilter = if (startYear.isNullOrEmpty()) null else startYear

                val marvelComics = favMarvelComicsOpretions.loadFavouriteMarvelComics(titleFilter, startYearFilter, _state.value.limit, _state.value.offset, _state.value.pageCount - 1)

                _state.update { it.copy(
                    isLoading = false,
                    marvelComicsList = marvelComics?.getOrThrow()?.results ?: emptyList(),
                    maxPageCount = (marvelComics?.getOrThrow()?.total?.toInt() ?: 0) /  (marvelComics?.getOrThrow()?.limit?.toInt() ?: 1)
                ) }

            } catch (e: Exception) {
                _state.update {  it.copy(
                    isLoading = false,
                    error = e
                ) }
            }
        }
    }

    fun onEvent(event: FavMarvelComicsEvent) {
        when (event) {
            is FavMarvelComicsEvent.Filter -> {
                loadMarvelComicsFromDb(event.title, event.year)
            }
            is FavMarvelComicsEvent.NextPage -> {
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
                loadMarvelComicsFromDb(event.title, event.year)
            }
            is FavMarvelComicsEvent.PrevPage -> {
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
                loadMarvelComicsFromDb(event.title, event.year)
            }

            is FavMarvelComicsEvent.DeleteFavourite -> {
                viewModelScope.launch {
                    try {
                        favMarvelComicsOpretions.deleteFavouriteMarvelComics(event.marvelComics.id)
                        _uiEvent.send(UiEvent.Success("Sikeresen törölted a kedvencek közül: ${event.marvelComics.title}"))
                        loadMarvelComicsFromDb(null, null)
                    } catch (_: Exception) {
                        _uiEvent.send(UiEvent.Success("Hiba történt a törléskor: ${event.marvelComics.title}"))
                    }

                }
            }
        }
    }
}