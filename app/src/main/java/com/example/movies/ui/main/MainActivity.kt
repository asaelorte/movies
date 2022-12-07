package com.example.movies.ui.main

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.movies.R
import com.example.movies.databinding.ActivityMainBinding
import com.example.movies.ui.common.PermissionRequester
import com.example.movies.ui.common.app
//import com.example.movies.ui.common.getViewModel
import com.example.movies.ui.common.startActivity
import com.example.movies.ui.detail.DetailActivity
import com.example.movies.ui.main.MainViewModel.UiModel
import com.example.usecases.GetPopularMovies
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MoviesAdapter
    private lateinit var adapterHorizontal: MoviesAdapterHorizontal


    private val coarsePermissionRequester =
        PermissionRequester(this, ACCESS_COARSE_LOCATION)

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MoviesAdapter(viewModel::onMovieClicked)
        adapterHorizontal = MoviesAdapterHorizontal(viewModel::onMovieClicked)
        binding.recyclerRecommendation.adapter = adapter
        binding.recyclerTopRated.adapter = adapterHorizontal
        binding.recyclerUpcoming.adapter = adapterHorizontal
        viewModel.model.observe(this, Observer(::updateUi))
    }

    private fun updateUi(model: UiModel) {

        binding.progress.visibility = if (model is UiModel.Loading) View.VISIBLE else View.GONE

        when (model) {
            is UiModel.Content -> {
                adapter.movies = model.movies
                adapterHorizontal.movies = model.movies
            }
            is UiModel.Navigation -> startActivity<DetailActivity> {
                putExtra(DetailActivity.MOVIE, model.movie.id)
            }
            UiModel.RequestLocationPermission -> coarsePermissionRequester.request {
                viewModel.onCoarsePermissionRequested()
            }
            else -> {}
        }
    }
}