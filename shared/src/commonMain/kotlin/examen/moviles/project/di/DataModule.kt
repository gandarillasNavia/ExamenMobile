package examen.moviles.project.di

import org.koin.dsl.module
import examen.moviles.project.catalog.data.datasource.CatalogRemoteDataSource
import examen.moviles.project.catalog.data.repository.CatalogRepositoryImpl
import examen.moviles.project.catalog.data.service.CatalogApiService
import examen.moviles.project.catalog.domain.repository.CatalogRepository

import examen.moviles.project.earthquake.data.datasource.EarthquakeRemoteDataSource
import examen.moviles.project.earthquake.data.repository.EarthquakeRepositoryImpl
import examen.moviles.project.earthquake.data.service.EarthquakeApiService
import examen.moviles.project.earthquake.domain.repository.EarthquakeRepository

val dataModule = module {
    single<CatalogRemoteDataSource> { CatalogApiService() }
    single<CatalogRepository> { CatalogRepositoryImpl(get()) }

    //Examen 3. USGS Earthquakes
    single<EarthquakeRemoteDataSource> { EarthquakeApiService() }
    single<EarthquakeRepository> { EarthquakeRepositoryImpl(get()) }
}
