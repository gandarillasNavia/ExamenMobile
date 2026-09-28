package examen.moviles.project.catalog.data.mapper

import examen.moviles.project.catalog.data.dto.MovieDto
import examen.moviles.project.catalog.domain.model.MovieModel

fun MovieDto.toDomain(): MovieModel = MovieModel(
    id = id ?: 0,
    title = title ?: "",
    overview = overview ?: "",
    posterUrl = posterPath?.let { "https://image.tmdb.org/t/p/w500$it" } ?: "",
    backdropUrl = backdropPath?.let { "https://image.tmdb.org/t/p/w500$it" } ?: "",
    voteAverage = voteAverage ?: 0.0,
    releaseDate = releaseDate ?: ""
)