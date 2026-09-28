package examen.moviles.project.earthquake.presentation.viewmodel

sealed interface EarthquakeEffect {
    data class ShowError(val message: String) : EarthquakeEffect
}