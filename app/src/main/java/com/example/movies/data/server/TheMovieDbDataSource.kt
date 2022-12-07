package com.example.movies.data.server

import com.example.data.source.RemoteDataSource
import com.example.domain.Movie
import com.example.movies.data.toDomainMovie

class TheMovieDbDataSource : RemoteDataSource {

    override suspend fun getPopularMovies(apiKey: String, region: String): List<Movie> =
        TheMovieDb.service
            .listPopularMoviesAsync(apiKey, region)
            .results
            .map { it.toDomainMovie() }
}