package hu.bme.aut.android.marvelcomicsfinder.feature.marvelcomics.list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.marvelcomicsfinder.domain.usecases.marvelcomics.MarvelComicsUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MarvelComicsViewModel @Inject constructor(private val marvelComicsOpretions: MarvelComicsUseCases) : ViewModel() {
    private val _state = MutableStateFlow(MarvelComicsState())
    val state = _state.asStateFlow()

}