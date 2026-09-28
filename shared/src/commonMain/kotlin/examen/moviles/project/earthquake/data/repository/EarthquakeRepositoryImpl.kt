package examen.moviles.project.earthquake.data.repository

import examen.moviles.project.earthquake.data.datasource.EarthquakeRemoteDataSource
import examen.moviles.project.earthquake.data.mapper.toDomain
import examen.moviles.project.earthquake.domain.model.EarthquakeModel
import examen.moviles.project.earthquake.domain.repository.EarthquakeRepository

class EarthquakeRepositoryImpl(
    private val dataSource: EarthquakeRemoteDataSource
) : EarthquakeRepository {

    override suspend fun getEarthquakes(): Result<List<EarthquakeModel>> {
        return try {
            val response = dataSource.getEarthquakes()
            val domainList = response.features?.map { it.toDomain() } ?: emptyList()
            Result.success(domainList)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}