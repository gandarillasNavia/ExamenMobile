package examen.moviles.project.di

import org.koin.dsl.module
import examen.moviles.project.catalog.presentation.viewmodel.CatalogViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
val presentationModule = module {
    viewModelOf(::CatalogViewModel)
}
