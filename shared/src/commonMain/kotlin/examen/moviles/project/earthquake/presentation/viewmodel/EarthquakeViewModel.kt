package examen.moviles.project.earthquake.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import examen.moviles.project.earthquake.domain.usecase.GetEarthquakesUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EarthquakeViewModel(
    private val getEarthquakesUseCase: GetEarthquakesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(EarthquakeState())
    val state: StateFlow<EarthquakeState> = _state.asStateFlow()

    private val _effect = Channel<EarthquakeEffect>()
    val effect = _effect.receiveAsFlow()

    init {
        onEvent(EarthquakeEvent.LoadEarthquakes)
    }

    fun onEvent(event: EarthquakeEvent) {
        when (event) {
            is EarthquakeEvent.LoadEarthquakes -> loadEarthquakes()
        }
    }

    private fun loadEarthquakes() {
        _state.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            getEarthquakesUseCase()
                .onSuccess { list ->
                    _state.update {
                        it.copy(isLoading = false, earthquakes = list)
                    }
                }
                .onFailure { exception ->
                    val errorMessage = exception.message ?: "Error al cargar teremotos"
                    _state.update {
                        it.copy(isLoading = false, error = errorMessage)
                    }
                    _effect.send(EarthquakeEffect.ShowError(errorMessage))
                }
        }
    }
}