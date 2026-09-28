package examen.moviles.project.catalog.presentation.viewmodel

sealed interface CatalogEffect {
    data class ShowToast(val message: String) : CatalogEffect
    data class NavigateToDetail(val movieId: Int) : CatalogEffect
}