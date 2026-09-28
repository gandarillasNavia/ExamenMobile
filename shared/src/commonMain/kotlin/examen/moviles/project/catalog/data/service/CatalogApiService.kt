package examen.moviles.project.catalog.data.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import examen.moviles.project.catalog.data.datasource.CatalogRemoteDataSource
import examen.moviles.project.catalog.data.dto.CatalogResponseDto

class CatalogApiService : CatalogRemoteDataSource {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    override suspend fun getMovies(): CatalogResponseDto {
        val url = "https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=fa3e844ce31744388e07fa47c7c5d8c3"
        val response = client.get(url)
        try {
            return response.body<CatalogResponseDto>()
        } catch (e: Exception) {
            throw e
        }
    }
}