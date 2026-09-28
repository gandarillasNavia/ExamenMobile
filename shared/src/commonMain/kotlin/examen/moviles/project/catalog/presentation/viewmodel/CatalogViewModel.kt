package examen.moviles.project.catalog.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import examen.moviles.project.catalog.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CatalogViewModel(
    private val getPopularMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CatalogState())
    val state: StateFlow<CatalogState> = _state.asStateFlow()

    private val _effect = Channel<CatalogEffect>()
    val effect = _effect.receiveAsFlow()

    init {
        onEvent(CatalogEvent.LoadMovies)
    }

    fun onEvent(event: CatalogEvent) {
        when (event) {
            is CatalogEvent.LoadMovies -> loadMovies()
            is CatalogEvent.OnMovieClick -> {
                viewModelScope.launch {
                    _effect.send(CatalogEffect.NavigateToDetail(event.movieId))
                }
            }
        }
    }

    private fun loadMovies() {
        _state.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            getPopularMoviesUseCase()
                .onSuccess { moviesList ->
                    _state.update {
                        it.copy(isLoading = false, movies = moviesList)
                    }
                }
                .onFailure { exception ->
                    val errorMessage = exception.message ?: "Error inesperado"
                    _state.update {
                        it.copy(isLoading = false, error = errorMessage)
                    }
                    _effect.send(CatalogEffect.ShowToast(errorMessage))
                }
        }
    }
}