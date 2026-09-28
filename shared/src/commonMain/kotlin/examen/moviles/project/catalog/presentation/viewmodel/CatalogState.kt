package examen.moviles.project.catalog.presentation.viewmodel

import examen.moviles.project.catalog.domain.model.MovieModel

data class CatalogState(
    val isLoading: Boolean = false,
    val movies: List<MovieModel> = emptyList(),
    val error: String? = null
)