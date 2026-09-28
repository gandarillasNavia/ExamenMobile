package examen.moviles.project.earthquake.presentation.viewmodel

sealed interface EarthquakeEvent {
    data object LoadEarthquakes : EarthquakeEvent
}