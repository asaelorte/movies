package com.example.movies.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.Movie
import com.example.movies.ui.common.ScopedViewModel
import com.example.usecases.FindMovieById
import com.example.usecases.ToggleMovieFavorite
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DetailViewModel @Inject constructor(
    @Named("movieId") private val movieId: Int,
    private val findMovieById: FindMovieById,
    private val toggleMovieFavorite: ToggleMovieFavorite
) :
    ScopedViewModel() {

    class UiModel(val movie: Movie)

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) findMovie()
            return _model
        }

    private fun findMovie() = launch {
        _model.value = UiModel(findMovieById.invoke(movieId))
    }

    fun onFavoriteClicked() = launch {
        _model.value?.movie?.let {
            _model.value = UiModel(toggleMovieFavorite.invoke(it))
        }
    }
}