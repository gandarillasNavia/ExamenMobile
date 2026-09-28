package examen.moviles.project.earthquake.data.datasource

import examen.moviles.project.earthquake.data.dto.EarthquakeResponseDto

interface EarthquakeRemoteDataSource {
    suspend fun getEarthquakes(): EarthquakeResponseDto
}