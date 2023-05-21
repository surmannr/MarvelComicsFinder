package hu.bme.aut.android.marvelcomicsfinder.ui.model

sealed class UiEvent{
    data class Success(val message: String) : UiEvent()
    data class Failure(val error: String) : UiEvent()
}