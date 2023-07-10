package com.example.mypetproject.screens.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.mypetproject.R
import com.example.mypetproject.databinding.FragmentMovieDetailsBinding
import com.example.mypetproject.screens.factory


class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding: FragmentMovieDetailsBinding get() = _binding!!

    private val viewModel: MovieDetailsViewModel by viewModels { factory() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadMovie(requireArguments().getInt(ARG_MOVIE_ID))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)

        viewModel.movieDetails.observe(viewLifecycleOwner, Observer {
            binding.titleMovieTV.text = it.name
            binding.descriptionMovieTV.text = it.description
            if (it.poster.url.isNotBlank()) {
                Glide.with(this)
                    .load(it.poster)
                    .into(binding.posterIVDetails)
            } else {
                Glide.with(this)
                    .load(R.drawable.image_search)
                    .into(binding.posterIVDetails)
            }
        })

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    
    companion object{

        private const val ARG_MOVIE_ID = "ARG_MOVIE_ID"

        fun newInstance(movieId: Int): MovieDetailsFragment {

            val fragment = MovieDetailsFragment()
            fragment.arguments = bundleOf(ARG_MOVIE_ID to movieId)
            return fragment
        }
    }
}