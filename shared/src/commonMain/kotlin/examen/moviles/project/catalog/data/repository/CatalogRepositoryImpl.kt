package examen.moviles.project.catalog.data.repository

import examen.moviles.project.catalog.data.datasource.CatalogRemoteDataSource
import examen.moviles.project.catalog.data.mapper.toDomain
import examen.moviles.project.catalog.domain.model.MovieModel
import examen.moviles.project.catalog.domain.repository.CatalogRepository

class CatalogRepositoryImpl(
    private val dataSource: CatalogRemoteDataSource
) : CatalogRepository {

    override suspend fun getMovies(): Result<List<MovieModel>> {
        return try {
            val response = dataSource.getMovies()
            val domainList = response.results?.map { it.toDomain() } ?: emptyList()
            Result.success(domainList)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}