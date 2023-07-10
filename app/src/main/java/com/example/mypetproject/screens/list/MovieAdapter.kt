package com.example.mypetproject.screens.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mypetproject.R
import com.example.mypetproject.api.Doc
import com.example.mypetproject.databinding.ItemMovieBinding

interface MovieActionListener {
    fun onMovieDetails(movie: Doc)

}

class MovieAdapter(
    private val movieActionListener: MovieActionListener
): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(), View.OnClickListener {

    var movies = listOf<Doc>()

    override fun getItemCount(): Int = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)

        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        with(holder.binding) {

            holder.itemView.tag = movie

            titleMovieTV.text = movie.name
            yearTV.text = movie.year.toString()
            ratingKPTV.text = movie.rating.kp.toString()
            ratingImdbTV.text = movie.rating.imdb.toString()
            if (movie.poster.url.isNotBlank()){
                Glide.with(posterIVItem.context)
                    .load(movie.poster)
                    .placeholder(R.drawable.image_search)
                    .error(R.drawable.image_search)
                    .into(posterIVItem)
            } else {
                posterIVItem.setImageResource(R.drawable.image_search)
            }
        }
    }

    class MovieViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onClick(v: View) {
        val movie = v.tag as Doc
        movieActionListener.onMovieDetails(movie)
    }
}
