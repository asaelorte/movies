package com.example.movies.ui.detail

import androidx.lifecycle.SavedStateHandle
import com.example.data.repository.MoviesRepository
import com.example.usecases.FindMovieById
import com.example.usecases.GetPopularMovies
import com.example.usecases.ToggleMovieFavorite
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import java.lang.IllegalStateException
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class DetailActivityModule {

    @Provides
    fun findMovieByIdProvider(moviesRepository: MoviesRepository) = FindMovieById(moviesRepository)

    @Provides
    fun toggleMovieFavoriteProvider(moviesRepository: MoviesRepository) =
        ToggleMovieFavorite(moviesRepository)

    @Provides
    @Named("movieId")
    fun movieIdProvider(stateHandle: SavedStateHandle): Int =
        stateHandle.get<Int>(DetailActivity.MOVIE)
            ?: throw IllegalStateException("Movie Id not found in the state handle")
}
