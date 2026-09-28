package examen.moviles.project.earthquake.domain.usecase

import examen.moviles.project.earthquake.domain.model.EarthquakeModel
import examen.moviles.project.earthquake.domain.repository.EarthquakeRepository

class GetEarthquakesUseCase(
    private val repository: EarthquakeRepository
) {
    suspend operator fun invoke(): Result<List<EarthquakeModel>> {
        return repository.getEarthquakes()
    }
}