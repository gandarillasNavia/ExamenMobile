package examen.moviles.project.di

import org.koin.dsl.module
import examen.moviles.project.catalog.data.datasource.CatalogRemoteDataSource
import examen.moviles.project.catalog.data.repository.CatalogRepositoryImpl
import examen.moviles.project.catalog.data.service.CatalogApiService
import examen.moviles.project.catalog.domain.repository.CatalogRepository

val dataModule = module {
    single<CatalogRemoteDataSource> { CatalogApiService() }
    single<CatalogRepository> { CatalogRepositoryImpl(get()) }
}
