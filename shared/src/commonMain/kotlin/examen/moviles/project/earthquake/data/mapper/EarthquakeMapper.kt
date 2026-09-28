package examen.moviles.project.earthquake.data.mapper

import examen.moviles.project.earthquake.data.dto.EarthquakeFeatureDto
import examen.moviles.project.earthquake.domain.model.EarthquakeModel

fun EarthquakeFeatureDto.toDomain(): EarthquakeModel {
    val longitude = geometry?.coordinates?.getOrNull(0) ?: 0.0
    val latitude = geometry?.coordinates?.getOrNull(1) ?: 0.0
    val depth = geometry?.coordinates?.getOrNull(2) ?: 0.0

    return EarthquakeModel(
        place = properties?.place ?: "Ubicación desconocida",
        magnitude = properties?.mag ?: 0.0,
        time = properties?.time ?: 0L,
        url = properties?.url ?: "",
        longitude = longitude,
        latitude = latitude,
        depth = depth
    )
}