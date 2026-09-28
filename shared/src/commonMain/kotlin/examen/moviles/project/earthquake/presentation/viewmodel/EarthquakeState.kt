package examen.moviles.project.earthquake.presentation.viewmodel

import examen.moviles.project.earthquake.domain.model.EarthquakeModel

data class EarthquakeState(
    val isLoading: Boolean = false,
    val earthquakes: List<EarthquakeModel> = emptyList(),
    val error: String? = null
)