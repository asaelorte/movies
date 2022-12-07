package com.example.movies.data

import com.example.domain.Movie
import com.example.movies.data.database.Movie as DomainMovie
import com.example.movies.data.server.Movie as ServerMovie

fun Movie.toRoomMovie(): DomainMovie =
    DomainMovie(
        id,
        title,
        overview,
        releaseDate,
        posterPath,
        backdropPath,
        originalLanguage,
        originalTitle,
        popularity,
        voteAverage,
        favorite
    )

fun DomainMovie.toDomainMovie(): Movie = Movie(
    id,
    title,
    overview,
    releaseDate,
    posterPath,
    backdropPath,
    originalLanguage,
    originalTitle,
    popularity,
    voteAverage,
    favorite
)

fun ServerMovie.toDomainMovie(): Movie =
    Movie(
        0,
        title,
        overview,
        releaseDate,
        posterPath,
        backdropPath ?: posterPath,
        originalLanguage,
        originalTitle,
        popularity,
        voteAverage,
        false
    )