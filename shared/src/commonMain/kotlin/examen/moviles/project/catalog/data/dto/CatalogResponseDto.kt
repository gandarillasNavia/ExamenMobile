package examen.moviles.project.catalog.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CatalogResponseDto(
    val page: Int? = null,
    val results: List<MovieDto>? = null
)