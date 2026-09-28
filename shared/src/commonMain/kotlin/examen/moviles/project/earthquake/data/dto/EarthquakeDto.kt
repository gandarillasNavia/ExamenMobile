package examen.moviles.project.earthquake.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class EarthquakeResponseDto(
    val features: List<EarthquakeFeatureDto>? = null
)

@Serializable
data class EarthquakeFeatureDto(
    val properties: EarthquakePropertiesDto? = null,
    val geometry: EarthquakeGeometryDto? = null
)

@Serializable
data class EarthquakePropertiesDto(
    val place: String? = null,
    val mag: Double? = null,
    val time: Long? = null,
    val url: String? = null
)

@Serializable
data class EarthquakeGeometryDto(
    val coordinates: List<Double>? = null
)