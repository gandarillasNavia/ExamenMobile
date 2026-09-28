package examen.moviles.project.catalog.domain.usecase

import examen.moviles.project.catalog.domain.model.MovieModel
import examen.moviles.project.catalog.domain.repository.CatalogRepository

class GetMoviesUseCase(
    private val repository: CatalogRepository
) {
    suspend operator fun invoke(): Result<List<MovieModel>> {
        return repository.getMovies()
    }
}
