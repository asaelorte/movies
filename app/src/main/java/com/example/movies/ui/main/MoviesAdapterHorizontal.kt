package com.example.movies.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Movie
import com.example.movies.R
import com.example.movies.databinding.ViewMovieHorizontalBinding
import com.example.movies.ui.common.basicDiffUtil
import com.example.movies.ui.common.inflate
import com.example.movies.ui.common.loadUrl

class MoviesAdapterHorizontal(private val listener: (Movie) -> Unit) :
    RecyclerView.Adapter<MoviesAdapterHorizontal.ViewHolder>() {

    var movies: List<Movie> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_movie_horizontal, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { listener(movie) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ViewMovieHorizontalBinding.bind(view)
        fun bind(movie: Movie) = with(binding) {
            movieCover.loadUrl("https://image.tmdb.org/t/p/w185/${movie.posterPath}")
        }
    }
}