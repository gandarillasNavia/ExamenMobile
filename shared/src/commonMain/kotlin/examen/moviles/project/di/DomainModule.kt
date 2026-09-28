package examen.moviles.project.di

import org.koin.dsl.module
import examen.moviles.project.catalog.domain.usecase.GetMoviesUseCase
val domainModule = module {
    factory { GetMoviesUseCase(get()) }
}