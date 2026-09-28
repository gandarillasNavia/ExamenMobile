package examen.moviles.project.earthquake.domain.repository

import examen.moviles.project.earthquake.domain.model.EarthquakeModel

interface EarthquakeRepository {
    suspend fun getEarthquakes(): Result<List<EarthquakeModel>>
}