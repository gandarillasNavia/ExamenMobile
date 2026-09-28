package examen.moviles.project.di

import org.koin.dsl.module
import examen.moviles.project.catalog.domain.usecase.GetMoviesUseCase
import examen.moviles.project.earthquake.domain.usecase.GetEarthquakesUseCase
val domainModule = module {
    factory { GetMoviesUseCase(get()) }
    //Examen 3. USGS Earthquakes
    factory { GetEarthquakesUseCase(get()) }
}