package com.example.mypetproject.screens.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mypetproject.R
import com.example.mypetproject.api.Doc
import com.example.mypetproject.databinding.ItemMovieBinding

class MovieAdapter(
    private val movieClickListener: MovieClickListener
): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun getItemCount(): Int = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        with(holder.binding) {

            holder.itemView.setOnClickListener {
                movieClickListener.onMovieClick(movie)
            }

            titleMovieTV.text = movie.name
            yearTV.text = movie.year.toString()
            ratingKPTV.text = movie.rating.kp.toString()
            ratingImdbTV.text = movie.rating.imdb.toString()
            if (movie.poster.url.isNotBlank()){
                Glide.with(posterIVItem.context)
                    .load(movie.poster.url)
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

    private val diffCallback = object : DiffUtil.ItemCallback<Doc>() {
        override fun areItemsTheSame(oldItem: Doc, newItem: Doc): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Doc, newItem: Doc): Boolean {
            return newItem == oldItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var movies: List<Doc>
        get() = differ.currentList
        set(value) { differ.submitList(value) }
}
