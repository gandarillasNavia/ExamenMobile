package examen.moviles.project.earthquake.data.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import examen.moviles.project.earthquake.data.datasource.EarthquakeRemoteDataSource
import examen.moviles.project.earthquake.data.dto.EarthquakeResponseDto

class EarthquakeApiService : EarthquakeRemoteDataSource {

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

    override suspend fun getEarthquakes(): EarthquakeResponseDto {
        val url = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&minmagnitude=5&limit=3"
        val response = client.get(url)
        return response.body<EarthquakeResponseDto>()
    }
}