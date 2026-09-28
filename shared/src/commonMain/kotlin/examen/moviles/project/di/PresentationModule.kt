package examen.moviles.project.di

import examen.moviles.project.catalog.presentation.viewmodel.CatalogViewModel
import examen.moviles.project.earthquake.presentation.viewmodel.EarthquakeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::CatalogViewModel)
    //Examen 3. USGS Earthquakes
    viewModelOf(::EarthquakeViewModel)
}