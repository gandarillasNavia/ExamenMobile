package examen.moviles.project.catalog.domain.repository

import examen.moviles.project.catalog.domain.model.MovieModel

interface CatalogRepository {
    suspend fun getMovies(): Result<List<MovieModel>>
}