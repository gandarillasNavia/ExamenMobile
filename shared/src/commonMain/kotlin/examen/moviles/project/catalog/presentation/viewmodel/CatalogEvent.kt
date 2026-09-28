package examen.moviles.project.catalog.presentation.viewmodel

sealed interface CatalogEvent {
    data object LoadMovies : CatalogEvent
    data class OnMovieClick(val movieId: Int) : CatalogEvent
}