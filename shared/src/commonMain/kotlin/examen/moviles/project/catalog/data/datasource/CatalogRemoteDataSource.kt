package examen.moviles.project.catalog.data.datasource

import examen.moviles.project.catalog.data.dto.CatalogResponseDto

interface CatalogRemoteDataSource {
    suspend fun getMovies(): CatalogResponseDto
}